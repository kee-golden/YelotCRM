package com.yelot.crm.controller;

import com.alibaba.fastjson.JSON;
import com.yelot.crm.Util.Constants;
import com.yelot.crm.Util.DateUtil;
import com.yelot.crm.Util.ResultData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Brand;
import com.yelot.crm.entity.ConsultOrder;
import com.yelot.crm.entity.RepairOrder;
import com.yelot.crm.mapper.BrandMapper;
import com.yelot.crm.service.ConsultOrderService;
import com.yelot.crm.service.RepairOrderService;
import com.yelot.crm.vo.CityListVo;
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
 * Created by kee on 17/8/22.
 */
@Controller
@RequestMapping("consult-order")
public class ConsultOrderController {
    @Autowired
    private ConsultOrderService consultOrderService;

    @Autowired
    private RepairOrderService repairOrderService;

    @Autowired
    private BrandMapper brandMapper;

    @RequestMapping("add")
    public String add(Model model){
        CityListVo cityListVo = repairOrderService.convertToCityListVo();
        String categoryJson = JSON.toJSONString(cityListVo);
        String firstCategory = cityListVo.getCitylist().get(0).getP();
        String secondCategory = cityListVo.getCitylist().get(0).getC().get(0).getN();
        model.addAttribute("categoryJson",categoryJson);
        model.addAttribute("firstCategory",firstCategory);
        model.addAttribute("secondCategory",secondCategory);
        List<Brand> brandList = brandMapper.findAll();
        model.addAttribute("brandList",brandList);
        return "consult_order/consult_order_add";
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultData save(ConsultOrder consultOrder,String vistorDate){
        Date vistorAt = DateUtil.toDate(vistorDate, Constants.DefaultDateFormate);
        consultOrder.setVistorAt(vistorAt);
        consultOrderService.save(consultOrder);
        return ResultData.ok();
    }

    @RequestMapping("alllist")
    public String alllist(){
        return "consult_order/consult_order_alllist";
    }

    /**
     * 订单分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping("query")
    public Table queryOrder(Model model,
                            @RequestParam(value = "extra_search", defaultValue = "")String extra_search,
                            @RequestParam(value = "start", defaultValue = "0") int start,
                            @RequestParam(value = "length", defaultValue = "10") int length) {

        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);

        int pageCount = consultOrderService.countTotalPageAll(extra_search);
        List<ConsultOrder> consultOrderList = consultOrderService.findByPageAll(extra_search, pageHelper);
        return new Table(pageCount, pageCount, consultOrderList);
    }
}