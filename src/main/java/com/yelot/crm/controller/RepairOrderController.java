package com.yelot.crm.controller;

import com.alibaba.fastjson.JSON;
import com.yelot.crm.Util.DateUtil;
import com.yelot.crm.Util.ResultData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.*;
import com.yelot.crm.enums.RepairOrderStatus;
import com.yelot.crm.mapper.*;
import com.yelot.crm.service.CategoryAttributeService;
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
    private RepairOrderMapper repairOrderMapper;

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
    
    @Autowired
    private ConsultOrderService consultOrderService;
    
    @Autowired
    private ConsultOrderMapper consultOrderMapper;
    
    @Autowired
    private ShopMapper shopMapper;

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
    public ResultData save(Long customerId,Long consultOrderId,String firstCategory,String secondCategory,String brandName,String valuesAttributeJson,
                           String serviceItemJson,String refOrderIdsJson,String imagePaths,String imageDesc,String repairDesc,String typeName,
                           @RequestParam(value = "advancePayment",defaultValue = "0") Integer advancePayment,
                           @RequestParam(value = "labourPayment",defaultValue = "0")Integer labourPayment,
                           @RequestParam(value = "materialPayment",defaultValue = "0")Integer materialPayment,
                           String pickupDate, String discountDesc,String materialDesc){
        RepairOrder repairOrder = new RepairOrder();

        User user = UserUtil.getCurrentUser();
        repairOrder.setCreateUserId(user.getId());
        repairOrder.setShopId(user.getShop_id());

        Category category = categoryMapper.findByName(firstCategory,secondCategory);
        Brand brand = brandMapper.findByName(brandName);
        Brand newBrand = null;
        if(brand == null){
            newBrand = new Brand();
            newBrand.setName(brandName);
            brandMapper.save(newBrand);
        }

        repairOrder.setConsultOrderId(consultOrderId);
        repairOrder.setFirstCategoryId(category.getParentId());
        repairOrder.setSecondCategoryId(category.getId());
        if(brand == null){
            repairOrder.setBrandId(newBrand.getId());
        }else{
            repairOrder.setBrandId(brand.getId());
        }
        Customer customer = customerMapper.find(customerId);
        repairOrder.setCustomerId(customerId);
        repairOrder.setCustomerName(customer.getName());
        repairOrder.setCustomerAddress(customer.getAddress());
        repairOrder.setCustomerPhone(customer.getPhone());
        repairOrder.setCustomerPhoneSecond(customer.getOtherPhone());
        repairOrder.setProductInfoJson(valuesAttributeJson);
        repairOrder.setServiceItemIds(serviceItemJson);
        repairOrder.setRefOrderIds(refOrderIdsJson);
        repairOrder.setImagesJson(imagePaths);
        repairOrder.setImageDesc(imageDesc);
        repairOrder.setRepairDesc(repairDesc);
        repairOrder.setTypeName(typeName);
        repairOrder.setLabourPayment(labourPayment);
        repairOrder.setAdvancePayment(advancePayment);
        repairOrder.setMaterialPayment(materialPayment);
        repairOrder.setPickupAt(DateUtil.toDate(pickupDate,"yyyy-MM-dd"));
        Date now = new Date();
        repairOrder.setCreateAt(now);
        repairOrder.setUpdateAt(now);
        repairOrder.setDiscountDesc(discountDesc);
        repairOrder.setMaterialDesc(materialDesc);
        repairOrder.setChannelSource(customer.getChannelSource());

        //订单号生成规则：门店编号+年月日+流水序号（门店当天第几单），第一单从11开始，年只保留后面2位
        String orderNo = user.getShop_id()+DateUtil.toString(now,"yyMMdd");
        int orderIndex = repairOrderService.countByShopId(user.getShop_id()) + 11;
        repairOrder.setOrderNo(orderNo+orderIndex);

        repairOrder.setStatus(2);//submit

       // repairOrderService.save(repairOrder);
        
        if (refOrderIdsJson != null && !"null".equals(refOrderIdsJson) && !"".equals(refOrderIdsJson)) {
        	repairOrderService.updateRefOrderIdsByOrderNo(repairOrder.getOrderNo(), refOrderIdsJson);
		}
        
        consultOrderMapper.updateStatus(consultOrderId, 3); // 咨询单已接单

        return ResultData.ok();

    }

    @RequestMapping("update")
    @ResponseBody
    public ResultData update(Long id,Long customerId,String firstCategory,String secondCategory,Long brandId,String valuesAttributeJson,
                             String serviceItemJson,String imagePaths,String imageDesc,String repairDesc,
                             String typeName,
                             @RequestParam(value = "advancePayment",defaultValue = "0") Integer advancePayment,
                             @RequestParam(value = "labourPayment",defaultValue = "0")Integer labourPayment,
                             @RequestParam(value = "materialPayment",defaultValue = "0")Integer materialPayment
            ,String pickupDate){

        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setId(id);

        Category category = categoryMapper.findByName(firstCategory,secondCategory);

        repairOrder.setFirstCategoryId(category.getParentId());
        repairOrder.setSecondCategoryId(category.getId());
        repairOrder.setBrandId(brandId);
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
        repairOrder.setTypeName(typeName);
        repairOrder.setLabourPayment(labourPayment);
        repairOrder.setAdvancePayment(advancePayment);
        repairOrder.setMaterialPayment(materialPayment);
        repairOrder.setPickupAt(DateUtil.toDate(pickupDate,"yyyy-MM-dd"));

        repairOrderService.update(repairOrder);
        return ResultData.ok();
    }

    @ResponseBody
    @RequestMapping("get-attributes")
    public ResultData getAttributes(Long id,String firstCategory,String secondCategory,
                                    @RequestParam(value = "isEdit",defaultValue = "false")boolean isEdit){

        RepairOrder repairOrder = repairOrderMapper.find(id);
        String myFirstCategory = firstCategory;
        String mySecondCategory = secondCategory;
        if(isEdit){
             myFirstCategory = categoryMapper.find(repairOrder.getFirstCategoryId()).getName();
             mySecondCategory = categoryMapper.find(repairOrder.getSecondCategoryId()).getName();
        }

        List<Attribute> attributeList = categoryAttributeService.findAttributes(firstCategory,secondCategory);

        Category category = categoryMapper.findByName(firstCategory,secondCategory);

        List<CategoryServiceItem> categoryServiceItemList = categoryServiceItemMapper.findByCategoryId(category.getId());

        if(isEdit && myFirstCategory.equals(firstCategory) && mySecondCategory.equals(secondCategory)){//如果是当前曾经选择的分类，需要初始化原有数据的值
            initAttributeItem(attributeList,repairOrder);
            initServiceItem(categoryServiceItemList,repairOrder);
        }


        ResultData resultData = ResultData.ok();
        resultData.putDataValue("attributeList",attributeList);
        resultData.putDataValue("categoryServiceItemList",categoryServiceItemList);
        return resultData;
    }

    @RequestMapping("mylist")
    public String mylist(){
        return "repair_order/repair_order_mylist";
    }

    @RequestMapping("alllist")
    public String alllist(){
        return "repair_order/repair_order_alllist";
    }

    @RequestMapping("checklist")
    public String checklist(Model model){
    	List<Role> roleList = roleMapper.findByUserId(UserUtil.getCurrentUser().getId());
    	Boolean isShow = false;
    	for (Role role : roleList) {
			if ("客服主管".equals(role.getName())) {
				isShow = true;
			}
		}
        model.addAttribute("isShow", isShow);
        return "repair_order/repair_order_checklist";
    }

    @RequestMapping("warnlist")
    public String warnlist(){
        return "repair_order/repair_order_warnlist";
    }

    @RequestMapping("centerAlllist")
    public String centerAlllist(){
        return "repair_order/repair_order_centerAlllist";
    }

    @RequestMapping("centerChecklist")
    public String centerChecklist(){
        return "repair_order/repair_order_centerChecklist";
    }

    @RequestMapping("centerWarnlist")
    public String centerWarnlist(){
        return "repair_order/repair_order_centerWarnlist";
    }

    @RequestMapping("inLibrarylist")
    public String inLibrarylist(){
        return "repair_order/repair_order_inLibrarylist";
    }

    /**
     * 订单分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping("query")
    public Table queryOrder(Model model,
        @RequestParam(value = "extra_search", defaultValue = "")String extra_search,
        @RequestParam(value = "type", defaultValue = "")String type,
        @RequestParam(value = "start", defaultValue = "0") int start,
        @RequestParam(value = "length", defaultValue = "10") int length) {
    	
        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);
        
        Long userId = UserUtil.getCurrentUser().getId();
        int pageCount = repairOrderService.countTotalPage(extra_search, type, userId);
        List<RepairOrder> repairOrderList = repairOrderService.findByPage(extra_search, type, userId, pageHelper);
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
                                 @RequestParam(value = "type", defaultValue = "")String type,
                                 @RequestParam(value = "direction", defaultValue = "")String direction,
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
            List<RoleStatus> roleStatusList = roleStatusMapper.findByRoleId(roleId);
            for (RoleStatus roleStatus:roleStatusList) {
                String subStatusArray[] = roleStatus.getStatus().split(",");
                for (String tempStatus: subStatusArray) {
                    if (statusList.contains(tempStatus)){
                        continue;
                    }
                    statusList.add(tempStatus);
                }

            }
        }

        //根据客服主管仅仅能查看自己门店的订单，需要特殊处理一下。
        //角色设置的时候，不能让一个人，同时具有门店权限和维修中心权限
        if(statusList.contains(RepairOrderStatus.SUBMIT.getCode()+"") 
        		|| statusList.contains(RepairOrderStatus.CHECKOUT_APPROVE.getCode()+"")
                || statusList.contains(RepairOrderStatus.CHECK_EVALUE_APPROVE.getCode()+"")){//状态为2，客服主管审核状态，必须审核本门店的订单

            List<String> lastStatusList = new ArrayList<String>();
        	if("toCenter".equals(direction)){
        		lastStatusList.add(RepairOrderStatus.SUBMIT.getCode()+"");
        		lastStatusList.add(RepairOrderStatus.CHECK_EVALUE_APPROVE.getCode()+"");
        	} else {
        		lastStatusList.add(RepairOrderStatus.CHECKOUT_APPROVE.getCode()+"");
        	}
            int pageCount = repairOrderService.countTotalPageCheckListAndShop(extra_search, lastStatusList, user.getShop_id(), type);
            List<RepairOrder> repairOrderList = repairOrderService.findByPageCheckListAndShop(extra_search,lastStatusList,pageHelper,user.getShop_id(),type);
            return new Table(pageCount, pageCount, repairOrderList);
        }


        int pageCount = repairOrderService.countTotalPageCheckList(extra_search, statusList, type);
        List<RepairOrder> repairOrderList = repairOrderService.findByPageCheckList(extra_search,statusList,pageHelper,type);

        return new Table(pageCount, pageCount, repairOrderList);

    }

    /**
     * 根据订单id查看订单详情
     * @param model
     * @param orderId
     * @return
     */
    @RequestMapping("detail")
    public String findRepairOrderByOrderId(Model model, Long orderId, Boolean customerVisable){
        RepairOrder repairOrder = repairOrderService.findRepairOrderByOrderId(orderId);
        model.addAttribute("repairOrder", repairOrder);
        model.addAttribute("customerVisable", customerVisable);
        return "repair_order/repair_order_detail";
    }

    /**
     * 根据订单号查看订单详情
     * @param model
     * @param orderNo
     * @return
     */
    @RequestMapping("detailByOrderNo")
    public String findRepairOrderByOrderNo(Model model, String orderNo, Boolean customerVisable){
        RepairOrder repairOrder = repairOrderService.findRepairOrderByOrderNo(orderNo);
        model.addAttribute("repairOrder", repairOrder);
        model.addAttribute("customerVisable", customerVisable);
        return "repair_order/repair_order_detail";
    }

    /**
     * 创建客户
     * @return
     */
    @RequestMapping("add-customer")
    public String addCustomer(Model model){
        Customer customer = new Customer();
        model.addAttribute("bean",customer);
        return "repair_order/customer_add";
    }

    /**
     * 进入维修单编辑页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("to-edit")
    public String toEdit(Model model,Long id){

        RepairOrder repairOrder = repairOrderMapper.find(id);
//从add 方法中拷贝过来
        CityListVo cityListVo = repairOrderService.convertToCityListVo();
        String categoryJson = JSON.toJSONString(cityListVo);
        String firstCategory = categoryMapper.find(repairOrder.getFirstCategoryId()).getName();
        String secondCategory = categoryMapper.find(repairOrder.getSecondCategoryId()).getName();
        model.addAttribute("categoryJson",categoryJson);
        model.addAttribute("firstCategory",firstCategory);
        model.addAttribute("secondCategory",secondCategory);

        Category category = categoryMapper.findByName(firstCategory,secondCategory);

        List<CategoryServiceItem> categoryServiceItemList = categoryServiceItemMapper.findByCategoryId(category.getId());
        initServiceItem(categoryServiceItemList,repairOrder);//赋值
        String categoryServiceJson = JSON.toJSONString(categoryServiceItemList);

        model.addAttribute("categoryServiceJson",categoryServiceJson);


        //获取品牌
        List<Brand> brandList = brandMapper.findAll();
        model.addAttribute("brandList",brandList);

        List<Attribute> attributeList = categoryAttributeService.findAttributes(firstCategory,secondCategory);
        initAttributeItem(attributeList,repairOrder);

        String attibutesJson = JSON.toJSONString(attributeList);
       // System.out.println(attibutesJson);
        //System.out.println(repairOrder.getProductInfoJson());

        String imagesPath = repairOrder.getImagesJson();
        if(!StringUtils.isEmpty(imagesPath)){
            String images[] = repairOrder.getImagesJson().split(",");
            String imagesJson = JSON.toJSONString(images);
            model.addAttribute("imagesPath",repairOrder.getImagesJson());
            model.addAttribute("imagesJson",imagesJson);
        }else {
            model.addAttribute("imagesPath","");
            model.addAttribute("imagesJson","[]");
        }

        Customer customer = customerMapper.find(repairOrder.getCustomerId());

        model.addAttribute("attributesJson",attibutesJson);
        model.addAttribute("repairOrder",repairOrder);
        model.addAttribute("customer",customer);


        return "repair_order/repair_order_edit";


    }

    @RequestMapping("consultOrderList")
    public String consultOrderList(Model model){
        return "repair_order/consult_order_list";
    }

    /**
     * 订单分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping("queryConsultOrderList")
    public Table queryOrder(Model model,
                            @RequestParam(value = "extra_search", defaultValue = "")String extra_search,
                            @RequestParam(value = "start", defaultValue = "0") int start,
                            @RequestParam(value = "length", defaultValue = "15") int length) {

        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);

        int pageCount = consultOrderService.countTotalPageAllByPhone(extra_search);
        List<ConsultOrder> consultOrderList = consultOrderService.findByPageAllByPhone(extra_search, pageHelper);
        return new Table(pageCount, pageCount, consultOrderList);
    }

    /**
     * 关联咨询单查看详情
     * @param model
     * @param consultOrderNo
     * @return
     */
    @RequestMapping("consultOrderdetail")
    public String findConsultOrderByConsultOrderNo(Model model, String consultOrderNo){
    	CityListVo cityListVo = repairOrderService.convertToCityListVo();
        String categoryJson = JSON.toJSONString(cityListVo);
    	ConsultOrder consultOrder = consultOrderService.findConsultOrderByConsultOrderNo(consultOrderNo);
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

        return "repair_order/consult_order_detail";
    }
    
    /**
     * 映射选择属性值
     * @param categoryServiceItemList
     * @param repairOrder
     */
    private void initServiceItem(List<CategoryServiceItem> categoryServiceItemList,RepairOrder repairOrder){

		if (categoryServiceItemList == null || repairOrder == null
				|| StringUtils.isEmpty(repairOrder.getServiceItemIds())
				|| "null".equals(repairOrder.getServiceItemIds())) {
			return;
		}

        List<Long> idList = JSON.parseArray(repairOrder.getServiceItemIds(),Long.class);
        for (CategoryServiceItem item : categoryServiceItemList) {
            if(idList.contains(item.getId())){
                item.setSelectedStatus(true);
            }else {
                item.setSelectedStatus(false);
            }
        }

    }

    /**
     * 映射属性值
     * @param attributeList
     * @param repairOrder
     */
    private void initAttributeItem(List<Attribute> attributeList,RepairOrder repairOrder){
        if(attributeList == null || repairOrder == null || StringUtils.isEmpty(repairOrder.getProductInfoJson())){
            return;
        }

        List<Attribute> resultList = JSON.parseArray(repairOrder.getProductInfoJson(),Attribute.class);

        for (Attribute attibute:attributeList) {
            for (int i = 0; i < resultList.size(); i++) {
                if(attibute.getId() == resultList.get(i).getId()){
                    attibute.setRealValue(resultList.get(i).getSelectionValues());
                    break;
                }
            }
        }
    }

//    public static void main(String[] args) {
//
//        String str = DateUtil.toString(new Date(),"yyyyMMdd");
//        String str2 = DateUtil.toString(new Date(),"yyMMdd");
//        System.out.println(str+","+str2);
//
//    }

}
