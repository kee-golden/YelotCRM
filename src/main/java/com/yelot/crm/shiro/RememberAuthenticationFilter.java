package com.yelot.crm.shiro;

import com.yelot.crm.Util.Constants;
import com.yelot.crm.Util.CookieTool;
import com.yelot.crm.entity.User;
import com.yelot.crm.mapper.UserMapper;
import com.yelot.crm.service.BaseMenuService;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kee on 17/1/7.
 */
@Component
public class RememberAuthenticationFilter extends FormAuthenticationFilter{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BaseMenuService baseMenuService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);

        Cookie rememberMeCookie = CookieTool.getCookieByName((HttpServletRequest) request, "remember");
        Cookie usernameCookie = CookieTool.getCookieByName((HttpServletRequest) request, "username");

        //如果 isAuthenticated 为 false 证明不是登录过的，同时 isRememberd 为true 证明是没登陆直接通过记住我功能进来的
        if(!subject.isAuthenticated() && rememberMeCookie!=null && rememberMeCookie.getValue().equals("yes")
                && usernameCookie != null && usernameCookie.getValue() != null){

            //获取session看看是不是空的
            Session session = subject.getSession(true);

            //随便拿session的一个属性来看session当前是否是空的，你们的项目可以自行发挥
            if(session.getAttribute(Constants.SessionUsername) == null){

                //如果是空的才初始化，否则每次都要初始化，项目得慢死
                //这边根据前面的前提假设，拿到的是username
                String username = usernameCookie.getValue();
                if(username == null || username.equals("")){
                    return false;
                }

                //在这个方法里面做初始化用户上下文的事情，比如通过查询数据库来设置session值，你们自己发挥
                User user=userMapper.findByUsername(username);
                baseMenuService.setUserAndMenus(user);

            }
        }

        //这个方法本来只返回 subject.isAuthenticated() 现在我们加上 subject.isRemembered() 让它同时也兼容remember这种情况
        return subject.isAuthenticated() || (rememberMeCookie != null && rememberMeCookie.getValue().equals("yes"));
    }
}
