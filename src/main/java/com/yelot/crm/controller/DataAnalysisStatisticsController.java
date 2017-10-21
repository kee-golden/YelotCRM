package com.yelot.crm.controller;

import com.yelot.crm.Util.ExportExcel;
import com.yelot.crm.Util.GlobalUtil;
import com.yelot.crm.Util.ResultData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.*;
import com.yelot.crm.mapper.CategoryMapper;
import com.yelot.crm.mapper.RepairOrderMapper;
import com.yelot.crm.mapper.ShopMapper;
import com.yelot.crm.mapper.StatisticOrderMapper;
import com.yelot.crm.vo.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author xyzloveabc
 * @2017年9月29日
 */
@Controller
@RequestMapping("data-analysis-statistics")
public class DataAnalysisStatisticsController {
	@Autowired
	private RepairOrderMapper repairOrderMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private StatisticOrderMapper statisticOrderMapper;

    @RequestMapping("list")
    public String list(Model model){

        List<Shop> shopList = shopMapper.findAll();
        model.addAttribute("shopList",shopList);

        List<Category> categoryList = categoryMapper.findAllFirstClass();
        model.addAttribute("categoryList", categoryList);
        
        return "rpt/data_analysis_statistics";
    }

    @ResponseBody
    @RequestMapping("query")
    public ResultData query(String dateArea, String dateArea2, Long shopId, String type, Long categoryId, String deliverType, String compareType) throws ParseException{
    	
    	List<StatisticOrder> tmpDateAreaStatisticOrderList = new ArrayList<StatisticOrder>();
    	List<StatisticOrder> tmpDateArea2StatisticOrderList = new ArrayList<StatisticOrder>();
    	List<StatisticOrder> dateAreaStatisticOrderList = new ArrayList<StatisticOrder>();
    	List<StatisticOrder> dateArea2StatisticOrderList = new ArrayList<StatisticOrder>();
        List<BarMonthData> barMonthDataList = new ArrayList<>();

    	List<String> xAxis = new ArrayList<String>();
    	List<String> dateAreaXAxis = new ArrayList<String>();
    	List<String> dateAreaXAxis2 = new ArrayList<String>();
    	
    	if ("Day".equals(type)) {
    		dateAreaXAxis = getDayBetweenStartAndEnd(dateArea.split("-")[0], dateArea.split("-")[1]);
        	if (dateArea2 != null && !"".equals(dateArea2)) {
        		dateAreaXAxis2 = getDayBetweenStartAndEnd(dateArea2.split("-")[0], dateArea2.split("-")[1]);
        	}
		} else if ("Week".equals(type)) {
			dateAreaXAxis = getWeekBetweenStartAndEnd(dateArea.split("-")[0], dateArea.split("-")[1]);
        	if (dateArea2 != null && !"".equals(dateArea2)) {
        		dateAreaXAxis2 = getWeekBetweenStartAndEnd(dateArea2.split("-")[0], dateArea2.split("-")[1]);
        	}
		} else {
			dateAreaXAxis = getMonthBetweenStartAndEnd(dateArea.split("-")[0], dateArea.split("-")[1]);
        	if (dateArea2 != null && !"".equals(dateArea2)) {
        		dateAreaXAxis2 = getMonthBetweenStartAndEnd(dateArea2.split("-")[0], dateArea2.split("-")[1]);
        	}
		}
    	
    	for (int i = 0; i < dateAreaXAxis.size(); i++) {
        	if (dateArea2 != null && !"".equals(dateArea2)) {
        		xAxis.add(dateAreaXAxis.get(i) + "/" + dateAreaXAxis2.get(i));
        	} else {
        		xAxis.add(dateAreaXAxis.get(i));
        	}
    	}
    	
    	tmpDateAreaStatisticOrderList = statisticOrderMapper.findDataAnalysisStatisticOrder(dateArea.split("-")[0] + " 00:00:00",dateArea.split("-")[1] + " 23:59:59",shopId,type,categoryId,deliverType);
    	dateAreaStatisticOrderList = setAllStatisticOrderList(dateAreaXAxis, tmpDateAreaStatisticOrderList);
    	List<Integer> dateAreaSumList = getTotalDataList(dateAreaStatisticOrderList, compareType);
   	 	BarMonthData barMonthData = new BarMonthData();
        barMonthData.setName("区间一数据");
        barMonthData.setStack("区间一数据");
        barMonthData.setData(dateAreaSumList);
        barMonthDataList.add(barMonthData);
    	
    	if (dateArea2 != null && !"".equals(dateArea2)) {
    		tmpDateArea2StatisticOrderList = statisticOrderMapper.findDataAnalysisStatisticOrder(dateArea2.split("-")[0] + " 00:00:00",dateArea2.split("-")[1] + " 23:59:59",shopId,type,categoryId,deliverType);
    		dateArea2StatisticOrderList = setAllStatisticOrderList(dateAreaXAxis2, tmpDateArea2StatisticOrderList);
        	List<Integer> dateArea2SumList = getTotalDataList(dateArea2StatisticOrderList, compareType);
       	 	BarMonthData barMonthData2 = new BarMonthData();
       	 	barMonthData2.setName("区间二数据");
       	 	barMonthData2.setStack("区间二数据");
       	 	barMonthData2.setData(dateArea2SumList);
            barMonthDataList.add(barMonthData2);
		}
    	
        return ResultData.ok().putDataValue("xAxis", xAxis).putDataValue("series",barMonthDataList);
    }

