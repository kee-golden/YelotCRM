package com.yelot.crm.controller;

import com.yelot.crm.Util.CookieTool;
import com.yelot.crm.Util.ResponseData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.entity.User;
import com.yelot.crm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by kee on 17/5/14.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("test")
    public String test(){
        return "hello world";
    }

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("login")
    public String login(String username,String password,String remember,
                        Model model,HttpServletRequest request,HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
//        String psdMd5 = new Md5Hash(password).toString();
        String psdMd5 = password;// 暂时不用md5 加密

        UsernamePasswordToken token = new UsernamePasswordToken(username, psdMd5);
        try {

            if(remember != null && remember.equals("rememberMe")){//必须放在subject.login(token)  之前，之后bug。
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

            model.addAttribute("username",username);
            model.addAttribute("status",500);
            return "login";
        }

//        User user = userService.login(username,password);
//        if(user != null){
//            return "redirect:/index";
//        }
//        return "login";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    private void saveCookie(String username, String password, HttpServletRequest request, HttpServletResponse response){
        //一下为处理是否自动登录，保存cookie
        int  loginMaxAge = 30*24*60*60;   //定义账户密码的生命周期，这里是一个月。单位为秒

        CookieTool.addCookie(response , "username" , username , loginMaxAge); //将姓名加入到cookie中
        CookieTool.addCookie(response , "password" , password , loginMaxAge);   //将密码加入到cookie中
        CookieTool.addCookie(response , "remember" , "yes" , loginMaxAge);   //将密码加入到cookie中


    }


    @RequestMapping("find")
    public User find(Long id){
        return userService.find(id);
    }

    @RequestMapping("save")
    public ResponseData save(User user){
        userService.save(user);
        return new ResponseData(ResponseData.SUCCESS,ResponseData.SUCCESS_MESSAGE);
    }

    /**
     * 更新用户状态is_alive = 0
     * @param id
     * @return
     */
    @RequestMapping("update-alive")
    public ResponseData updateAlive(Integer alive,Long id){
        userService.updateAlive(alive,id);
        return new ResponseData(ResponseData.SUCCESS,ResponseData.SUCCESS_MESSAGE);

    }

    /**
     * 查询所有用户，包含已删除状态的用户
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("find-by-page")
    public List<User> findByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        if(page <= 0){
            page = 1;
        }
        Integer start = (page - 1) * size;
        return userService.findByPage(start,size);
    }

    @RequestMapping("find-by-name")
    public List<User> findByNameLike(String name){
        return userService.findByNameLike(name);
    }


}
