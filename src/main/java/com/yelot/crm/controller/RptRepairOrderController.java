package com.yelot.crm.controller;

import com.alibaba.fastjson.JSON;
import com.yelot.crm.Util.ExlRptCellType;
import com.yelot.crm.Util.ExlRptExportUtil;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.*;
import com.yelot.crm.enums.RepairOrderStatus;
import com.yelot.crm.mapper.*;
import com.yelot.crm.service.CategoryAttributeService;
import com.yelot.crm.service.RptRepairOrderService;
import com.yelot.crm.service.UserService;
import com.yelot.crm.vo.CityListVo;
import com.yelot.crm.vo.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xyzloveabc
 * @2017年9月13日
 */
@Controller
@RequestMapping("rpt-repair-order")
public class RptRepairOrderController {

    @Autowired
    private RptRepairOrderService rptRepairOrderService;

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
    private ShopMapper shopMapper;
    
    @Autowired
    private UserService userService;

    @RequestMapping("list")
    public String list(Model model){

        CityListVo cityListVo = rptRepairOrderService.convertToCityListVo();
        String categoryJson = JSON.toJSONString(cityListVo);
        String firstCategory = cityListVo.getCitylist().get(0).getP();
        String secondCategory = cityListVo.getCitylist().get(0).getC().get(0).getN();
        
        model.addAttribute("categoryJson",categoryJson);
        model.addAttribute("firstCategory",firstCategory);
        model.addAttribute("secondCategory",secondCategory);

        List<Shop> shopList = shopMapper.findAll();
        model.addAttribute("shopList", shopList);
        
        List<User> onlineUserList = userService.findUserByType("2", null); // 在线客服
        model.addAttribute("onlineUserList", onlineUserList);
        
        List<User> shopUserList = userService.findUserByType("1", null); // 门店客服
        model.addAttribute("shopUserList", shopUserList);
        
        model.addAttribute("repairOrderStatusList", RepairOrderStatus.values());
        
        /*List<Attribute> attributeList = categoryAttributeService.findAttributes(firstCategory,secondCategory);
        String attibutesJson = JSON.toJSONString(attributeList);

        model.addAttribute("attributesJson",attibutesJson);*/
        return "rpt/rpt_repair_order_all";
    }

    @ResponseBody
    @RequestMapping("findUserByShopId")
    public List<User> findUserByShopId(String shopId){
    	return userService.findUserByType("1", shopId);
    }
    
