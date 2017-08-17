package com.yelot.crm.mapper;

import com.yelot.crm.entity.RepairOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by kee on 17/6/5.
 */
@Mapper
@Repository
public interface RepairOrderMapper {

    RepairOrder find(Long id);

//    @Insert("insert into t_repair_order(order_no,status,create_user_id,last_approve_user_id,approve_user_id,delivery_at," +
//            "delivery_type,customer_id,customer_name,customer_phone,customer_phone_second,customer_address," +
//            "advance_payment,non_payment,advance_payment_type,non_payment_type,total_payment,shop_id,create_at,update_at)" +
//            " values(#{order_no},#{status},#{create_user_id},#{last_approve_user_id},#{approve_user_id},#{delivery_at},#{delivery_type}," +
//            "#{customer_id},#{customer_name},#{customer_phone},#{customer_phone_second},#{customer_address},#{advance_payment}," +
//            "#{non_payment},#{advance_payment_type},#{non_payment_type},#{total_payment},#{shop_id},#{create_at},#{update_at})")
    void save(RepairOrder repairOrder);


    @Select("select * from t_repare_order where order_no = #{order_no}")
    RepairOrder findByOrderNo(String order_no);
}
