package com.yelot.crm.controller;

import com.alibaba.fastjson.JSON;
import com.sun.tools.javac.main.Main;
import com.yelot.crm.Util.Constants;
import com.yelot.crm.Util.DateUtil;
import com.yelot.crm.Util.ResultData;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.*;
import com.yelot.crm.mapper.*;
import com.yelot.crm.service.ConsultOrderOperatorsService;
import com.yelot.crm.service.ConsultOrderService;
import com.yelot.crm.service.RepairOrderService;
import com.yelot.crm.vo.CityListVo;
import com.yelot.crm.vo.Table;
import org.apache.commons.lang3.StringUtils;
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
    private ConsultOrderMapper consultOrderMapper;

    @Autowired
    private RepairOrderService repairOrderService;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private ConsultOrderOperatorsService consultOrderOperatorsService;

    @Autowired
    private CategoryMapper categoryMapper;

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
        List<Shop> shopList = shopMapper.findAll();
        model.addAttribute("shopList",shopList);
        model.addAttribute("brandList",brandList);
        return "consult_order/consult_order_add";
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultData save(ConsultOrder consultOrder,String vistorDate){
        Date vistorAt = DateUtil.toDate(vistorDate, Constants.DateFormate);
        consultOrder.setVistorAt(vistorAt);
        consultOrderService.save(consultOrder);
        //同时生成日志
        consultOrderOperatorsService.save(consultOrder);

        return ResultData.ok();
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultData update(ConsultOrder consultOrder,String vistorDate){

        Date vistorAt = DateUtil.toDate(vistorDate, Constants.DateFormate);
        consultOrder.setVistorAt(vistorAt);
        consultOrderService.update(consultOrder);
        consultOrderOperatorsService.save(consultOrder);
        return ResultData.ok();
    }

    @RequestMapping("detail")
    public String detail(Model model,Long id){
        CityListVo cityListVo = repairOrderService.convertToCityListVo();
        String categoryJson = JSON.toJSONString(cityListVo);
        ConsultOrder consultOrder = consultOrderMapper.find(id);
        Brand brand = brandMapper.find(consultOrder.getBrandId());
        if(brand != null){
            consultOrder.setBrandName(brand.getName());
        }
        String imagesPath = consultOrder.getImagesPath();
        if(!StringUtils.isEmpty(imagesPath)){
            String images[] = consultOrder.getImagesPath().split(",");
            String imagesJson = JSON.toJSONString(images);
            model.addAttribute("imagesPath",consultOrder.getImagesPath());
            model.addAttribute("imagesJson",imagesJson);
        }else {
            model.addAttribute("imagesPath","");
            model.addAttribute("imagesJson","[]");
        }

        List<Brand> brandList = brandMapper.findAll();
        List<Shop> shopList = shopMapper.findAll();
        model.addAttribute("shopList",shopList);
        model.addAttribute("brandList",brandList);

        model.addAttribute("categoryJson",categoryJson);
        model.addAttribute("bean",consultOrder);

        return "consult_order/consult_order_detail";

    }

    @RequestMapping("logs")
    public String operatorLogs(Long orderId,Model model){
        List<ConsultOrderOperators> consultOrderOperatorsList = consultOrderOperatorsService.findByOrderId(orderId);
        model.addAttribute("consultOrderOperatorsList",consultOrderOperatorsList);
        return "consult_order/consult_order_logs";
    }

    @RequestMapping("to-check")
    public String toCheck(Long orderId,Model model){
        ConsultOrder consultOrder = consultOrderMapper.find(orderId);
        model.addAttribute("bean",consultOrder);
        return "consult_order/consult_order_check";
    }

    @RequestMapping("update-status")
    @ResponseBody
    public ResultData updateStatus(Long id,Long status){
        consultOrderMapper.updateStatus(id,status);
        return ResultData.ok();
    }

    @RequestMapping("check-phone")
    @ResponseBody
    public ResultData checkPhone(Long id){
        ConsultOrder consultOrder = consultOrderMapper.find(id);
        if(StringUtils.isEmpty(consultOrder.getCustomerPhone())){
            return ResultData.notFound();
        }
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
                            @RequestParam(value = "length", defaultValue = "15") int length) {

        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);

        int pageCount = consultOrderService.countTotalPageAll(extra_search);
        List<ConsultOrder> consultOrderList = consultOrderService.findByPageAll(extra_search, pageHelper);
        return new Table(pageCount, pageCount, consultOrderList);
    }
}
