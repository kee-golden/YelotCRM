package com.yelot.crm.controller;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
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

    @RequestMapping("my-card")
    public String myCard(HttpServletRequest request,Model model){
        System.out.println("my-card log");
//        String code = request.getParameter("code");
//        String state = request.getParameter("state");
//        model.addAttribute("code",code);
//        return "weixin/my_card";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx98d897ca3e583985&" +
                "redirect_uri=http://crm.rojewel.com/wx/to-card/&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
        return "redirect:"+url;
    }

    @RequestMapping("to-card")
    public String toCard(HttpServletRequest request,Model model){
        System.out.println("my-account log");

        String code = request.getParameter("code");
                model.addAttribute("code",code);

        System.out.println("weixin code = "+code);



        return "weixin/my_card";
    }

    @RequestMapping("my-account")
    public String myAccount(HttpServletRequest request,Model model){
        System.out.println("my-account log");

        String code = request.getParameter("code");
        model.addAttribute("code",code);


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
        return "weixin/my_points";
    }


    @RequestMapping("getInfor")
    public String getInfor(HttpServletRequest req) {
        String CODE =  req.getParameter("code");
        String state =  req.getParameter("state");
//        String accessTokenURL = "https://api.weixin.qq.com/sns/oauth2/access_token?"
//                + "appid="+appID+"&secret="+开放平台APPSECRET+"&code=CODE&"
//                + "grant_type=authorization_code";
//        JSONObject jsonObject=HttpRequestUtil.httpRequest(accessTokenURL.
//                replaceAll("CODE", code), "GET", null);
//        boolean containsValue = jsonObject.containsKey("errcode");
//        if(containsValue){
//            String errcode = jsonObject.getString("errcode");
//            System.err.println("有错误");
//        }else{
//            String unionid = jsonObject.getString("unionid");
//        }

        return "pc/user-manage/";
    }
}
