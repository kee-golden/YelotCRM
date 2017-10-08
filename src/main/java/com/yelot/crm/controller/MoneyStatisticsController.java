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
import java.util.ArrayList;
import java.util.List;

/**
 * @author xyzloveabc
 * @2017年9月29日
 */
@Controller
@RequestMapping("money-statistics")
public class MoneyStatisticsController {
	@Autowired
	private RepairOrderMapper repairOrderMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private StatisticOrderMapper statisticOrderMapper;

    @RequestMapping("my")
    public String myMoney(){
        return "money_statistics/my_money_statistics";
    }

    @RequestMapping("shop")
    public String shopMoney(){

        return "money_statistics/shop_money_statistics";
    }

    @RequestMapping("super")
    public String superStatistic(Model model){
        return "money_statistics/super_kpi_statistics";
    }

    @RequestMapping("person-statistic")
    public String personStatistic(Model model){

        List<Shop> shopList = shopMapper.findAll();
        model.addAttribute("shopList",shopList);
        List<Category> categoryList = categoryMapper.findAllFirstClass();
        model.addAttribute("categoryList",categoryList);

        return "money_statistics/person_statistics";

    }

    /**
     * 个人统计
     * @param model
     * @param startDate
     * @param endDate
     * @return
     */
    @ResponseBody
    @RequestMapping("person-query")
    public Table personQuery(Model model,String startDate,String endDate,Long shopId,Long categoryId,
                             @RequestParam(value = "start", defaultValue = "0") int start,
                             @RequestParam(value = "length", defaultValue = "10") int length){

        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);

        int pageCount = statisticOrderMapper.countTotalPageByPerson(startDate,endDate,shopId);

        List<StatisticOrder> statisticOrderList = statisticOrderMapper.findPersonStatisticOrder(startDate,endDate,shopId,categoryId,pageHelper);

