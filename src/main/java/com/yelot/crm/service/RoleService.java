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
 * 维修中心角色：1）收货分练，2）预检确认 3）QC质检 4）货品入库 5）货品出库。
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
            rolePrivilegeMapper.deleteByRoleId(role.getId());
            roleMapper.update(role);

        }

        if (privilege != null) {
            for (String privilegeId : privilege) {
                RolePrivilegeRef rolePrivilegeRef = new RolePrivilegeRef();
                Privilege p = new Privilege();
//                p.setId(privilegeId);
//                rolePrivilege.setId(IdWorker.nextId());
                rolePrivilegeRef.setPrivilegeId(Long.valueOf(privilegeId));
                rolePrivilegeRef.setRoleId(role.getId());

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
