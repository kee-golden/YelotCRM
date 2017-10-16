package com.yelot.crm.controller;

import com.yelot.crm.Util.Constants;
import com.yelot.crm.Util.DateUtil;
import com.yelot.crm.Util.ResultData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Customer;
import com.yelot.crm.entity.Express;
import com.yelot.crm.entity.RepairOrder;
import com.yelot.crm.entity.User;
import com.yelot.crm.mapper.ExpressMapper;
import com.yelot.crm.service.RepairOrderService;
import com.yelot.crm.vo.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by kee on 17/10/8.
 */
@Controller
@RequestMapping("express")
public class ExpressController {
    @Autowired
    private ExpressMapper expressMapper;
    
    @Autowired
    private RepairOrderService repairOrderService;

    @RequestMapping("index")
    public String index(){
        return "express/express_index";
    }

    @RequestMapping("list")
    public String list(){
        return "express/express_list";
    }

    @ResponseBody
    @RequestMapping("list-data")
    public Table userListData(Model model,
                              @RequestParam(value = "extra_search", defaultValue = "")String extra_search,
                              @RequestParam(value = "start", defaultValue = "0") int start,
                              @RequestParam(value = "length", defaultValue = "10") int length) {

        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);
        int pageCount = expressMapper.countBySearch(extra_search);
        List<Express> expressList = expressMapper.findBySearch(pageHelper,extra_search);

        model.addAttribute("expressList", expressList);

        return new Table(pageCount, pageCount, expressList);
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("add")
    public String add(Model model) {
        Express express = new Express();
        model.addAttribute("bean",express);
        return "express/express_edit";
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("edit")
    public String edit(Model model, Long id) {
        Express express = expressMapper.find(id);
        model.addAttribute("bean",express);

        return "express/express_edit";

    }

    @ResponseBody
    @RequestMapping("save")
    public ResultData save(Express express){
        if(express.getId() == null){
        	
        	Express express2 = expressMapper.findExpressByNameAndNo(express.getExpressName(), express.getExpressNo());
        	if (express2 != null) {
        		ResultData resultData = ResultData.errorRequest();
        		resultData.putDataValue("data", "该快递公司的快递单号已存在！");
        		return resultData;
			}
        	
            Date date = new Date();
            express.setCreateAt(date);
            express.setCreateUserId(UserUtil.getCurrentUser().getId());
            express.setShopId(UserUtil.getCurrentUser().getShop_id());
            express.setRepairOrderNoJson(express.getRepairOrderNoJson().replaceAll("，", ","));
            expressMapper.save(express);
            
            if (express.getExpressType() == 3) {
                express2 = expressMapper.findExpressByNameAndNo(express.getExpressName(), express.getExpressNo());
                express.setId(express2.getId());
                
                updateRepairOrderExpress(express);
			}
            
        }else{
        	
        	Express express2 = expressMapper.findExpressByNameAndNo(express.getExpressName(), express.getExpressNo());
        	if (!express2.getId().equals(express.getId())) {
        		ResultData resultData = ResultData.errorRequest();
        		resultData.putDataValue("data", "该快递公司的快递单号已存在！");
        		return resultData;
			}
        	
        	String[] repairOrderNoList = express2.getRepairOrderNoJson().split(",");
        	for (String str : repairOrderNoList) {
    			repairOrderService.updateExpressByOrderNo(str, null, null);
			}
        	
            express.setRepairOrderNoJson(express.getRepairOrderNoJson().replaceAll("，", ","));
            expressMapper.update(express);
            
            if (express.getExpressType() == 3) {
            	updateRepairOrderExpress(express);
			}
        }

        ResultData resultData = ResultData.ok();
        resultData.putDataValue("bean",express);
        return resultData;

    }

    @ResponseBody
    @RequestMapping("delete")
    public ResultData delete(Long id){
        Express express = expressMapper.find(id);

    	String[] repairOrderNoList = express.getRepairOrderNoJson().split(",");
    	for (String str : repairOrderNoList) {
			repairOrderService.updateExpressByOrderNo(str, null, null);
		}
    	
        expressMapper.delete(id);
        return ResultData.ok();

    }
    
    private void updateRepairOrderExpress(Express express){
    	Long expressId = express.getId();
    	String[] repairOrderNoList = express.getRepairOrderNoJson().split(",");
    	int payAmount = express.getPayAmount();
    	int total = repairOrderNoList.length;
    	int expressMoney = 0;

    	for (int i = 0; i < repairOrderNoList.length; i++) {
    		payAmount = payAmount - expressMoney;
    		expressMoney = Math.round(payAmount / total);
    		total--;
			repairOrderService.updateExpressByOrderNo(repairOrderNoList[i], expressId, expressMoney);
    	}
    }

}
