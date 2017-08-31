package com.yelot.crm.controller;

import com.yelot.crm.Util.ResultData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.RepairOrder;
import com.yelot.crm.entity.RepairOrderOperators;
import com.yelot.crm.entity.User;
import com.yelot.crm.enums.OperatorStatus;
import com.yelot.crm.enums.RepairOrderStatus;
import com.yelot.crm.mapper.RepairOrderMapper;
import com.yelot.crm.mapper.RepairOrderOperatorsMapper;
import com.yelot.crm.mapper.UserMapper;
import com.yelot.crm.service.RepairOrderOperatorsService;
import com.yelot.crm.vo.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by kee on 17/8/19.
 */
@Controller
@RequestMapping("repair-order-operators")
public class RepairOrderOperatorsController {

    @Autowired
    private RepairOrderOperatorsService repairOrderOperatorsService;

    @Autowired
    private RepairOrderMapper repairOrderMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("workflow")
    public String workflow(Model model,Long orderId){

        List<RepairOrderOperators> repairOrderOperatorsList = repairOrderOperatorsService.getRepairOrderOperators(orderId);
        model.addAttribute("repairOrderOperatorsList",repairOrderOperatorsList);

        return "repair_order/repair_order_workflow";

    }

    @RequestMapping("to-approve")
    public String toApprove(Model model,Long orderId){
        List<RepairOrderOperators> repairOrderOperatorsList = repairOrderOperatorsService.getRepairOrderOperators(orderId);
        RepairOrder repairOrder = repairOrderMapper.find(orderId);
        model.addAttribute("repairOrderOperatorsList",repairOrderOperatorsList);
        model.addAttribute("orderId",orderId);
        model.addAttribute("orderNo",repairOrder.getOrderNo());

        model.addAttribute("orderStatus",repairOrder.getStatus());

        return "repair_order/repair_order_approve";

    }

    @RequestMapping("approve")
    @ResponseBody
    public ResultData approve(Model model,Long orderId,String comment,String imagesPath){
        RepairOrder repairOrder = repairOrderMapper.find(orderId);
        RepairOrderOperators repairOrderOperators = new RepairOrderOperators();
        repairOrderOperators.setOrderNo(repairOrder.getOrderNo());
        repairOrderOperators.setOperator_status(OperatorStatus.APPROVE.getCode());
        repairOrderOperators.setCreateAt(new Date());
        repairOrderOperators.setApprove_user_id(UserUtil.getCurrentUser().getId());
        int orderStatus = repairOrder.getStatus();
        int approveStatus = getNextApproveStatus(orderStatus);
        repairOrderMapper.updateOrderStatusAndImagesPath(orderId,approveStatus,imagesPath);
        repairOrderOperators.setRepair_order_id(orderId);
        repairOrderOperators.setOrder_status(approveStatus);
        repairOrderOperators.setOperator_comment(comment);
        repairOrderOperatorsService.save(repairOrderOperators);

        return ResultData.ok();
    }

    @RequestMapping("reject")
    @ResponseBody
    public ResultData reject(Model model,Long orderId,String comment,String imagesPath){
        RepairOrder repairOrder = repairOrderMapper.find(orderId);
        RepairOrderOperators repairOrderOperators = new RepairOrderOperators();
        repairOrderOperators.setOrderNo(repairOrder.getOrderNo());
        repairOrderOperators.setOperator_status(OperatorStatus.REJECT.getCode());
        repairOrderOperators.setCreateAt(new Date());
        repairOrderOperators.setApprove_user_id(UserUtil.getCurrentUser().getId());
        int orderStatus = repairOrder.getStatus();
        int rejectStatus = getNextRejectStatus(orderStatus);
        repairOrderMapper.updateOrderStatusAndImagesPath(orderId,rejectStatus,imagesPath);
        repairOrderOperators.setRepair_order_id(orderId);
        repairOrderOperators.setOrder_status(rejectStatus);
        repairOrderOperators.setOperator_comment(comment);
        repairOrderOperatorsService.save(repairOrderOperators);

        return ResultData.ok();
    }

    /**
     *
     * @param orderStatus
     * @return
     */
    public int getNextApproveStatus(int orderStatus){
        if(orderStatus == RepairOrderStatus.SUBMIT.getCode()){
            return RepairOrderStatus.SHOP_MANAGE_APPROVE.getCode();
        }else if(orderStatus == RepairOrderStatus.SHOP_MANAGE_APPROVE.getCode()){
            return RepairOrderStatus.CENTER_APPROVE.getCode();
        }else if(orderStatus == RepairOrderStatus.CENTER_APPROVE.getCode()){
            return RepairOrderStatus.CHECK_APPROVE.getCode();
        }else if(orderStatus == RepairOrderStatus.CHECK_APPROVE.getCode()){
            return RepairOrderStatus.QC_APPROVE.getCode();
        }else if(orderStatus == RepairOrderStatus.QC_APPROVE.getCode()){
            return RepairOrderStatus.CHECKIN_APPROVE.getCode();
        }else if(orderStatus == RepairOrderStatus.CHECKIN_APPROVE.getCode()){
            return RepairOrderStatus.CHECKOUT_APPROVE.getCode();
        }else if(orderStatus == RepairOrderStatus.CHECKOUT_APPROVE.getCode()){
            return RepairOrderStatus.SHOP_RECEIVE_APPROVE.getCode();
        }else if(orderStatus == RepairOrderStatus.SHOP_RECEIVE_APPROVE.getCode()){
            return RepairOrderStatus.CUSTOMER_RECEIVE_APPROVE.getCode();
        }

        return RepairOrderStatus.SUBMIT.getCode();

    }

    public int getNextRejectStatus(int orderStatus){
        if(orderStatus == RepairOrderStatus.SUBMIT.getCode()){
            return RepairOrderStatus.SHOP_MANAGE_REJECT.getCode();
        }else if(orderStatus == RepairOrderStatus.SHOP_MANAGE_APPROVE.getCode()){
            return RepairOrderStatus.CENTER_REJECT.getCode();
        }else if(orderStatus == RepairOrderStatus.CENTER_APPROVE.getCode()){
            return RepairOrderStatus.CHECK_REJECT.getCode();
        }else if(orderStatus == RepairOrderStatus.CHECK_APPROVE.getCode()){
            return RepairOrderStatus.QC_REJECT.getCode();
        }else if(orderStatus == RepairOrderStatus.QC_APPROVE.getCode()){
            return RepairOrderStatus.CHECKIN_REJECT.getCode();
        }else if(orderStatus == RepairOrderStatus.CHECKIN_APPROVE.getCode()){
            return RepairOrderStatus.CHECKOUT_REJECT.getCode();
        }else if(orderStatus == RepairOrderStatus.CHECKOUT_APPROVE.getCode()){
            return RepairOrderStatus.SHOP_RECEIVE_REJECT.getCode();
        }else if(orderStatus == RepairOrderStatus.SHOP_RECEIVE_APPROVE.getCode()){
            return RepairOrderStatus.CUSTOMER_RECEIVE_REJECT.getCode();
        }

        return RepairOrderStatus.CANCEL.getCode();



    }



}
