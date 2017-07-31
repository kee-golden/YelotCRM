package com.yelot.crm.controller;

import com.yelot.crm.Util.CookieTool;
import com.yelot.crm.Util.ResponseData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.entity.User;
import com.yelot.crm.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xyzloveabc
 * @2017年6月10日
 */
@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;


	@RequestMapping("/")
	public String login(){
		return "login";
	}

    @RequestMapping("index")
    public String index(){
	    //暂时调整到角色列表
        return "redirect:/customer/index";
    }

	@RequestMapping("login")
	public String login(String username, String password, String remember,
						Model model, HttpServletRequest request, HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
//        String psdMd5 = new Md5Hash(password).toString();
		String psdMd5 = password;// 暂时不用md5 加密

		UsernamePasswordToken token = new UsernamePasswordToken(username, psdMd5);
		try {

			if (remember != null && remember.equals("rememberMe")) {//必须放在subject.login(token)  之前，之后bug。
				//使用Shrio rememberMe
//                token.setRememberMe(true);
				saveCookie(username, password, request, response);//自定义保存cookie

			}
			subject.login(token);//登录验证成功后,会执行以下,否则会抛出异常

			//根据不同的角色获取不同的菜单，现在增加了固定的第一个菜单，该菜单是在slideBar.jsp页面中写死了，就是第一个，
			//因而，不需要动态获取第一个菜单
			User user = UserUtil.getCurrentUser();
			String firstUrl = "index";


			return "redirect:/" + firstUrl;
		} catch (Exception e) {
			e.printStackTrace();

			model.addAttribute("username", username);
			model.addAttribute("status", 500);
			return "login";
		}
	}

	private void saveCookie(String username, String password, HttpServletRequest request, HttpServletResponse response){
		//一下为处理是否自动登录，保存cookie
		int  loginMaxAge = 30*24*60*60;   //定义账户密码的生命周期，这里是一个月。单位为秒

		CookieTool.addCookie(response , "username" , username , loginMaxAge); //将姓名加入到cookie中
		CookieTool.addCookie(response , "password" , password , loginMaxAge);   //将密码加入到cookie中
		CookieTool.addCookie(response , "remember" , "yes" , loginMaxAge);   //将密码加入到cookie中


	}

	/**
	 * 注销
	 */
	@RequestMapping("/logout")
	public String logout(Model model,HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
		String  username = UserUtil.getCurrentUser().getName();
		model.addAttribute("username",username);
		if (subject.isAuthenticated()) {
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
		}

		CookieTool.addCookie(response, "username", null, 0); // 清除Cookie
		CookieTool.addCookie(response, "password", null, 0); // 清除Cookie
		CookieTool.addCookie(response, "remember", null, 0); // 清除Cookie
		return "redirect:/";
	}
	
//	@RequestMapping("/login")
//	public ResponseData login(String username, String password) {
//		return loginService.login(username, password);
//	}

}
