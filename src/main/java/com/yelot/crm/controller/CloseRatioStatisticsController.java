package com.yelot.crm.controller;

import com.yelot.crm.Util.ExportExcel;
import com.yelot.crm.Util.GlobalUtil;
import com.yelot.crm.Util.ResultData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.*;
import com.yelot.crm.enums.ConsultOrderStatus;
import com.yelot.crm.mapper.CategoryMapper;
import com.yelot.crm.mapper.ConsultOrderMapper;
import com.yelot.crm.mapper.ShopMapper;
import com.yelot.crm.mapper.StatisticOrderMapper;
import com.yelot.crm.vo.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 成交率统计
 * Created by kee on 17/9/29.
 */
@Controller
@RequestMapping("close-ratio-statistics")
public class CloseRatioStatisticsController {
    @Autowired
    private ConsultOrderMapper consultOrderMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private StatisticOrderMapper statisticOrderMapper;



    @RequestMapping("my")
    public String myCloseRadio(){

        return "close_ratio_statistics/my_close_ratio_statistics";
    }

    @ResponseBody
    @RequestMapping("my-month-data")
    public ResultData myConsultMonthData(){
        List<Integer> sumList = getMonthIntegers(UserUtil.getCurrentUser().getId(),null,null,null);
        //有效数量
        List<Integer> usefulList = getMonthIntegers(UserUtil.getCurrentUser().getId(),null,null, ConsultOrderStatus.ACCEPT.getCode());
        ResultData resultData = ResultData.ok();
        resultData.putDataValue("sum",sumList);
        resultData.putDataValue("list",usefulList);
        return resultData;
    }

    @ResponseBody
    @RequestMapping("my-month-category-data")
    public ResultData myRatioCategoryMonthData(){
        List<Category> categoryList = categoryMapper.findAllFirstClass();
        List<String> categoryNameList = new ArrayList<String>();
        List<BarMonthData> barMonthDataList = new ArrayList<>();
        for (Category category: categoryList) {
            categoryNameList.add(category.getName());
            BarMonthData barMonthData = new BarMonthData();
            barMonthData.setName(category.getName());
            List<Integer> sumList = getMonthIntegers(UserUtil.getCurrentUser().getId(),category.getName(),null,null);
            barMonthData.setData(sumList);
            barMonthData.setStack("总咨询量");
            barMonthDataList.add(barMonthData);
        }
        for (Category category: categoryList) {
//            categoryNameList.add(category.getName());
            BarMonthData barMonthData = new BarMonthData();
            barMonthData.setName(category.getName()+"(有效)");
            List<Integer> sumList = getMonthIntegers(UserUtil.getCurrentUser().getId(),category.getName(),null,ConsultOrderStatus.ACCEPT.getCode());
            barMonthData.setData(sumList);
            barMonthData.setStack("有效咨询量");
            barMonthDataList.add(barMonthData);
        }
        ResultData resultData = ResultData.ok();
        resultData.putDataValue("series",barMonthDataList);
        resultData.putDataValue("legend", categoryNameList);

        return resultData;

    }

    /**
     * 门店成交率统计
     * @return
     */
    @ResponseBody
    @RequestMapping("shop-month-data")
    public ResultData shopRatioMonthData(){
        List<Integer> sumList = getMonthIntegers(null,null,UserUtil.getCurrentUser().getShop_id(),null);
        //有效数量
        List<Integer> usefulList = getMonthIntegers(null,null,UserUtil.getCurrentUser().getShop_id(), ConsultOrderStatus.ACCEPT.getCode());
        ResultData resultData = ResultData.ok();
        resultData.putDataValue("sum",sumList);
        resultData.putDataValue("list",usefulList);
        return ResultData.ok().putDataValue("sum",sumList);
    }

    @ResponseBody
    @RequestMapping("shop-month-category-data")
    public ResultData shopRatioCategoryMonthData(){
        List<Category> categoryList = categoryMapper.findAllFirstClass();
        List<String> categoryNameList = new ArrayList<String>();
        List<BarMonthData> barMonthDataList = new ArrayList<>();
        for (Category category: categoryList) {
            categoryNameList.add(category.getName());
            BarMonthData barMonthData = new BarMonthData();
            barMonthData.setName(category.getName());
            List<Integer> sumList = getMonthIntegers(null,category.getName(),UserUtil.getCurrentUser().getShop_id(),null);
            barMonthData.setData(sumList);
            barMonthData.setStack("总咨询量");
            barMonthDataList.add(barMonthData);
        }
        for (Category category: categoryList) {
//            categoryNameList.add(category.getName());
            BarMonthData barMonthData = new BarMonthData();
            barMonthData.setName(category.getName()+"(有效)");
            List<Integer> sumList = getMonthIntegers(null,category.getName(),UserUtil.getCurrentUser().getShop_id(),ConsultOrderStatus.ACCEPT.getCode());
            barMonthData.setData(sumList);
            barMonthData.setStack("有效咨询量");
            barMonthDataList.add(barMonthData);
        }
        ResultData resultData = ResultData.ok();
        resultData.putDataValue("series",barMonthDataList);
        resultData.putDataValue("legend", categoryNameList);

        return resultData;

    }




