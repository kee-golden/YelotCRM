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




    @RequestMapping("index")
    public String index(){
        return "admin/admin_index";
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
