package com.yelot.crm.controller;


import com.yelot.crm.Util.ResultData;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Privilege;
import com.yelot.crm.entity.Role;
import com.yelot.crm.enums.PrivilegeType;
import com.yelot.crm.mapper.PrivilegeMapper;
import com.yelot.crm.mapper.RoleMapper;
import com.yelot.crm.service.PrivilegeService;
import com.yelot.crm.service.RoleService;
import com.yelot.crm.vo.Table;
import com.yelot.crm.vo.Tree;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by renshilei on 2015/9/7.
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleMapper roleMapper;


//    @Autowired
//    private PrivilegeService menuService;

    @Autowired
    private PrivilegeMapper privilegeMapper;


    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;

    /**
     * 角色列表
     *
     * @return
     */
    @RequestMapping("list")
    public String list(Model model) {
//        Group group = AdminUtil.getCurrentAdmin().getGroup();
        List<Role> roleList = roleMapper.findAll();
        model.addAttribute("roleList", roleList);
        return "role/list";
    }


    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("list-data")
    public Table listData(
            @RequestParam(value = "start", defaultValue = "0") Integer start,
            @RequestParam(value = "length", defaultValue = "20") Integer length) {

//        Group group = AdminUtil.getCurrentAdmin().getGroup();
        int pageCount = roleMapper.countTotalPage();
        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);//length = 20,角色不多，在一页显示
        List<Role> roleList = roleMapper.findByPage(pageHelper);
        return new Table(pageCount, pageCount, roleList);
    }

    /**
     * 形
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/privilege/tree-data")
    public List<Tree> treeData(String id, String roleId) {
        List<Privilege> privilegeList;
        if (StringUtils.isEmpty(id) || StringUtils.equals("#", id)) {
            privilegeList = privilegeService.findAll();
            List<Tree> treeList = getTrees(privilegeList, roleId);
            Tree root = new Tree();
            root.setId("#");
            root.setName("菜单管理");
            root.setIsParent(true);
            root.setIconSkin("root");
            root.setType(PrivilegeType.MENU.getCode());
            root.setChildren(treeList);
            List<Tree> rootList = new ArrayList<>();
            rootList.add(root);
            return rootList;
        } else {
            privilegeList = privilegeService.findChildren(Long.valueOf(id));
            return getTrees(privilegeList, roleId);
        }
    }

    /**
     * 角色的权限展示   （List<Privilege>=>List<Tree>）
     *
     * @param privilegeList
     * @return
     */
    private List<Tree> getTrees(List<Privilege> privilegeList, String roleId) {
        List<Tree> treeList = new ArrayList<>();
        Set<String> privilegeSet = privilegeMapper.findCodeByRoleId(roleId);

        for (Privilege privilege : privilegeList) {
            Tree tree = new Tree();
            tree.setId(privilege.getId() + "");
            tree.setName(privilege.getName());

            if (privilege.getType().equals(PrivilegeType.BUTTON.getCode())) {
                tree.setIsParent(false);
            } else {
                tree.setIsParent(true);
            }
            if (privilegeSet.contains(privilege.getCode())) {
                tree.setChecked(true);
            } else {
                tree.setChecked(false);
            }

            tree.setIconSkin(PrivilegeType.getIconSkin(privilege.getType()));
            tree.setType(privilege.getType());
            treeList.add(tree);
        }
        return treeList;
    }


    /**
     * 添加角色
     *
     * @param model
     * @return
     */
    @RequestMapping("add")
    public String add(Model model) {
        Role role;
        role = new Role();

        model.addAttribute("bean", role);

        List<Privilege> menuList = privilegeService.findAll();


        Map<String, Boolean> roleMenuMap = new HashMap<>();
        Map<Object, String> typeMap = new HashMap<>();
        typeMap.put(PrivilegeType.MENU.getCode(), PrivilegeType.MENU.getMessage());
        typeMap.put(PrivilegeType.BUTTON.getCode(), PrivilegeType.BUTTON.getMessage());

        model.addAttribute("typeMap", typeMap);
//
        model.addAttribute("menuList", menuList);
        model.addAttribute("roleMenuMap", roleMenuMap);
        model.addAttribute("typeMap", typeMap);

        return "role/edit";
    }

    /**
     * 编辑角色
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("edit")
    public String edit(String id, Model model) {
        Role role;
        if (id == null) {
            role = new Role();
        } else {
            role = roleMapper.find(Long.valueOf(id));
        }
        model.addAttribute("bean", role);

        List<Privilege> menus = privilegeMapper.findByRoleId(id);

        Map<String, Boolean> roleMenuMap = new HashMap<>();

        for (Privilege menu : menus) {
            if(menu == null){//bug,
                continue;
            }
            roleMenuMap.put(menu.getCode(), true);
        }

        Map<Object, String> typeMap = new HashMap<>();
        typeMap.put(PrivilegeType.MENU.getCode(), PrivilegeType.MENU.getMessage());
        typeMap.put(PrivilegeType.BUTTON.getCode(), PrivilegeType.BUTTON.getMessage());

        List<Privilege> menuList = privilegeService.findAll();

        model.addAttribute("menuList", menuList);
        model.addAttribute("roleMenuMap", roleMenuMap);
        model.addAttribute("typeMap", typeMap);

        return "role/edit";
    }

    /**
     * 保存角色
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("save")
    public ResultData save(Role role, @RequestParam(value = "privilege", required = false) String[] privileges) {
//        Group group = AdminUtil.getCurrentAdmin().getGroup();
//        role.setGroup(group);
        roleService.save(role, privileges);
        return ResultData.ok();
    }


    /**
     * 删除角色
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("delete")
    public ResultData delete(Long id) {
        roleService.delete(id);
        return ResultData.ok();
    }

}
