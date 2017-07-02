package com.yelot.crm.service;


import com.yelot.crm.entity.Privilege;
import com.yelot.crm.entity.Role;
import com.yelot.crm.entity.RolePrivilegeRef;
import com.yelot.crm.mapper.PrivilegeMapper;
import com.yelot.crm.mapper.RoleMapper;
import com.yelot.crm.mapper.RolePrivilegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by feng- on 2016/4/5.
 */
@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePrivilegeMapper rolePrivilegeMapper;

    @Autowired
    private PrivilegeMapper privilegeMapper;


    /**
     * 色保存 刷
     *
     * @param role
     * @param privilege
     */
    public void save(Role role, String[] privilege) {
//        Group group = AdminUtil.getCurrentAdmin().getGroup();
        Role target;
        if (null == role.getId() || role.getId().equals("")) {
            target = role;
//            target.setId(IdWorker.nextId());
//            target.setGroup(group);
            roleMapper.insert(target);
        } else {
            target = roleMapper.find(role.getId());
            target.setCode(role.getCode());
            target.setName(role.getName());
//            target.setGroup(group);
            rolePrivilegeMapper.deleteByRoleId(role.getId());

        }

        if (privilege != null) {
            for (String privilegeId : privilege) {
                RolePrivilegeRef rolePrivilegeRef = new RolePrivilegeRef();
                Privilege p = new Privilege();
//                p.setId(privilegeId);
//                rolePrivilege.setId(IdWorker.nextId());
                rolePrivilegeRef.setPrivilege_id(Long.valueOf(privilegeId));
                rolePrivilegeRef.setRole_id(role.getId());

                rolePrivilegeMapper.insert(rolePrivilegeRef);
            }

        }

    }

    public void delete(Long id) {
        rolePrivilegeMapper.deleteByPrivilegeId(id);
        roleMapper.deleteAdminRoleByRoleId(id);
        roleMapper.delete(id);
    }
}
