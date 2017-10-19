package com.yelot.crm.mapper;

import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Account;
import com.yelot.crm.entity.Attribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    void updateFullName(@Param("openid")String openid,@Param("phone") String phone, @Param("fullName") String fullName);

    void updateEmail(@Param("openid")String openid,@Param("phone") String phone, @Param("email") String email);

    void updateCity(@Param("openid")String openid,@Param("phone") String phone, @Param("city") String city);

    int countBySearch(@Param("extra_search") String extra_search);

    List<Account> findBySearch(@Param("pageHelper") PageHelper pageHelper, @Param("extra_search") String extra_search);
    void updateInterest(@Param("openid")String openid,@Param("phone") String phone,@Param("interestJson") String interestJson);
}