    @ResponseBody
    @RequestMapping("queryList")
    public Table queryList(String dateArea, String dateArea2, Long shopId, String type, Long categoryId, String deliverType, String compareType) throws ParseException{
    	
    	List<StatisticOrder> tmpDateAreaStatisticOrderList = new ArrayList<StatisticOrder>();
    	List<StatisticOrder> tmpDateArea2StatisticOrderList = new ArrayList<StatisticOrder>();
    	List<StatisticOrder> dateAreaStatisticOrderList = new ArrayList<StatisticOrder>();
    	List<StatisticOrder> dateArea2StatisticOrderList = new ArrayList<StatisticOrder>();
    	List<DataAnalysisStatistics> dataAnalysisStatisticsList = new ArrayList<DataAnalysisStatistics>();

    	List<String> dateAreaXAxis = new ArrayList<String>();
    	List<String> dateAreaXAxis2 = new ArrayList<String>();
    	
    	if ("Day".equals(type)) {
    		dateAreaXAxis = getDayBetweenStartAndEnd(dateArea.split("-")[0], dateArea.split("-")[1]);
    		dateAreaXAxis2 = getDayBetweenStartAndEnd(dateArea2.split("-")[0], dateArea2.split("-")[1]);
		} else if ("Week".equals(type)) {
			dateAreaXAxis = getWeekBetweenStartAndEnd(dateArea.split("-")[0], dateArea.split("-")[1]);
    		dateAreaXAxis2 = getWeekBetweenStartAndEnd(dateArea2.split("-")[0], dateArea2.split("-")[1]);
		} else {
			dateAreaXAxis = getMonthBetweenStartAndEnd(dateArea.split("-")[0], dateArea.split("-")[1]);
    		dateAreaXAxis2 = getMonthBetweenStartAndEnd(dateArea2.split("-")[0], dateArea2.split("-")[1]);
		}
    	
    	tmpDateAreaStatisticOrderList = statisticOrderMapper.findDataAnalysisStatisticOrder(dateArea.split("-")[0] + " 00:00:00",dateArea.split("-")[1] + " 23:59:59",shopId,type,categoryId,deliverType);
    	dateAreaStatisticOrderList = setAllStatisticOrderList(dateAreaXAxis, tmpDateAreaStatisticOrderList);
    	List<Integer> dateAreaSumList = getTotalDataList(dateAreaStatisticOrderList, compareType);
    	
		tmpDateArea2StatisticOrderList = statisticOrderMapper.findDataAnalysisStatisticOrder(dateArea2.split("-")[0] + " 00:00:00",dateArea2.split("-")[1] + " 23:59:59",shopId,type,categoryId,deliverType);
		dateArea2StatisticOrderList = setAllStatisticOrderList(dateAreaXAxis2, tmpDateArea2StatisticOrderList);
    	List<Integer> dateArea2SumList = getTotalDataList(dateArea2StatisticOrderList, compareType);
    	
    	for (int i = 0; i < dateAreaXAxis.size(); i++) {
    		DataAnalysisStatistics dataAnalysisStatistics = new DataAnalysisStatistics();
    		dataAnalysisStatistics.setTimeInterval(dateAreaXAxis.get(i) + " / " + dateAreaXAxis2.get(i));
    		dataAnalysisStatistics.setFirstValue(dateAreaSumList.get(i));
    		dataAnalysisStatistics.setSecondValue(dateArea2SumList.get(i));
    		dataAnalysisStatistics.setVariation(dateAreaSumList.get(i) - dateArea2SumList.get(i));
    		dataAnalysisStatistics.setChangeRate(getChangeRate(dateAreaSumList.get(i), dateArea2SumList.get(i)));
    		dataAnalysisStatisticsList.add(dataAnalysisStatistics);
		}
    	
    	int firstValueTotal = 0;
    	for (Integer tmp : dateAreaSumList) {
    		firstValueTotal += tmp.intValue();
		}
    	
    	int secondValueTotal = 0;
    	for (Integer tmp : dateArea2SumList) {
    		secondValueTotal += tmp.intValue();
		}
		DataAnalysisStatistics dataAnalysisStatistics = new DataAnalysisStatistics();
		dataAnalysisStatistics.setTimeInterval(dateAreaXAxis.get(0) + "至" + dateAreaXAxis.get(dateAreaXAxis.size()-1) + " / " + dateAreaXAxis2.get(0) + "至" + dateAreaXAxis2.get(dateAreaXAxis2.size()-1));
    	dataAnalysisStatistics.setFirstValue(firstValueTotal);
    	dataAnalysisStatistics.setSecondValue(secondValueTotal);
    	dataAnalysisStatistics.setVariation(firstValueTotal - secondValueTotal);
    	dataAnalysisStatistics.setChangeRate(getChangeRate(firstValueTotal, secondValueTotal));
    	dataAnalysisStatisticsList.add(dataAnalysisStatistics);
    	
        return new Table(dateAreaXAxis.size() + 1, dateAreaXAxis.size() + 1, dataAnalysisStatisticsList);
    }
    