    /**
     * 订单分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping("query")
	public Table queryOrder(
			@RequestParam(value = "dateArea", defaultValue = "") String dateArea,
			@RequestParam(value = "shopId", defaultValue = "") String shopId,
			@RequestParam(value = "firstCategory", defaultValue = "") String firstCategory,
			@RequestParam(value = "secondCategory", defaultValue = "") String secondCategory,
			@RequestParam(value = "onLineUser", defaultValue = "") String onLineUser,
			@RequestParam(value = "shopUser", defaultValue = "") String shopUser,
			@RequestParam(value = "deliverType", defaultValue = "") String deliverType,
			@RequestParam(value = "customerType", defaultValue = "") String customerType,
			@RequestParam(value = "channelSource", defaultValue = "") String channelSource,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "start", defaultValue = "0") int start,
			@RequestParam(value = "length", defaultValue = "10") int length) {
    	
        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);
        
        int pageCount = rptRepairOrderService.countTotalPage(dateArea.split("-")[0], dateArea.split("-")[1], shopId, firstCategory, secondCategory, onLineUser,shopUser, deliverType, customerType, channelSource, status);
        
        List<RptRepairOrder> rptRepairOrderList = rptRepairOrderService.findByPage(dateArea.split("-")[0], dateArea.split("-")[1], shopId, firstCategory, secondCategory, onLineUser,shopUser, deliverType, customerType, channelSource, status, pageHelper);
        return new Table(pageCount, pageCount, rptRepairOrderList);
    }

    /**
     * 订单分页查询
     * @return
     * @throws IOException 
     */
    @RequestMapping("exportExcel")
	public void exportExcel(
			HttpServletResponse response,
			@RequestParam(value = "dateArea", defaultValue = "") String dateArea,
			@RequestParam(value = "shopId", defaultValue = "") String shopId,
			@RequestParam(value = "firstCategory", defaultValue = "") String firstCategory,
			@RequestParam(value = "secondCategory", defaultValue = "") String secondCategory,
			@RequestParam(value = "onLineUser", defaultValue = "") String onLineUser,
			@RequestParam(value = "shopUser", defaultValue = "") String shopUser,
			@RequestParam(value = "deliverType", defaultValue = "") String deliverType,
			@RequestParam(value = "customerType", defaultValue = "") String customerType,
			@RequestParam(value = "channelSource", defaultValue = "") String channelSource,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "start", defaultValue = "0") int start,
			@RequestParam(value = "length", defaultValue = "10") int length) throws IOException {
    	
        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(0);
        pageHelper.setSize(ExlRptExportUtil.REPORT_PAGE_SIZE);
        
        List<RptRepairOrder> rptRepairOrderList = rptRepairOrderService.findByPage(dateArea.split("-")[0], dateArea.split("-")[1], shopId, firstCategory, secondCategory, onLineUser, shopUser, deliverType, customerType, channelSource, status, pageHelper);
        
		List<Object[]> dataList = new ArrayList<Object[]>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for (RptRepairOrder rptRepairOrder : rptRepairOrderList) {
			setOneRepairStatus(rptRepairOrder);
			
			Object[] obj = new Object[52];
			obj[0] = rptRepairOrder.getShopName();	// 门店
			obj[1] = rptRepairOrder.getCreateAt() != null ? sdf.format(rptRepairOrder.getCreateAt()) : null;	// 接单日
			obj[2] = sdf.format(new Date());	// 今天日期
			obj[3] = rptRepairOrder.getOrderNo();	// 单号
			obj[4] = rptRepairOrder.getPickupAt() != null ? sdf.format(rptRepairOrder.getPickupAt()) : null;	// 预计归还日
			obj[5] = "";	// 到期提醒
			obj[6] = rptRepairOrder.getSongHuiDate() != null ? sdf.format(rptRepairOrder.getSongHuiDate()) : null;	// 送回日
			obj[7] = rptRepairOrder.getQuHuoDate() != null ? sdf.format(rptRepairOrder.getQuHuoDate()) : null;	// 取货日
			obj[8] = rptRepairOrder.getConsultCreateUserName() == null ? rptRepairOrder.getCreateUserName() : rptRepairOrder.getConsultCreateUserName();	// 首接人
			obj[9] = rptRepairOrder.getCreateUserName();	// 接单人
			obj[10] = rptRepairOrder.getDeliverType() == null ? "客户上门" : rptRepairOrder.getDeliverType();	// 接单方式
			obj[11] = rptRepairOrder.getTypeName();	// 订单类型
			obj[12] = rptRepairOrder.getStatusName();	// 订单状态
			obj[13] = "";	// 计算月份
			obj[14] = rptRepairOrder.getBrandName();	// 品牌
			obj[15] = rptRepairOrder.getFirstCategoryName();	// 货品类型
			obj[16] = rptRepairOrder.getSecondCategoryName();	// 货品名称
			obj[17] = rptRepairOrder.getRepairDesc();	// 维修内容
			obj[18] = rptRepairOrder.getServiceItemNames();	// 维修工序
			obj[19] = "";	// 是否返修
			obj[20] = rptRepairOrder.getTotalPayment();	// 小结
			obj[21] = rptRepairOrder.getMaterialPayment() == -1 ? "待定" : rptRepairOrder.getMaterialPayment();	// 料钱
			obj[22] = "";	// 回收料
			obj[23] = rptRepairOrder.getDiscountAmountPayment() == -1 ? "待定" : rptRepairOrder.getDiscountAmountPayment();	// 优惠
			obj[24] = rptRepairOrder.getNonPaymentTypeName();	// 付款方式
			obj[25] = "";	// 付款金额
			obj[26] = rptRepairOrder.getAdvancePayment();	// 定金
			obj[27] = "";	// 凭证号
			obj[28] = "";	// 发票
			obj[29] = "";	// 快递费
			obj[30] = "";	// 快递公司
			obj[31] = "";	// 快递单号
			obj[32] = "";	// 保费
			obj[33] = "";	// 保单号
			obj[34] = "";	// 合计支出
			obj[35] = rptRepairOrder.getCustomerName();	// 姓名
			obj[36] = rptRepairOrder.getCustomerSex();	// 性别
			obj[37] = rptRepairOrder.getCustomerPhone();	// 电话
			obj[38] = rptRepairOrder.getProvince();	// 省
			obj[39] = rptRepairOrder.getCity();	// 市
			obj[40] = rptRepairOrder.getCustomerAddress();	// 快递地址
			obj[41] = rptRepairOrder.getWechatNickname();	// 微信名称
			obj[42] = rptRepairOrder.getWechatId();	// 微信号
			obj[43] = rptRepairOrder.getCustomerQQ();	// 其他账号（QQ，淘宝，微博等）
			obj[44] = "";	// 设备号
			obj[45] = rptRepairOrder.getCustomerType();	// 客户类型
			obj[46] = rptRepairOrder.getChannelSource();	// 来源
			obj[47] = "";	// 搜索关键词
			obj[48] = "";	// 着陆页链接
			obj[49] = "";	// 备注
			obj[50] = "";	// 对比照片
			obj[51] = rptRepairOrder.getConsultCreateAt() != null ? sdf.format(rptRepairOrder.getConsultCreateAt()) : null;	// 起初咨询时间
			dataList.add(obj);
		}
        
		response.setContentType("application/msexcel");
		response.setHeader("Content-disposition","attachment;filename="+ 
				new String(("订单统计报表"+sdf.format(new Date())).getBytes("gb2312"), "iso8859-1")+".xlsx");
		
		List<ExlRptCellType> titleList = new ArrayList<ExlRptCellType>();
		titleList = ExlRptExportUtil.getTitleList(setTitleList());

		ExlRptExportUtil.makeRepairOrderInfo("订单统计报表", titleList, dataList).write(response.getOutputStream());
    }
    
    private List<String> setTitleList(){
		List<String> titleList = new ArrayList<String>();
		titleList.add("门店");
		titleList.add("接单日");
		titleList.add("今天日期");
		titleList.add("单号");
		titleList.add("预计归还日");
		titleList.add("到期提醒");
		titleList.add("送回日");
		titleList.add("取货日");
		titleList.add("首接人");
		titleList.add("接单人");
		titleList.add("接单方式");
		titleList.add("订单类型");
		titleList.add("订单状态");
		titleList.add("计算月份");
		titleList.add("品牌");
		titleList.add("货品类型");
		titleList.add("货品名称");
		titleList.add("维修内容");
		titleList.add("维修工序");
		titleList.add("是否返修");
		titleList.add("小结");
		titleList.add("料钱");
		titleList.add("回收料");
		titleList.add("优惠");
		titleList.add("付款方式");
		titleList.add("付款金额");
		titleList.add("定金");
		titleList.add("凭证号");
		titleList.add("发票");
		titleList.add("快递费");
		titleList.add("快递公司");
		titleList.add("快递单号");
		titleList.add("保费");
		titleList.add("保单号");
		titleList.add("合计支出");
		titleList.add("姓名");
		titleList.add("性别");
		titleList.add("电话");
		titleList.add("省");
		titleList.add("市");
		titleList.add("快递地址");
		titleList.add("微信名称");
		titleList.add("微信号");
		titleList.add("其他账号（QQ，淘宝，微博等）");
		titleList.add("设备号");
		titleList.add("客户类型");
		titleList.add("来源");
		titleList.add("搜索关键词");
		titleList.add("着陆页链接");
		titleList.add("备注");
		titleList.add("对比照片");
		titleList.add("起初咨询时间");
		return titleList;
    }

	private void setOneRepairStatus(RptRepairOrder rptRepairOrder){
		RepairOrderStatus[] repairOrderStatusList = RepairOrderStatus.values();
		for (RepairOrderStatus repairOrderStatus : repairOrderStatusList) {
			if (rptRepairOrder.getStatus() == repairOrderStatus.getCode()) {
				rptRepairOrder.setStatusName(repairOrderStatus.getMessage());
				break;
			}
		}
	}
}
