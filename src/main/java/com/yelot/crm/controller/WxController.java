package com.yelot.crm.controller;

import com.alibaba.fastjson.JSON;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxCardApiSignature;
import com.soecode.wxtools.bean.WxJsapiConfig;
import com.soecode.wxtools.bean.WxUserList;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.bean.result.card.Card;
import com.soecode.wxtools.exception.WxErrorException;
import com.soecode.wxtools.util.StringUtils;
import com.yelot.crm.AppConfig;
import com.yelot.crm.Util.Constants;
import com.yelot.crm.Util.ResultData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.entity.Account;
import com.yelot.crm.entity.RepairOrder;
import com.yelot.crm.entity.SendMessage;
import com.yelot.crm.enums.VerifyCodeType;
import com.yelot.crm.mapper.AccountMapper;
import com.yelot.crm.mapper.RepairOrderMapper;
import com.yelot.crm.service.RepairOrderService;
import com.yelot.crm.service.SendMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.MalformedObjectNameException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 微信入口
 * Created by kee on 17/9/19.
 */
@Controller
@RequestMapping("wx")
public class WxController {

    private WxService iService = new WxService();

//    private Logger log = LoggerFactory.getLogger(WxController.class);

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private SendMessageService sendMessageService;
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private RepairOrderService repairOrderService;


    /**
     * 微信公众号  服务器配置(未启用)
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("check")
    public void weixin(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // 验证服务器的有效性
        PrintWriter out = response.getWriter();
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (iService.checkSignature(signature, timestamp, nonce, echostr)) {
            out.print(echostr);
        }
    }

    /**
     * 会员 菜单的总入口，统一控制
     * menu 配置的my-order,my-*，等
     * @return
     */
    @RequestMapping("account")
    public String accountEntrance(String menu){
        System.out.println("accountEntrance log");
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxConfig.getInstance().getAppId()+
                "&redirect_uri=http://crm.rojewel.com/wx/to-account/?menu=" +menu+
                "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
        return "redirect:"+url;
    }


