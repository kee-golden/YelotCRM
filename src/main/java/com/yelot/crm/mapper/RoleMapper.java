package com.yelot.crm.mapper;

import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Role;
import com.yelot.crm.vo.RoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kee on 17/7/2.
 */
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role>{

    List<Role> findByMenuId(@Param("menuId") String menuId);

    List<Role> findByUserId(Long userId);

    Role findByRoleName(@Param("roleName")String roleName,@Param("groupId")String groupId);

//    Integer insertAdminRole(
//            @Param("id")String id,
//            @Param("adminId") String adminId,
//            @Param("roleId") String roleId);

    Integer deleteUserRoleByUserId(@Param("userId") Long userId);

    Integer deleteAdminRoleByRoleId(@Param("roleId") Long roleId);

    List<Role> findByGroupId(@Param("groupId")String groupId);

    Integer countTotalPage();

    List<Role> findByPage(@Param("pageHelper") PageHelper pageHelper);

    List<Role> findAll();

    List<RoleVo> findAllByUserId(Long userId);

    void insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
