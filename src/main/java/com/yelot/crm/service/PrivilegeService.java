package com.yelot.crm.service;

import com.yelot.crm.entity.Privilege;
import com.yelot.crm.mapper.PrivilegeMapper;
import com.yelot.crm.mapper.RolePrivilegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by feng-er on 2016/4/5.
 */
@Service
public class PrivilegeService {


    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Autowired
    private RolePrivilegeMapper rolePrivilegeMapper;


    /**
     * 获取全部菜单树
     *
     * @return
     */
    public List<Privilege> findAll() {
        List<Privilege> parents = privilegeMapper.findParents();
        for (Privilege parent : parents) {
            List<Privilege> children = findChildren(parent.getId());
            parent.setChildren(children);
        }
        return parents;
    }

    /**
     * 获取子菜单
     *
     * @param parentId
     * @return
     */
    public List<Privilege> findChildren(Long parentId) {

        List<Privilege> menus = privilegeMapper.findByParentId(parentId);
        for (Privilege menu : menus) {
            List<Privilege> children = findChildren(menu.getId());
            menu.setChildren(children);
        }

        return menus;
    }
    

    /**
     * 删除菜单,同时删除关联角色,子菜单
     *
     * @param id
     */
    public Boolean delete(Long id) {
        List<Privilege> menus = privilegeMapper.findByParentId(id);


        if (menus.size() > 0) {
            return false;
        }

        int i = rolePrivilegeMapper.deleteByPrivilegeId(id);


        for (Privilege menu : menus) {
            rolePrivilegeMapper.deleteByPrivilegeId(menu.getId());//// TODO: 9/23/16 删除算法需要优化
        }

        privilegeMapper.deleteByParentId(id);

        privilegeMapper.delete(id);
        return true;
    }


}
