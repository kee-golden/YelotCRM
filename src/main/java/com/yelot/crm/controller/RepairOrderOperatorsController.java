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
        RepairOrder repairOrder = repairOrderMapper.find(orderId);

        List<RepairOrderOperators> repairOrderOperatorsList = repairOrderOperatorsService.getRepairOrderOperators(orderId);
        model.addAttribute("repairOrderOperatorsList",repairOrderOperatorsList);
        model.addAttribute("orderNo",repairOrder.getOrderNo());
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
        model.addAttribute("bean",repairOrder);

        List<User> repairUserList = repairOrderOperatorsService.findRepairUserList();
        model.addAttribute("repairUserList", repairUserList);

        return "repair_order/repair_order_approve";

    }

    @RequestMapping("approve")
    @ResponseBody
	public ResultData approve(Model model, Long orderId, String comment,
			String imagesPath, Long repairUserId, String repairLastAt,
			Integer advancePayment, Integer labourPayment, 
			Integer materialPayment, Integer discountAmountPayment,
			String discountDesc,Integer nonPaymentType) {
        RepairOrder repairOrder = repairOrderMapper.find(orderId);
        RepairOrderOperators repairOrderOperators = new RepairOrderOperators();
        repairOrderOperators.setOrderNo(repairOrder.getOrderNo());
        repairOrderOperators.setOperator_status(OperatorStatus.APPROVE.getCode());
        repairOrderOperators.setCreateAt(new Date());
        repairOrderOperators.setApprove_user_id(UserUtil.getCurrentUser().getId());
        int orderStatus = repairOrder.getStatus();

        int approveStatus = getNextApproveStatus(orderStatus,repairOrder);
        
        Integer totalPayment = null;
        if(approveStatus == RepairOrderStatus.SHOP_EXPRESS_APPROVE.getCode()) {//36,在32状态下进行，数值计算
        	totalPayment = advancePayment + labourPayment + materialPayment - discountAmountPayment;
        }
        
        repairOrderMapper.updateOrderStatusAndImagesPath(orderId,approveStatus,imagesPath,repairUserId,repairLastAt,labourPayment,materialPayment,discountAmountPayment,totalPayment,discountDesc,nonPaymentType);
        repairOrderOperators.setRepair_order_id(orderId);
        repairOrderOperators.setOrder_status(approveStatus);
        repairOrderOperators.setOperator_comment(comment);
        repairOrderOperatorsService.save(repairOrderOperators);

        return ResultData.ok();
    }

    @RequestMapping("reject")
    @ResponseBody
    public ResultData reject(Model model,Long orderId,String comment,String imagesPath,Integer advancePayment, 
    		Integer labourPayment, Integer materialPayment, Integer discountAmountPayment,String discountDesc){
        RepairOrder repairOrder = repairOrderMapper.find(orderId);
        RepairOrderOperators repairOrderOperators = new RepairOrderOperators();
        repairOrderOperators.setOrderNo(repairOrder.getOrderNo());
        repairOrderOperators.setOperator_status(OperatorStatus.REJECT.getCode());
        repairOrderOperators.setCreateAt(new Date());
        repairOrderOperators.setApprove_user_id(UserUtil.getCurrentUser().getId());
        int orderStatus = repairOrder.getStatus();
        int rejectStatus = getNextRejectStatus(orderStatus,repairOrder);
        repairOrderMapper.updateOrderStatusAndImagesPath(orderId,rejectStatus,imagesPath,null,null,null,null,null,null,null,null);
        repairOrderOperators.setRepair_order_id(orderId);
        repairOrderOperators.setOrder_status(rejectStatus);
        repairOrderOperators.setOperator_comment(comment);
        repairOrderOperatorsService.save(repairOrderOperators);

        return ResultData.ok();
    }

    /**
     * 如果是评估单，需要增加两个状态，就是从check_approve--->SHOP_EVALUE_ORDER
     * @param orderStatus
     * @return
     */
    public int getNextApproveStatus(int orderStatus,RepairOrder repairOrder){
        if(repairOrder.getTypeName() != null && repairOrder.getTypeName().equals("评估单")){//要特殊流程
            if(orderStatus == RepairOrderStatus.SUBMIT.getCode()){//2->4->12->17->44->16
                return RepairOrderStatus.SHOP_MANAGE_APPROVE.getCode();
            }else if(orderStatus == RepairOrderStatus.SHOP_MANAGE_APPROVE.getCode()){//status=4
                return RepairOrderStatus.CENTER_APPROVE.getCode();
            }else if(orderStatus == RepairOrderStatus.CENTER_APPROVE.getCode()){//status=12   ---->17
                return RepairOrderStatus.CHECK_EVALUE_APPROVE.getCode();
            }else if(orderStatus == RepairOrderStatus.CHECK_EVALUE_APPROVE.getCode()) {//status = 17 --->44
                //主管审核后，订单就为维修单了
                repairOrderMapper.updateTypeName(repairOrder.getId(),"维修单");
                return RepairOrderStatus.SHOP_EVALUE_MANAGE_APPROVE.getCode();
            }

        }

        //是评估单转维修单的节点
        if(orderStatus == RepairOrderStatus.SHOP_EVALUE_MANAGE_APPROVE.getCode()){//status 44-->16
            return RepairOrderStatus.CHECK_APPROVE.getCode();
        }

        //普通维修单(2--4--12--16--20--24--28--32--36--40)
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
//            return RepairOrderStatus.SHOP_EXPRESS_APPROVE.getCode();
//        }else if(orderStatus == RepairOrderStatus.SHOP_EXPRESS_APPROVE.getCode()){
            return RepairOrderStatus.CUSTOMER_RECEIVE_APPROVE.getCode();
        }else if(orderStatus == RepairOrderStatus.SHOP_EXPRESS_APPROVE.getCode()){
            return RepairOrderStatus.CUSTOMER_RECEIVE_APPROVE.getCode();
        }

        return RepairOrderStatus.SUBMIT.getCode();

    }

    /**
     * 设置拒绝状态
     * @param orderStatus
     * @return
     */
    public int getNextRejectStatus(int orderStatus,RepairOrder repairOrder){
        if(orderStatus == RepairOrderStatus.SUBMIT.getCode()){//2
            return RepairOrderStatus.CANCEL.getCode();//第一次如果门店管理，可以拒绝订单，也就是取消订单
        }else if(orderStatus == RepairOrderStatus.SHOP_MANAGE_APPROVE.getCode()){//4
            return RepairOrderStatus.SUBMIT.getCode();//2
        }else if(orderStatus == RepairOrderStatus.CENTER_APPROVE.getCode()){//12
            return RepairOrderStatus.SHOP_MANAGE_APPROVE.getCode();
        }else if(orderStatus == RepairOrderStatus.CHECK_APPROVE.getCode()){//16
            return RepairOrderStatus.CENTER_APPROVE.getCode();
        }else if(orderStatus == RepairOrderStatus.QC_APPROVE.getCode()){
            return RepairOrderStatus.CHECK_APPROVE.getCode();
        }else if(orderStatus == RepairOrderStatus.CHECKIN_APPROVE.getCode()){
            return RepairOrderStatus.QC_APPROVE.getCode();
        }else if(orderStatus == RepairOrderStatus.CHECKOUT_APPROVE.getCode()){//
            return RepairOrderStatus.CHECKIN_APPROVE.getCode();
        }else if(orderStatus == RepairOrderStatus.SHOP_RECEIVE_APPROVE.getCode()){
            //当前状态，用户没有拒绝状态，只有返修件操作，当用户点击返修时，更改订单类型为"返修件"，并把状态修改到待预检状态
            repairOrderMapper.updateTypeName(repairOrder.getId(),"返修单");
            return RepairOrderStatus.CENTER_APPROVE.getCode();
        }

        return RepairOrderStatus.CANCEL.getCode();



    }



}
