package com.yelot.crm.mapper;

import com.yelot.crm.entity.RolePrivilegeRef;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by kee on 17/7/2.
 */
@Mapper
@Repository
public interface RolePrivilegeMapper extends BaseMapper<RolePrivilegeRef> {

    int deleteByRoleId(@Param("roleId") Long roleId);

    int deleteByPrivilegeId(@Param("privilegeId") Long privilegeId);
}
