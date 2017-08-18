package com.yelot.crm.controller;

import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.RepairOrderOperators;
import com.yelot.crm.entity.User;
import com.yelot.crm.mapper.RepairOrderMapper;
import com.yelot.crm.mapper.RepairOrderOperatorsMapper;
import com.yelot.crm.mapper.UserMapper;
import com.yelot.crm.vo.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kee on 17/8/19.
 */
@Controller
@RequestMapping("repair-order-operators")
public class RepairOrderOperatorsController {

    @Autowired
    private RepairOrderOperatorsMapper repairOrderOperatorsMapper;

    @Autowired
    private RepairOrderMapper repairOrderMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("workflow")
    public String workflow(Model model,Long orderId){

        List<RepairOrderOperators> repairOrderOperatorsList = repairOrderOperatorsMapper.findByOrderId(orderId);

        for (RepairOrderOperators repairOrderOperators:repairOrderOperatorsList) {
            Long repairOrderId = repairOrderOperators.getRepair_order_id();
            Long approveUserId = repairOrderOperators.getApprove_user_id();
            Long nextApproveUserId = repairOrderOperators.getNext_approve_user_id();

            String orderNo = repairOrderMapper.find(repairOrderId).getOrderNo();
            String approveUserName = userMapper.find(approveUserId).getName();
            String createUserName = userMapper.find(nextApproveUserId).getName();

            repairOrderOperators.setCreateUserName(createUserName);
            repairOrderOperators.setOrderNo(orderNo);
            repairOrderOperators.setApproveUserName(approveUserName);

        }
        model.addAttribute("repairOrderOperatorsList",repairOrderOperatorsList);

        return "repair_order/repair_order_workflow";

    }

}
