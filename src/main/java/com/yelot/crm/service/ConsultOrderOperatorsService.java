package com.yelot.crm.service;

import com.alibaba.fastjson.JSON;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.entity.ConsultOrder;
import com.yelot.crm.entity.ConsultOrderOperators;
import com.yelot.crm.entity.RepairOrder;
import com.yelot.crm.entity.RepairOrderOperators;
import com.yelot.crm.mapper.ConsultOrderOperatorsMapper;
import com.yelot.crm.mapper.RepairOrderMapper;
import com.yelot.crm.mapper.RepairOrderOperatorsMapper;
import com.yelot.crm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by kee on 17/8/28.
 */
@Service
public class ConsultOrderOperatorsService {

    @Autowired
    private ConsultOrderOperatorsMapper consultOrderOperatorsMapper;


    public void save(ConsultOrder consultOrder){
        ConsultOrderOperators consultOrderOperators = new ConsultOrderOperators();
        consultOrderOperators.setConsultOrderId(consultOrder.getId());
        consultOrderOperators.setUserId(UserUtil.getCurrentUser().getId());
        consultOrderOperators.setCreateAt(new Date());
        consultOrderOperators.setConsultOrderJson(JSON.toJSONString(consultOrder));
        consultOrderOperatorsMapper.save(consultOrderOperators);
    }

    public List<ConsultOrderOperators> findByOrderId(Long orderId) {
        return consultOrderOperatorsMapper.findByOrderId(orderId);
    }
}
