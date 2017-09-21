package com.yelot.crm.controller;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxUserList;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.exception.WxErrorException;
import com.yelot.crm.Util.ResultData;
import com.yelot.crm.entity.Account;
import com.yelot.crm.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 微信入口
 * Created by kee on 17/9/19.
 */
@Controller
@RequestMapping("wx")
public class WxController {

    private IService iService = new WxService();

    @Autowired
    private AccountMapper accountMapper;


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
     * @return
     */
    @RequestMapping("account")
    public String accountEntrance(String menu){
        System.out.println("accountEntrance log");
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxConfig.getInstance().getAppId()+
                "&redirect_uri=http://crm.rojewel.com/wx/to-account/?menu=" +menu+
                "&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
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

        String code = request.getParameter("code");
        model.addAttribute("code",code);

        try {
            WxOAuth2AccessTokenResult result = iService.oauth2ToGetAccessToken(code);
            if(result != null){
                System.out.println("kee token:"+result.getAccess_token());
                System.out.println("kee openid:"+result.getOpenid());
                model.addAttribute("accessToken",result.getAccess_token());
                model.addAttribute("openid",result.getOpenid());
                //step 1,验证用户是否已经绑定账号
                Account account = accountMapper.findByOpenId(result.getOpenid());
                if(account == null){
                    return "weixin/account_login";
                }
            }

        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        if(menu == null){
            return "weixin/my_card";
        }else if(menu.equals("my-card")){
            return "weixin/my_card";
        }else if(menu.equals("my-account")){
            return "weixin/my_account";
        }else if(menu.equals("my-points")){
            return "weixin/my_points";
        }else if(menu.equals("update-profile")){
            return "weixin/update_profile";
        }else if(menu.equals("my-coupon")){
            return "weixin/my_coupon";
        }


        System.out.println("weixin code = "+code);

        return "weixin/my_card";
    }

    @RequestMapping("get-verify-code")
    public ResultData getVerifyCode(String phone){


        return ResultData.ok();
    }



    @RequestMapping("my-account")
    public String myAccount(HttpServletRequest request,Model model){
        System.out.println("my-account log");




        return "weixin/my_account";
    }

    @RequestMapping("my-points")
    public String myPoints(){
        return "weixin/my_points";
    }

    @RequestMapping("update-profile")
    public String updateProfile(){
        return "weixin/update_profile";
    }

    /**
     * 优惠券
     * @return
     */
    @RequestMapping("my-coupon")
    public String myCoupon(){

        return "weixin/my_coupon";
    }



}
