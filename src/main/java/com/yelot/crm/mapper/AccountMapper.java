package com.yelot.crm.mapper;

import com.yelot.crm.entity.Account;
import com.yelot.crm.entity.Attribute;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by kee on 17/8/8.
 */
@Mapper
@Repository
public interface AccountMapper {
    Account find(Long id);
}