        return new Table(pageCount, pageCount, statisticOrderList);
    }

    /**
     * 门店统计
     * @param model
     * @return
     */
    @RequestMapping("shop-statistic")
    public String shopStatistic(Model model){

        List<Shop> shopList = shopMapper.findAll();
        model.addAttribute("shopList",shopList);
        List<Category> categoryList = categoryMapper.findAllFirstClass();
        model.addAttribute("categoryList",categoryList);
        return "money_statistics/shop_statistics";
    }

    @ResponseBody
    @RequestMapping("shop-query")
    public Table shopQuery(Model model,
                           @RequestParam(value = "startDate", defaultValue = "")String startDate,
                           @RequestParam(value = "endDate", defaultValue = "")String endDate,
                           Long shopId,Long categoryId,String type,
                             @RequestParam(value = "start", defaultValue = "0") int start,
                             @RequestParam(value = "length", defaultValue = "10") int length){

        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);

        int pageCount = statisticOrderMapper.countTotalPageByShop(startDate,endDate,shopId,categoryId,type);

        List<StatisticOrder> statisticOrderList = statisticOrderMapper.findShopStatisticOrder(startDate,endDate,shopId,categoryId,type,pageHelper);

        return new Table(pageCount, pageCount, statisticOrderList);
    }





    @RequestMapping("total")
    public String totalMoney(){

        return "money_statistics/total_money_statistics";
    }

    @ResponseBody
    @RequestMapping("my-month-data")
    public ResultData myMoneyMonthData(){
        List<Integer> sumList = getMonthIntegers(UserUtil.getCurrentUser().getId(),null,null);
        return ResultData.ok().putDataValue("sum",sumList);
    }

    @ResponseBody
    @RequestMapping("my-month-category-data")
    public ResultData myMoneyCategoryMonthData(){
        List<Category> categoryList = categoryMapper.findAllFirstClass();
        
        List<String> categoryNameList = new ArrayList<String>();
        List<BarMonthData> barMonthDataList = new ArrayList<>();
        
        for (Category category: categoryList) {
        	categoryNameList.add(category.getName());
        	
            BarMonthData barMonthData = new BarMonthData();
            barMonthData.setName(category.getName());
            List<Integer> sumList = getMonthIntegers(UserUtil.getCurrentUser().getId(),category.getId(),null);
            barMonthData.setData(sumList);
            barMonthDataList.add(barMonthData);
        }

        return ResultData.ok().putDataValue("legend", categoryNameList).putDataValue("series",barMonthDataList);

    }

    @ResponseBody
    @RequestMapping("shop-month-data")
    public ResultData shopMoneyMonthData(){
        List<Integer> sumList = getMonthIntegers(null,null,UserUtil.getCurrentUser().getShop_id());
        return ResultData.ok().putDataValue("sum",sumList);
    }

    @ResponseBody
    @RequestMapping("shop-month-category-data")
    public ResultData shopMoneyCategoryMonthData(){
        List<Category> categoryList = categoryMapper.findAllFirstClass();
        
        List<String> categoryNameList = new ArrayList<String>();
        List<BarMonthData> barMonthDataList = new ArrayList<>();
        
        for (Category category: categoryList) {
        	categoryNameList.add(category.getName());
        	
            BarMonthData barMonthData = new BarMonthData();
            barMonthData.setName(category.getName());
            List<Integer> sumList = getMonthIntegers(null,category.getId(),UserUtil.getCurrentUser().getShop_id());
            barMonthData.setData(sumList);
            barMonthDataList.add(barMonthData);
        }

        return ResultData.ok().putDataValue("legend", categoryNameList).putDataValue("series",barMonthDataList);

    }

    @ResponseBody
    @RequestMapping("total-month-data")
    public ResultData totalMoneyMonthData(){
        List<Integer> sumList = getMonthIntegers(null,null,null);
        return ResultData.ok().putDataValue("sum",sumList);
    }

    @ResponseBody
    @RequestMapping("total-month-category-data")
    public ResultData totalMoneyCategoryMonthData(){
        List<Category> categoryList = categoryMapper.findAllFirstClass();
        
        List<String> categoryNameList = new ArrayList<String>();
        List<BarMonthData> barMonthDataList = new ArrayList<>();
        
        for (Category category: categoryList) {
        	categoryNameList.add(category.getName());
        	
            BarMonthData barMonthData = new BarMonthData();
            barMonthData.setName(category.getName());
            List<Integer> sumList = getMonthIntegers(null,category.getId(),null);
            barMonthData.setData(sumList);
            barMonthDataList.add(barMonthData);
        }

        return ResultData.ok().putDataValue("legend", categoryNameList).putDataValue("series",barMonthDataList);

    }

    private List<Integer> getMonthIntegers(Long userId,Long categoryId,Long shopId) {
        List<MonthData> monthDataList = repairOrderMapper.findByMonth(userId, categoryId, shopId);
        List<Integer> sumList = new ArrayList<Integer>(12);

        for (int i = 0; i < 12; i++) {
            sumList.add(0);//初始化
            for (MonthData monthData:monthDataList) {
                if(i == monthData.getMonth()){
                    sumList.set(i,monthData.getNumber());
                    break;
                }

            }
        }
        return sumList;
    }


    //exportExcel-person""
    @RequestMapping("exportExcel-person")
    public void exportExcelByPerson(
            HttpServletResponse response,
            @RequestParam(value = "startDate", defaultValue = "") String startDate,
            @RequestParam(value = "endDate", defaultValue = "") String endDate,
            @RequestParam(value = "shopId", defaultValue = "") Long shopId,
            @RequestParam(value = "categoryId", defaultValue = "") Long categoryId
           ) throws IOException {

        int pageCount = statisticOrderMapper.countTotalPageByPerson(startDate,endDate,shopId);

        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(0);
        pageHelper.setSize(pageCount);

        List<StatisticOrder> statisticOrderList = statisticOrderMapper.findPersonStatisticOrder(startDate,endDate,shopId,categoryId,pageHelper);

        ExportExcel ex = new ExportExcel();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        List<String> strList = GlobalUtil.getPersonList(statisticOrderList);
        int rows = strList.size() / 6;
        String []headers = {"用户名","姓名","手机号","门店","总金额","总订单量"};
        String fileName = "业绩订单统计报表"+startDate+"_"+endDate;
        ex.exportExcel("title",headers,strList,os,rows);
        ex.writeExcel(response, os,fileName);

    }

    @RequestMapping("exportExcel-shop")
    public void exportExcelByShop(
            HttpServletResponse response,
            @RequestParam(value = "startDate", defaultValue = "") String startDate,
            @RequestParam(value = "endDate", defaultValue = "") String endDate,
            @RequestParam(value = "shopId", defaultValue = "") Long shopId,
            @RequestParam(value = "categoryId", defaultValue = "") Long categoryId,
            String type
    ) throws IOException {

        int pageCount = statisticOrderMapper.countTotalPageByPerson(startDate,endDate,shopId);

        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(0);
        pageHelper.setSize(pageCount);


        List<StatisticOrder> statisticOrderList = statisticOrderMapper.findShopStatisticOrder(startDate,endDate,shopId,categoryId,type,pageHelper);

        ExportExcel ex = new ExportExcel();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        List<String> strList = GlobalUtil.getTimeList(statisticOrderList);
        int rows = strList.size() / 3;
        String []headers = {"时间","总金额","总订单量"};
        String fileName = "业绩门店订单统计报表"+startDate+"_"+endDate;
        ex.exportExcel("title",headers,strList,os,rows);
        ex.writeExcel(response, os,fileName);

    }


}
