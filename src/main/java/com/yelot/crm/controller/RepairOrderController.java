package com.yelot.crm.controller;

import com.alibaba.fastjson.JSON;
import com.yelot.crm.Util.ResultData;
import com.yelot.crm.entity.Attribute;
import com.yelot.crm.entity.Brand;
import com.yelot.crm.entity.Category;
import com.yelot.crm.entity.CategoryServiceItem;
import com.yelot.crm.mapper.BrandMapper;
import com.yelot.crm.mapper.CategoryMapper;
import com.yelot.crm.mapper.CategoryServiceItemMapper;
import com.yelot.crm.service.CategoryAttributeService;
import com.yelot.crm.service.RepairOrderService;
import com.yelot.crm.vo.CityListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * {"citylist":[
 {"p":"前端技术","c":[{"n":"HTML"},{"n":"CSS"},{"n":"JAVASCIPT"}]},
 {"p":"编程语言","c":[{"n":"C"},{"n":"C++"},{"n":"Objective-C"},{"n":"PHP"},{"n":"JAVA"}]},
 {"p":"数据库","c":[{"n":"Mysql"},{"n":"SqlServer"},{"n":"Oracle"},{"n":"DB2"}]},
 ]}
 *
 * Created by kee on 17/7/13.
 */
@Controller
@RequestMapping("repair-order")
public class RepairOrderController {

    @Autowired
    private RepairOrderService repairOrderService;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryAttributeService categoryAttributeService;

    @Autowired
    private CategoryServiceItemMapper categoryServiceItemMapper;

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

        Category category = categoryMapper.findByName(firstCategory,secondCategory);

        List<CategoryServiceItem> categoryServiceItemList = categoryServiceItemMapper.findByCategoryId(category.getId());
        String categoryServiceJson = JSON.toJSONString(categoryServiceItemList);
        model.addAttribute("categoryServiceJson",categoryServiceJson);


        //获取品牌
        List<Brand> brandList = brandMapper.findAll();
        model.addAttribute("brandList",brandList);

        List<Attribute> attributeList = categoryAttributeService.findAttributes(firstCategory,secondCategory);
        String attibutesJson = JSON.toJSONString(attributeList);

        model.addAttribute("attributesJson",attibutesJson);

        return "repair_order/repair_order_add";
    }

    @ResponseBody
    @RequestMapping("get-attributes")
    public ResultData getAttributes(String firstCategory,String secondCategory){
        List<Attribute> attributeList = categoryAttributeService.findAttributes(firstCategory,secondCategory);

        Category category = categoryMapper.findByName(firstCategory,secondCategory);

        List<CategoryServiceItem> categoryServiceItemList = categoryServiceItemMapper.findByCategoryId(category.getId());

        ResultData resultData = ResultData.ok();
        resultData.putDataValue("attributeList",attributeList);
        resultData.putDataValue("categoryServiceItemList",categoryServiceItemList);
        return resultData;
    }

    @RequestMapping("list")
    public String list(){
        return "repair_order/repair_order_list";
    }
}