    private List<Integer> getMonthIntegers(Long userId,String categoryName,Long shopId,Integer status) {
        List<MonthData> monthDataList = consultOrderMapper.findByMonthMyRadio(userId,categoryName,
                shopId,status);
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

    @RequestMapping("shop")
    public String shopCloseRadio(){

        return "close_ratio_statistics/shop_close_ratio_statistics";
    }

    @RequestMapping("total")
    public String totalCloseRadio(){

        return "close_ratio_statistics/total_ratio_statistics";
    }

    /**
     * 咨询统计
     * @return
     */
    @RequestMapping("super")
    public String superRatio(){

        return "close_ratio_statistics/super_close_ratio_statistics";
    }

    @RequestMapping("person-ratio")
    public String personRatio(Model model){
        List<Shop> shopList = shopMapper.findAll();
        model.addAttribute("shopList",shopList);
        List<Category> categoryList = categoryMapper.findAllFirstClass();
        model.addAttribute("categoryList",categoryList);
        return "close_ratio_statistics/person_ratio";
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
    public Table personQuery(Model model, String startDate, String endDate, Long shopId, String categoryName,
                             @RequestParam(value = "start", defaultValue = "0") int start,
                             @RequestParam(value = "length", defaultValue = "10") int length){

        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);
        //个人统计，共用一个业务逻辑
        int pageCount = statisticOrderMapper.countTotalPageByPerson(startDate,endDate,shopId);
//备注，成交率=
        List<StatisticOrder> statisticOrderList = statisticOrderMapper.findConsultPersonStatisticOrder(startDate,endDate,shopId,categoryName,pageHelper);

        return new Table(pageCount, pageCount, statisticOrderList);
    }

    /**
     * 门店统计
     * @param model
     * @return
     */
    @RequestMapping("shop-ratio")
    public String shopRatio(Model model){
        List<Shop> shopList = shopMapper.findAll();
        model.addAttribute("shopList",shopList);
        List<Category> categoryList = categoryMapper.findAllFirstClass();
        model.addAttribute("categoryList",categoryList);
        return "close_ratio_statistics/shop_ratio";
    }

    @ResponseBody
    @RequestMapping("shop-query")
    public Table shopQuery(Model model, String startDate, String endDate, Long shopId, String type,Long categoryId,
                             @RequestParam(value = "start", defaultValue = "0") int start,
                             @RequestParam(value = "length", defaultValue = "10") int length){

        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);
        //个人统计，共用一个业务逻辑
        Category category = categoryMapper.find(categoryId);
        String categoryName = null;
        if(category != null){
            categoryName = category.getName();
        }
        int pageCount = statisticOrderMapper.countTotalPageByShopRatio(startDate,endDate,shopId,type,categoryId,categoryName);
//备注，成交率=
        List<StatisticOrder> statisticOrderList = statisticOrderMapper.findShopRatioStatisticOrder(startDate,endDate,shopId,type,categoryId,categoryName,pageHelper);

        return new Table(pageCount, pageCount, statisticOrderList);
    }

    /**
     * 导出功能
     * @param response
     * @param startDate
     * @param endDate
     * @param shopId
     * @param categoryName
     * @throws IOException
     */

    @RequestMapping("exportExcel-person")
    public void exportExcelByPerson(
            HttpServletResponse response,
            @RequestParam(value = "startDate", defaultValue = "") String startDate,
            @RequestParam(value = "endDate", defaultValue = "") String endDate,
            @RequestParam(value = "shopId", defaultValue = "") Long shopId,
            @RequestParam(value = "categoryName", defaultValue = "") String categoryName
    ) throws IOException {

        int pageCount = statisticOrderMapper.countTotalPageByPerson(startDate,endDate,shopId);


        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(0);
        pageHelper.setSize(pageCount);


//备注，成交率=
        List<StatisticOrder> statisticOrderList = statisticOrderMapper.findConsultPersonStatisticOrder(startDate,endDate,shopId,categoryName,pageHelper);

        ExportExcel ex = new ExportExcel();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        List<String> strList = GlobalUtil.getPersonConsultList(statisticOrderList);
        int rows = strList.size() / 5;
        String []headers = {"用户名","姓名","手机号","门店","总订单量"};
        String fileName = "成交率个人订单统计报表"+startDate+"_"+endDate;
        ex.exportExcel("title",headers,strList,os,rows);
        ex.writeExcel(response, os,fileName);

    }

    @RequestMapping("exportExcel-shop")
    public void exportExcelByShop(
            HttpServletResponse response,
            @RequestParam(value = "startDate", defaultValue = "") String startDate,
            @RequestParam(value = "endDate", defaultValue = "") String endDate,
            @RequestParam(value = "shopId", defaultValue = "") Long shopId,
            @RequestParam(value = "categoryId", defaultValue = "") Long  categoryId,
            String type
    ) throws IOException {

        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(0);
        //个人统计，共用一个业务逻辑

        //个人统计，共用一个业务逻辑
        Category category = categoryMapper.find(categoryId);
        String categoryName = null;
        if(category != null){
            categoryName = category.getName();
        }
        int pageCount = statisticOrderMapper.countTotalPageByShopRatio(startDate,endDate,shopId,type,categoryId,categoryName);
        pageHelper.setSize(pageCount);

//备注，成交率=
        List<StatisticOrder> statisticOrderList = statisticOrderMapper.findShopRatioStatisticOrder(startDate,endDate,shopId,type,categoryId,categoryName,pageHelper);

        ExportExcel ex = new ExportExcel();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        List<String> strList = GlobalUtil.getTimeRatioList(statisticOrderList);
        int rows = strList.size() / 3;
        String []headers = {"时间","总咨询量","总订单量"};
        String fileName = "成交率门店订单统计报表"+startDate+"_"+endDate;
        ex.exportExcel("title",headers,strList,os,rows);
        ex.writeExcel(response, os,fileName);

    }

}