    /**
     * 总是会重定向到该Url中,参考accountEntrance()
     * 进入会员系统的业务逻辑如下
     * 1）首先查询t_account 该微信用户是会员，并且已经绑定手机号，否则需要跳转到绑定手机号页面
     * @param menu
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("to-account")
    public String toAccount(String menu,HttpServletRequest request,Model model){
        System.out.println("to-account log");
        System.out.println("to-account menu:"+menu);

        String code = request.getParameter("code");//微信平台的中间结果code
        model.addAttribute("code",code);
        model.addAttribute("menu",menu);

        try {
            WxOAuth2AccessTokenResult result = iService.oauth2ToGetAccessToken(code);
            if(result != null){
                System.out.println("kee token:"+result.getAccess_token());
                System.out.println("kee openid:"+result.getOpenid());
                model.addAttribute("accessToken",result.getAccess_token());
                model.addAttribute("openid",result.getOpenid());
                //step 1,验证用户是否已经绑定账号,如果查不到openid
                Account account = accountMapper.findByOpenId(result.getOpenid());
                if(account == null && !menu.equals("my-order")){//除了my-order页面
                    return "weixin/member/login";
                }else if(account == null && menu.equals("my-order")){//第一次点击的是该订单菜单，直接进入手机号注册页面
                    String params = "code="+code+"&openid="+result.getOpenid()+"&accessToken="+result.getAccess_token()+"&menu="+menu;
                    return "redirect:/wx/to-phone-register?"+params;
                }else if(account != null && menu.equals("my-order") && StringUtils.isEmpty(account.getPhone())){//已经绑定了微信号，但是没有绑定手机号
                    System.out.println("account phone is empty");
                    String params = "code="+code+"&openid="+result.getOpenid()+"&accessToken="+result.getAccess_token()+"&menu="+menu;
                    return "redirect:/wx/to-phone-register?"+params;
                }else {
                    //使用openid作为key
                    UserUtil.setSession(result.getOpenid(),account);

                }
                String url = jumpMenu(menu,result.getOpenid());
                if (url != null) return url;
            }

        } catch (WxErrorException e) {
            e.printStackTrace();
        }



        return "redirect:/wx/my-card";
    }

    private String jumpMenu(String menu,String openid) {
        if(menu == null){
            return "redirect:/wx/my-card?openid="+openid;
        }else if(menu.equals("my-card")){//虚拟卡
            return "redirect:/wx/my-card?openid="+openid;
        }else if(menu.equals("my-coupon")){//会员专享
            return "redirect:/wx/my-coupon?openid="+openid;
        }else if(menu.equals("my-profile")){//更新资料
            return "redirect:/wx/my-profile?openid="+openid;
        }else if(menu.equals("my-points")){//我的积分
            return "redirect:/wx/my-points?openid="+openid;
        }else if(menu.equals("my-order")){//个人中心,判断当前用户是否已经绑定了手机号，否则需要调整到绑定手机号注册页面
            return "redirect:/wx/my-order?openid="+openid;
        }
        return null;
    }

    /**
     * 获取微信平台用户授权
     * @return
     */
    @RequestMapping("wechat-login")
    public String wechatLogin(String code,HttpServletRequest request,String menu){
        System.out.println("wechat-login code="+code+",menu="+menu);

        String openid = "";


        try {
//            //code 只能使用一次
            WxOAuth2AccessTokenResult result = iService.oauth2ToGetAccessToken(code);
            WxUserList.WxUser wxUser = iService.oauth2ToGetUserInfo(result.getAccess_token(),new WxUserGet(result.getOpenid(),"zh_CN"));
            if(wxUser != null){//微信登录
                int maxId = accountMapper.findMaxId();//bug 当没有
                int nextId = Constants.FirstAccountNo + maxId;//第一个1001，以后每次增加1，id为自增
                System.out.println("result openid="+result.getOpenid());
                System.out.println("wxUser="+JSON.toJSONString(wxUser));
                Account account = new Account();
                account.setWxOpenid(result.getOpenid());
                account.setWxNickname(wxUser.getNickname());
                account.setCity(wxUser.getCity());
                account.setFullName(wxUser.getNickname());
//                account.setPhone("");
                account.setAccountNo(nextId+"");
                accountMapper.save(account);
                UserUtil.setSession(Constants.SessionAccount,account);

                openid = result.getOpenid();


            }else{
//                System.out.println("wxUser is null");

            }
//
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return jumpMenu(menu,openid);

    }

    /**
     * 弹出授权页面
     * @param menu
     * @return
     */
    @RequestMapping("account-wechat")
    public String accountAuth(String menu,String code){
        System.out.println("accountAuth log");
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxConfig.getInstance().getAppId()+
                "&redirect_uri=http://crm.rojewel.com/wx/wechat-login/?menu=" +menu+
                "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
        return "redirect:"+url;
    }

    /**
     * 手机注册页面
     * @param model
     * @param code
     * @return
     */
    @RequestMapping("to-phone-register")
    public String toPhoneRegister(Model model,String code,String openid,String menu,String accessToken){
        System.out.println("to-phone-register code:"+code);
        model.addAttribute("accessToken", accessToken);
        model.addAttribute("openid", openid);
        model.addAttribute("menu", menu);

        return "weixin/member/account_register";
    }


    /**
     * menu 保存用户的请求参数，通过该参数调转到对于的action
     * @param menu
     * @param openid
     * @param phone
     * @param verifyCode
     * @return
     */
    @RequestMapping("to-register")
    @ResponseBody
    public ResultData accountRegister(String menu,String openid,String phone,String verifyCode){
        System.out.println("menu:"+menu+",phone:"+phone+",verifyCode:"+verifyCode);
        Account myAccount = accountMapper.findByOpenId(openid);
        if(myAccount != null){//已经微信登录，绑定手机号
            accountMapper.updatePhone(phone,openid);
            return ResultData.ok().putDataValue("action",menu);
        }

        int maxId = accountMapper.findMaxId();//bug 当没有
        int nextId = Constants.FirstAccountNo + maxId;//第一个1001，以后每次增加1，id为自增

        Account account = new Account();
        account.setWxOpenid(openid);
        account.setPhone(phone);
        account.setAccountNo(nextId+"");
        accountMapper.save(account);
        UserUtil.setSession(openid,account);
        return ResultData.ok().putDataValue("action",menu);
    }

    @ResponseBody
    @RequestMapping("send-verify-code")
    public ResultData sendVerifyCode(String phone) {
        //请求发送验证码接口
        boolean isSuc = sendMessageService.sendVerifyCode(phone);
        if(isSuc){
            return ResultData.ok();
        }
        return ResultData.errorRequest();
    }

    @ResponseBody
    @RequestMapping("check-verify-code")
    public ResultData getVerifyCode(String phone,String verifyCode){

        Integer status = sendMessageService.checkVerifyCode(phone,verifyCode);
        if(status == VerifyCodeType.Verify_Success.getCode()){
            return ResultData.ok();
        }


        return ResultData.errorRequest();
    }


    @ResponseBody
    @RequestMapping("update-fullname")
    public ResultData updateFullName(String openid,String phone,String fullName){
        accountMapper.updateFullName(openid,phone,fullName);
        return ResultData.ok();
    }

    @ResponseBody
    @RequestMapping("update-my-phone")
    public ResultData updateMyPhone(String openid,String phone,String myPhone){
        accountMapper.updateMyPhone(openid,phone,myPhone);
        return ResultData.ok();
    }

    @ResponseBody
    @RequestMapping("update-interest")
    public ResultData updateInterest(String openid,String phone,String interest){//interest 就是json数组，前台已经处理
//        String interestJson = JSON.toJSONString(interest);
        accountMapper.updateInterest(openid,phone,interest);
        return ResultData.ok();

    }

    @ResponseBody
    @RequestMapping("update-city")
    public ResultData updateCity(String openid,String phone,String city){
        accountMapper.updateCity(openid,phone,city);
        return ResultData.ok();
    }

    /**
     * 我的虚拟卡
     * @return
     */
    @RequestMapping("my-card")
    public String myCard(String openid,Model model){
        Account account = UserUtil.getCurrentAccount(openid);
        model.addAttribute("account",account);

        return "weixin/member/my_card";
    }


    /**
     * 会员专享：优惠券
     * @return
     */
    @RequestMapping("my-coupon")
    public String myCoupon(Model model){
        List<Card> cardList = iService.getCardList();
        for (int i = 0; cardList != null && i < cardList.size(); i++) {
            String cardId = cardList.get(i).getCash().getBaseInfo().getId();
            WxCardApiSignature wxCardApiSignature = iService.createCardApiConfig(null,null,null);
            System.out.println("cardSignature:"+JSON.toJSONString(wxCardApiSignature));
            String signature = wxCardApiSignature.getSignature();
            Long timestamp = wxCardApiSignature.getTimestamp();
            String noncestr = wxCardApiSignature.getNoncestr();
            cardList.get(i).setSignature(signature);
            cardList.get(i).setTimestamp(timestamp);
            cardList.get(i).setNoncestr(noncestr);
            Date endDate = new Date(cardList.get(i).getCash().getBaseInfo().getDateInfo().getEndTimestamp()*1000);
            cardList.get(i).setEndDate(endDate);

        }
        model.addAttribute("cardList",cardList);
        WxJsapiConfig wxJsapiConfig = initJsConfig();
//        model.addAttribute("wxConfigJson",JSON.toJSONString(wxJsapiConfig));
        model.addAttribute("wxConfig",wxJsapiConfig);
        System.out.println("jsConfig:"+JSON.toJSONString(wxJsapiConfig));
        //

        return "weixin/member/my_coupon";
    }

    private WxJsapiConfig initJsConfig(){
        List<String> jsApiList = new ArrayList<>();
        String url = appConfig.getHostUrl()+"/wx/my-coupon";
        jsApiList.add("chooseImage");
        jsApiList.add("previewImage");
        jsApiList.add("addCard");
        jsApiList.add("chooseCard");
        jsApiList.add("openCard");
        WxJsapiConfig config = null;
        try {
            config = iService.createJsapiConfig(url, jsApiList);
            config.setAppid(WxConfig.getInstance().getAppId());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return config;
    }


    @RequestMapping("to-login")
    public String toLogin(String from,Model model){
        return "weixin/member/login";
    }

    /**
     * 更新资料
     * @return
     */
    @RequestMapping("my-profile")
    public String updateProfile(String openid,Model model){
        Account account = UserUtil.getCurrentAccount(openid);
        model.addAttribute("account",account);
        return "weixin/member/information";
    }


    /**
     * 我的积分
     */
    @RequestMapping("my-points")
    public String myPoints(String openid){
        //逻辑处理，积分必须要绑定手机号
        Account account = UserUtil.getCurrentAccount(openid);
//        if(account.getPhone() == null){
//            return "redirect:to-login?from="+"my-points";
//        }

        return "weixin/member/score";
    }

    /**
     * 个人中心
     * @return
     */
//    @RequestMapping("my-account")
//    public String myAccount(){
//        return "weixin/member/personal_center";
//    }

    @RequestMapping("my-order")
    public String myOrder(Model model,String openid){
        if(openid == null){
            openid = "";
        }
        System.out.println("openid="+openid);
        Account account = accountMapper.findByOpenId(openid);
        String testPhone = "";
        if(account != null){
            testPhone = account.getPhone();
        }

        List<RepairOrder> ongoingList = repairOrderService.findByPhoneAndStatus(testPhone,"ongoing");
        List<RepairOrder> finishedList = repairOrderService.findByPhoneAndStatus(testPhone,"finished");
        List<RepairOrder> allList = repairOrderService.findByPhoneAndStatus(testPhone,"all");
        model.addAttribute("ongoingList",ongoingList);
        model.addAttribute("finishedList",finishedList);
        model.addAttribute("allList",allList);
        return "weixin/member/order";
    }

    @RequestMapping("my-order-detail")
    public String myOrderDetail(){
        return "weixin/member/order_detail";
    }

    @RequestMapping("my-address")
    public String myAddress(){
        return "weixin/member/address";

    }

    @RequestMapping("my-information")
    public String centerInformation(){
        return "weixin/member/personal_center_information";
    }


}
