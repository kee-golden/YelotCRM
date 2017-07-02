package com.yelot.crm.service;


import com.yelot.crm.Util.Constants;
import com.yelot.crm.entity.Privilege;
import com.yelot.crm.entity.Shop;
import com.yelot.crm.entity.User;
import com.yelot.crm.enums.PrivilegeType;
import com.yelot.crm.mapper.PrivilegeMapper;
import com.yelot.crm.mapper.ShopMapper;
import com.yelot.crm.mapper.UserMapper;
import com.yelot.crm.vo.BaseMenu;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Created by feng-er on 2016/4/5.
 */
@Service
public class BaseMenuService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShopMapper shopMapper;

//    @Autowired
//    private GroupDao groupDao;


//    @Autowired
//    private PrivilegeService privilegeService;

    @Autowired
    private PrivilegeMapper privilegeMapper;


    /**
     * 更具用户获取菜单
     *
     * @param userName userName
     * @return 菜单
     */
    public List<BaseMenu> getMenus(String userName) {
//        Admin admin = adminDao.findByUsername(userName);
        User user = userMapper.findByUsername(userName);
        List<BaseMenu> baseMenus = new ArrayList<>();

        // 当前的业务逻辑是平台管理员，用户名固定如：nctAdmin,后期这个需要调整
//        Integer roleType = 2;//默认2，企业用户
//        if(userName.equals("nctAdmin")){
//            roleType = 1;
//        }

        List<Privilege> menus = findAllByUserId(user.getId(),getRoleType(userName));

        for (Privilege parent : menus) {
            BaseMenu baseMenu = new BaseMenu(parent.getName(), parent.getCode(), parent.getAction(),parent.getMenuClass());
            List<BaseMenu> children = new ArrayList<>();
            for (Privilege menu : parent.getChildren()) {
                children.add(new BaseMenu(menu.getName(), menu.getCode(), menu.getAction(),menu.getMenuClass()));
            }
            baseMenu.setChildren(children);
            baseMenus.add(baseMenu);
        }

        return baseMenus;

    }


    /**
     * 用户登录,注册后,需要把菜单,用户保存到session中
     */
    public void setUserAndMenus(User user){
        List<BaseMenu> menus = this.getMenus(user.getName());

//        String groupId = admin.getGroup().getId();
//        Group group = groupDao.find(groupId);
//        admin.setGroup(group);

        Long shopId = user.getShop_id();
        Shop shop = shopMapper.find(shopId);
        user.setShop(shop);

        Subject currentUser = SecurityUtils.getSubject();
        Session shiroSession = currentUser.getSession();
        shiroSession.setAttribute(Constants.SessionMenus,menus);
        //保存当前用户
        shiroSession.setAttribute(Constants.SessionUsername,user);
        shiroSession.setAttribute("kee","kee1234");

        //添加权限
        Set<String> privileges = privilegeMapper.findCodeByAdminId(user.getId());
        shiroSession.setAttribute(Constants.SessionPrivileges, privileges);

    }

    private Integer getRoleType(String userName){
        // 当前的业务逻辑是平台管理员，用户名固定如：nctAdmin,后期这个需要调整
        Integer roleType = 2;//默认2，企业用户
        if(userName.equals("nctAdmin")){
            roleType = 1;
        }

        return roleType;
    }

    public String findFirstMenuUrl(Long userId,String userName){
        String firstUrl = "";
        List<Privilege> privilegeList = findAllByUserId(userId,getRoleType(userName));
        for (int i = 0; privilegeList != null && i < privilegeList.size(); i++) {
            List<Privilege> chirdren = privilegeList.get(i).getChildren();
            if(chirdren != null && chirdren.size()>0){
                firstUrl = chirdren.get(0).getAction();
                break;
            }
        }
        return firstUrl;
    }

    /**
     * 获取用户菜单树
     *
     * @return
     */
    public List<Privilege> findAllByUserId(Long userId, Integer roleType) {
        List<Privilege> parents;
        parents = privilegeMapper.findParentsByAdminIdAndType(userId, PrivilegeType.MENU.getCode(),roleType);

        if (parents == null) {
            return new ArrayList<>();
        }

        for (Privilege parent : parents) {
            List<Privilege> children = findChildren(parent.getId(), userId);
            parent.setChildren(children);
        }

        return parents;
    }

    /**
     * 获取子菜单
     *
     * @param parentId
     * @param userId
     * @return
     */
    public List<Privilege> findChildren(Long parentId, Long userId) {

        List<Privilege> menus = privilegeMapper.findByParentIdAndAdminIdAndType(parentId, userId, PrivilegeType.MENU.getCode());
        for (Privilege menu : menus) {
            List<Privilege> children = findChildren(menu.getId(), userId);
            menu.setChildren(children);
        }

        return menus;
    }


}
