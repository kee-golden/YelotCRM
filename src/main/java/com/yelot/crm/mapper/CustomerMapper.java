package com.yelot.crm.mapper;

import com.yelot.crm.entity.Customer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kee on 17/5/28.
 */
@Mapper
@Repository
public interface CustomerMapper {

    @Select("select * from t_customer where id = #{id}")
    Customer find(Long id);

    @Insert("insert into t_customer(name,sex,phone,address,qq,email,comment,is_alive,create_at,update_at) values(#{name},#{sex}," +
            "#{phone},#{address},#{qq},#{email},#{comment},#{create_at},#{update_at})")
    void save(Customer customer);

    @Select("select * from t_customer limit #{start},#{size}")
    List<Customer> findByPage(@Param("start") Integer start, @Param("size") Integer size);

    @Update("update t_customer set name = #{name},phone=#{phone},address=#{address},sex=#{sex},email=#{email}," +
            "qq=#{qq},is_alive=#{is_alive},update_at=#{update_at} where id = #{id}")
    void update(Customer customer);


    @Select("select * from t_customer where phone = #{phone}")
    Customer findByPhone(String phone);

    @Update("update t_customer set is_alive = #{alive} where id = #{id}")
    void updateAlive(@Param("alive") Integer alive, @Param("id") Long id);
}
