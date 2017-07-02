package com.yelot.crm.Util;

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
}