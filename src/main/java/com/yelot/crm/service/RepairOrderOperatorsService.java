package com.yelot.crm.service;

import com.yelot.crm.entity.RepairOrder;
import com.yelot.crm.entity.RepairOrderOperators;
import com.yelot.crm.mapper.RepairOrderMapper;
import com.yelot.crm.mapper.RepairOrderOperatorsMapper;
import com.yelot.crm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kee on 17/8/28.
 */
@Service
public class RepairOrderOperatorsService {

    @Autowired
    private RepairOrderOperatorsMapper repairOrderOperatorsMapper;

    @Autowired
    private RepairOrderMapper repairOrderMapper;
    @Autowired
    private UserMapper userMapper;

    public List<RepairOrderOperators> getRepairOrderOperators(Long orderId){
        List<RepairOrderOperators> repairOrderOperatorsList = repairOrderOperatorsMapper.findByOrderId(orderId);

        for (RepairOrderOperators repairOrderOperators:repairOrderOperatorsList) {
            Long repairOrderId = repairOrderOperators.getRepair_order_id();
            Long approveUserId = repairOrderOperators.getApprove_user_id();

            RepairOrder repairOrder = repairOrderMapper.find(repairOrderId);
            String approveUserName = userMapper.find(approveUserId).getName();
            repairOrderOperators.setOrderNo(repairOrder.getOrderNo());
            repairOrderOperators.setOrder_status(repairOrder.getStatus());
            repairOrderOperators.setApproveUserName(approveUserName);

        }

        return repairOrderOperatorsList;
    }

    public void save(RepairOrderOperators repairOrderOperators){
        repairOrderOperatorsMapper.save(repairOrderOperators);
    }
}
