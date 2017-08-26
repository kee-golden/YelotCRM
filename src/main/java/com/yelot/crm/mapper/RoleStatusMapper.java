package com.yelot.crm.mapper;

import com.yelot.crm.entity.RoleStatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by kee on 17/8/8.
 */
@Mapper
@Repository
public interface RoleStatusMapper {

    RoleStatus find(Long id);

//    RoleStatus findByName(String processName);
}
