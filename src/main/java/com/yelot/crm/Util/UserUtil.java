package com.yelot.crm.Util;

import com.yelot.crm.entity.Account;
import com.yelot.crm.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by kee on 16/11/26.
 */
public class UserUtil {

    public static User getCurrentUser(){
        Subject subject= SecurityUtils.getSubject();
        User user = (User)subject.getSession().getAttribute(Constants.SessionUsername);
        return user;
    }

    /**
     * 保存到session中
     * @param key
     * @param object
     */
    public static void setSession(String key,Object object){
        Subject subject= SecurityUtils.getSubject();
        subject.getSession().setAttribute(key,object);
    }

    public static Object getSession(String key){
        Subject subject= SecurityUtils.getSubject();
        return subject.getSession().getAttribute(key);

    }

    /**
     * 登录的正在访问的客户保存到session中
     * @param openid
     * @return
     */
    public static Account getCurrentAccount(String openid){
        Subject subject= SecurityUtils.getSubject();
        Account account = (Account)subject.getSession().getAttribute(openid);
        return account;

    }
}