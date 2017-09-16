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
        
        model.addAttribute("repairOrderStatusList", RepairOrderStatus.values());
        
        /*List<Attribute> attributeList = categoryAttributeService.findAttributes(firstCategory,secondCategory);
        String attibutesJson = JSON.toJSONString(attributeList);

        model.addAttribute("attributesJson",attibutesJson);*/
        return "rpt/rpt_repair_order_all";
    }
    
    /**
     * 订单分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping("query")
	public Table queryOrder(
			@RequestParam(value = "startDate", defaultValue = "") String startDate,
			@RequestParam(value = "endDate", defaultValue = "") String endDate,
			@RequestParam(value = "firstCategory", defaultValue = "") String firstCategory,
			@RequestParam(value = "secondCategory", defaultValue = "") String secondCategory,
			@RequestParam(value = "shopId", defaultValue = "") String shopId,
			@RequestParam(value = "customerType", defaultValue = "") String customerType,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "typeName", defaultValue = "") String typeName,
			@RequestParam(value = "start", defaultValue = "0") int start,
			@RequestParam(value = "length", defaultValue = "10") int length) {
    	
        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);
        
        int pageCount = rptRepairOrderService.countTotalPage(startDate, endDate, firstCategory, secondCategory, shopId, customerType, status, typeName);
        
        List<RptRepairOrder> rptRepairOrderList = rptRepairOrderService.findByPage(startDate, endDate, firstCategory, secondCategory, shopId, customerType, status, typeName, pageHelper);
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
			@RequestParam(value = "startDate", defaultValue = "") String startDate,
			@RequestParam(value = "endDate", defaultValue = "") String endDate,
			@RequestParam(value = "firstCategory", defaultValue = "") String firstCategory,
			@RequestParam(value = "secondCategory", defaultValue = "") String secondCategory,
			@RequestParam(value = "shopId", defaultValue = "") String shopId,
			@RequestParam(value = "customerType", defaultValue = "") String customerType,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "typeName", defaultValue = "") String typeName,
			@RequestParam(value = "start", defaultValue = "0") int start,
			@RequestParam(value = "length", defaultValue = "10") int length) throws IOException {
    	
        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(0);
        pageHelper.setSize(ExlRptExportUtil.REPORT_PAGE_SIZE);
        
        List<RptRepairOrder> rptRepairOrderList = rptRepairOrderService.findByPage(startDate, endDate, firstCategory, secondCategory, shopId, customerType, status, typeName, pageHelper);
        
		List<Object[]> dataList = new ArrayList<Object[]>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for (RptRepairOrder rptRepairOrder : rptRepairOrderList) {
			Object[] obj = new Object[51];
			obj[0] = rptRepairOrder.getShopName();				// 门店
			obj[1] = sdf.format(rptRepairOrder.getCreateAt());	// 接单日
			obj[2] = sdf.format(new Date());					// 今天日期
			obj[3] = rptRepairOrder.getOrderNo();				// 单号
			obj[4] = sdf.format(rptRepairOrder.getPickupAt());	// 预计归还日
			obj[5] = "";										// 到期提醒
			obj[6] = "";										// 送回日
			obj[7] = "";										// 取货日
			obj[8] = "";										// 首接人
			obj[9] = rptRepairOrder.getCreateUserName();		// 接单人
			obj[10] = "";										// 接单方式
			obj[11] = "";										// 确认维修
			obj[12] = "";										// 计算月份
			obj[13] = rptRepairOrder.getBrandName();			// 品牌
			obj[14] = rptRepairOrder.getFirstCategoryName();	// 货品类型
			obj[15] = rptRepairOrder.getSecondCategoryName();	// 货品名称
			obj[16] = rptRepairOrder.getRepairDesc();			// 维修内容
			obj[17] = rptRepairOrder.getServiceItemNames();		// 维修工序
			obj[18] = "";										// 是否返修
			obj[19] = rptRepairOrder.getTotalPayment();			// 小结
			obj[20] = rptRepairOrder.getMaterialPayment();		// 料钱
			obj[21] = "";										// 回收料
			obj[22] = "";										// 优惠
			obj[23] = "";										// 付款方式
			obj[24] = "";										// 付款金额
			obj[25] = rptRepairOrder.getAdvancePayment();		// 定金
			obj[26] = "";										// 凭证号
			obj[27] = "";										// 发票
			obj[28] = "";										// 快递费
			obj[29] = "";										// 快递公司
			obj[30] = "";										// 快递单号
			obj[31] = "";										// 保费
			obj[32] = "";										// 保单号
			obj[33] = "";										// 合计支出
			obj[34] = rptRepairOrder.getCustomerName();			// 姓名
			obj[35] = rptRepairOrder.getCustomerSex();			// 性别
			obj[36] = rptRepairOrder.getCustomerPhone();		// 电话
			obj[37] = rptRepairOrder.getProvince();				// 省
			obj[38] = rptRepairOrder.getCity();					// 市
			obj[39] = rptRepairOrder.getCustomerAddress();		// 快递地址
			obj[40] = rptRepairOrder.getWechatNickname();		// 微信名称
			obj[41] = rptRepairOrder.getWechatId();				// 微信号
			obj[42] = rptRepairOrder.getCustomerQQ();			// 其他账号（QQ，淘宝，微博等）
			obj[43] = "";										// 设备号
			obj[44] = rptRepairOrder.getCustomerType();			// 客户类型
			obj[45] = rptRepairOrder.getChannelSource();		// 来源
			obj[46] = "";										// 搜索关键词
			obj[47] = "";										// 着陆页链接
			obj[48] = "";										// 备注
			obj[49] = "";										// 对比照片
			obj[50] = "";										// 起初咨询时间
			dataList.add(obj);
		}
        
		response.setContentType("application/msexcel");
		response.setHeader("Content-disposition","attachment;filename="+ 
				new String(("订单统计报表").getBytes("gb2312"), "iso8859-1")+ ".xlsx");
		
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
		titleList.add("确认维修");
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
}
