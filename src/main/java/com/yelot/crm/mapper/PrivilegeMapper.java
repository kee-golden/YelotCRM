package com.yelot.crm.mapper;

import com.yelot.crm.entity.Privilege;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by kee on 17/6/30.
 */
@Mapper
@Repository
public interface PrivilegeMapper extends BaseMapper<Privilege> {
    List<Privilege> findParents();

    List<Privilege> findByParentId(@Param("parentId") Long parentId);

    List<Privilege> findByRoleId(@Param("roleId") String roleId);

    List<Privilege> findByAdminId(@Param("adminId") String adminId);

    Set<String> findCodeByAdminId(@Param("adminId") Long adminId);

    /**
     * 查找所有ids
     * @return
     */
    List<String> findIds();

    Set<String> findCodeByRoleId(@Param("roleId") String roleId);

    List<Privilege> findParentsByAdminId(@Param("adminId") String adminId);

    List<Privilege> findByParentIdAndAdminId(@Param("parentId") String parentId, @Param("adminId") String adminId);

    List<Privilege> findByParentIdAndAdminIdAndType(@Param("parentId") Long parentId, @Param("adminId") Long adminId, @Param("type") Integer type);

    Integer deleteByParentId(@Param("parentId") Long parentId);

    List<Privilege> findParentsByAdminIdAndType(@Param("adminId") Long userId,
                                                @Param("type") Integer type,@Param("roleType")Integer roleType);
}
