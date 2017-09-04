package com.yelot.crm.controller;

import com.alibaba.fastjson.JSON;
import com.yelot.crm.Util.DateUtil;
import com.yelot.crm.Util.ResultData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.*;
import com.yelot.crm.mapper.*;
import com.yelot.crm.service.CategoryAttributeService;
import com.yelot.crm.service.RepairOrderService;
import com.yelot.crm.vo.CityListVo;
import com.yelot.crm.vo.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleStatusMapper roleStatusMapper;

    /**
     * 调整到新建页面
     * @param model
     * @return
     */
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


    /**
     *
     *
     * customerId: customerId,
     firstCategory: firstCategory,
     secondeCategory: secondeCategory,
     valuesAttributeJson:valuesAttributeJson,
     serviceItemJson:serviceItemJson,
     imagePaths:imagePaths,
     imageDesc:imageDesc,
     repairDesc:repairDesc,
     pickupDate:pickupDate
     * @return
     */

    @ResponseBody
    @RequestMapping("save")
    public ResultData save(Long customerId,String firstCategory,String secondCategory,String valuesAttributeJson,
                           String serviceItemJson,String imagePaths,String imageDesc,String repairDesc,String pickupDate){
        RepairOrder repairOrder = new RepairOrder();

        User user = UserUtil.getCurrentUser();
        repairOrder.setCreateUserId(user.getId());
        repairOrder.setShopId(user.getShop_id());

        Category category = categoryMapper.findByName(firstCategory,secondCategory);

        repairOrder.setFirstCategoryId(category.getParentId());
        repairOrder.setSecondCategoryId(category.getId());
        Customer customer = customerMapper.find(customerId);
        repairOrder.setCustomerId(customerId);
        repairOrder.setCustomerName(customer.getName());
        repairOrder.setCustomerAddress(customer.getAddress());
        repairOrder.setCustomerPhone(customer.getPhone());
        repairOrder.setProductInfoJson(valuesAttributeJson);
        repairOrder.setServiceItemIds(serviceItemJson);
        repairOrder.setImagesJson(imagePaths);
        repairOrder.setImageDesc(imageDesc);
        repairOrder.setRepairDesc(repairDesc);
        repairOrder.setPickupAt(DateUtil.toDate(pickupDate,"yyyy-MM-dd"));
        Date now = new Date();
        repairOrder.setCreateAt(now);
        repairOrder.setUpdateAt(now);

        String orderNo = user.getShop_id()+DateUtil.toString(now,"yyyyMMddHHMMSS");
        repairOrder.setOrderNo(orderNo);

        repairOrder.setStatus(2);//submit

        repairOrderService.save(repairOrder);

        return ResultData.ok();

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

    @RequestMapping("mylist")
    public String mylist(){
        return "repair_order/repair_order_mylist";
    }

    @RequestMapping("checklist")
    public String checklist(){
        return "repair_order/repair_order_checklist";
    }

    @RequestMapping("alllist")
    public String alllist(){
        return "repair_order/repair_order_alllist";
    }

    /**
     * 订单分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping("query")
    public Table queryOrder(Model model,
        @RequestParam(value = "type", defaultValue = "")String type,
        @RequestParam(value = "extra_search", defaultValue = "")String extra_search,
        @RequestParam(value = "start", defaultValue = "0") int start,
        @RequestParam(value = "length", defaultValue = "10") int length) {
    	
        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);
        
        Long userId = UserUtil.getCurrentUser().getId();
        int pageCount = repairOrderService.countTotalPageMy(extra_search, type, userId);
        List<RepairOrder> repairOrderList = repairOrderService.findByPageMy(extra_search, type, userId, pageHelper);
        return new Table(pageCount, pageCount, repairOrderList);
    }

    /**
     * 审核订单,先查询角色，并查询角色对应的订单状态值
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("check-query")
    public Table queryCheckOrder(Model model,
                                 @RequestParam(value = "extra_search", defaultValue = "")String extra_search,
                                 @RequestParam(value = "start", defaultValue = "0") int start,
                                 @RequestParam(value = "length", defaultValue = "10") int length){
        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);
        User user = UserUtil.getCurrentUser();
        Long userId = user.getId();
        List<Role> roleList = roleMapper.findByUserId(userId);
        List<String> statusList = new ArrayList<String>();
        for (int i = 0; roleList != null && i < roleList.size(); i++) {
            Long roleId = roleList.get(i).getId();
            String []subStatus = roleStatusMapper.find(roleId).getStatus().split(",");
            for (int j = 0; subStatus != null && j < subStatus.length; j++) {
                if(statusList.contains(subStatus[j])){
                    continue;
                }
                statusList.add(subStatus[j]);
            }

        }

        String statusTemp = "";
        for (int i = 0; i < statusList.size(); i++) {
            statusTemp += statusList.get(i)+",";
        }
        //去除最后一个分隔符"，"
        String statusString = "";
        if(statusTemp.length() >= 2){
            statusString = statusTemp.substring(0,statusTemp.lastIndexOf(","));
        }

        //根据客服主管仅仅能查看自己门店的订单，需要特殊处理一下。
        if(statusList.contains("2")){//状态为2，客服主管审核状态，必须审核本门店的订单
            int pageCount = repairOrderService.countTotalPageCheckListAndShop(extra_search, statusString,user.getShop_id());
            List<RepairOrder> repairOrderList = repairOrderService.findByPageCheckListAndShop(extra_search,statusString,pageHelper,user.getShop_id());
            return new Table(pageCount, pageCount, repairOrderList);
        }


        int pageCount = repairOrderService.countTotalPageCheckList(extra_search, statusString);
        List<RepairOrder> repairOrderList = repairOrderService.findByPageCheckList(extra_search,statusString,pageHelper);

        return new Table(pageCount, pageCount, repairOrderList);

    }

    /**
     * 根据订单id查看订单详情
     * @param model
     * @param orderId
     * @return
     */
    @RequestMapping("detail")
    public String findRepairOrderByOrderId(Model model, Long orderId){
        RepairOrder repairOrder = repairOrderService.findRepairOrderByOrderId(orderId);
        model.addAttribute("repairOrder", repairOrder);
        return "repair_order/repair_order_detail";
    }

}
