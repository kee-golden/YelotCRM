package com.yelot.crm.mapper;

import com.yelot.crm.entity.AccountConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by kee on 17/8/8.
 */
@Mapper
@Repository
public interface AccountConfigMapper {
    AccountConfig find(Long id);
    void update(AccountConfig accountConfig);
}
