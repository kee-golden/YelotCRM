package com.yelot.crm.shiro;


import com.yelot.crm.entity.User;
import com.yelot.crm.mapper.PrivilegeMapper;
import com.yelot.crm.mapper.UserMapper;
import com.yelot.crm.service.BaseMenuService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {

//	@Autowired
//	private UserService userService;

	@Autowired
	private BaseMenuService baseMenuService;

	@Autowired
	private PrivilegeMapper privilegeMapper;

	@Autowired
	private UserMapper userMapper;


	/**
	 *  这个函数,登录成功后,怎么被回调了两次???
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName=(String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();

		return authorizationInfo;
	}

	/**
	 *
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName=(String)token.getPrincipal();
		User user = userMapper.findByUsername(userName);
			if(user!=null){
				//手动设置到session中
				baseMenuService.setUserAndMenus(user);

				AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user.getName(),user.getPassword(),"xx");
				return authcInfo;
			}else{
				return null;				
			}
	}

}
