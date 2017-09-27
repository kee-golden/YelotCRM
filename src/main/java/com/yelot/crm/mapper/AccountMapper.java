package com.yelot.crm.mapper;

import com.yelot.crm.entity.Account;
import com.yelot.crm.entity.Attribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by kee on 17/8/8.
 */
@Mapper
@Repository
public interface AccountMapper {
    Account find(Long id);
    Account findByOpenId(String openId);

    void save(Account account);

    Integer findMaxId();

    void updateFullName(@Param("phone") String phone, @Param("fullName") String fullName);

    void updateEmail(@Param("phone") String phone, @Param("email") String email);

    void updateCity(@Param("phone") String phone, @Param("city") String city);
}
