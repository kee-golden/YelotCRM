package com.yelot.crm.mapper;


import com.yelot.crm.entity.SendMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by kee on 16/12/16.
 */
@Mapper
@Repository
public interface SendMessageMapper extends BaseMapper<SendMessage>{

    //更新当天次数，和该电话的总次数，并把当前发送时间记录下来，需要判断过期时间
    void updateTimes(@Param("phone")String phone);

    SendMessage findByPhone(@Param("phone")String phone);

    Integer checkVerifyCode(@Param("phone")String phone,@Param("verifyCode")String verifyCode);
}