    private List<StatisticOrder> setAllStatisticOrderList(List<String> xAxis, List<StatisticOrder> statisticOrderList){
    	List<StatisticOrder> tmpStatisticOrderList = new ArrayList<StatisticOrder>();
    	
    	for (String str : xAxis) {
    		StatisticOrder tmpStatisticOrder = new StatisticOrder();
			tmpStatisticOrder.setTime(str);
			tmpStatisticOrder.setTotalCount(0);
			tmpStatisticOrder.setTotalPayment(0);
			
			for (StatisticOrder statisticOrder : statisticOrderList) {
				if (str.equals(statisticOrder.getTime())) {
					tmpStatisticOrder= statisticOrder;
					break;
				}
			}
			tmpStatisticOrderList.add(tmpStatisticOrder);
		}
    	
    	return tmpStatisticOrderList;
    }
    
    private List<Integer> getTotalDataList(List<StatisticOrder> statisticOrderList, String compareType) {

    	List<Integer> totalDataList = new ArrayList<Integer>();
		for (StatisticOrder statisticOrder : statisticOrderList) {
	    	if ("0".equals(compareType)) {
	    		totalDataList.add(statisticOrder.getTotalPayment());
			} else {
				totalDataList.add(statisticOrder.getTotalCount());
			}
		}
    	return totalDataList;
    	
    }
    
    private List<String> getDayBetweenStartAndEnd(String startStr, String endStr) throws ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = sdf.parse(startStr.replace("/", "-"));
		Date endDate = sdf.parse(endStr.replace("/", "-"));
		List<String> result = new ArrayList<String>();
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(startDate);
		while (startDate.getTime() <= endDate.getTime()) {
			result.add(sdf.format(tempStart.getTime()));
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
			startDate = tempStart.getTime();
		}
		return result;
    }
    
    private List<String> getWeekBetweenStartAndEnd(String startStr, String endStr) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = format.parse(startStr.replace("/", "-"));
		Date endDate = format.parse(endStr.replace("/", "-"));
		
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		startCalendar.setTime(startDate);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		endCalendar.setTime(endDate);

		List<String> result = new ArrayList<String>();
		
		for (int i = startCalendar.get(Calendar.WEEK_OF_YEAR); i <= endCalendar.get(Calendar.WEEK_OF_YEAR); i++) {
			result.add(startCalendar.get(Calendar.YEAR) + "-" + String.format("%02d", i - 1));
		}
		
		return result;
    }

    private List<String> getMonthBetweenStartAndEnd(String startStr, String endStr) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = format.parse(startStr.replace("/", "-"));
		Date endDate = format.parse(endStr.replace("/", "-"));
		
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);

		List<String> result = new ArrayList<String>();
		
		for (int i = startCalendar.get(Calendar.MONTH); i <= endCalendar.get(Calendar.MONTH); i++) {
			result.add(startCalendar.get(Calendar.YEAR) + "-" + String.format("%02d", i+1));
		}
    	return result;
    }
    
    private String getChangeRate(double a, double b){
		if ((a - b) == 0) {
			return "无变化";
		} else if((a - b) > 0){
			if(b == 0){
				return "↑无穷大";
			} else {
				return "↑" + Math.round((a - b)/b*(double)10000)/(double)100 + "%";
			}
		} else {
			if(a == 0){
				return "↓无穷大";
			} else {
				return "↓" + Math.round((a - b)/b*(double)10000)/(double)100 + "%";
			}
		}
    }
}
