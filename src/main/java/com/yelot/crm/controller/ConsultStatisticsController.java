package com.yelot.crm.controller;

import com.yelot.crm.Util.ResultData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kee on 17/9/28.
 */
@Controller
@RequestMapping("consult-statistics")
public class ConsultStatisticsController {

    @Autowired
    private ConsultOrderMapper consultOrderMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private StatisticOrderMapper statisticOrderMapper;


    @RequestMapping("my")
    public String my(){

        return "consult_statistics/my_consult_statistics";
    }

    @RequestMapping("shop")
    public String shop(Model model){

        return "consult_statistics/shop_consult_statistics";
    }

    /**
     * 咨询统计
     * @return
     */
    @RequestMapping("super")
    public String superConsult(){

        return "consult_statistics/super_consult_statistics";
    }

    @RequestMapping("person-consult")
    public String personConsult(Model model){
        List<Shop> shopList = shopMapper.findAll();
        model.addAttribute("shopList",shopList);
        List<Category> categoryList = categoryMapper.findAllFirstClass();
        model.addAttribute("categoryList",categoryList);
        return "consult_statistics/person_consult";
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
    public Table personQuery(Model model, String startDate, String endDate, Long shopId,String categoryName,
                             @RequestParam(value = "start", defaultValue = "0") int start,
                             @RequestParam(value = "length", defaultValue = "10") int length){

        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);
        //个人统计，共用一个业务逻辑
        int pageCount = statisticOrderMapper.countTotalPageByPerson(startDate,endDate,shopId);

        List<StatisticOrder> statisticOrderList = statisticOrderMapper.findConsultPersonStatisticOrder(startDate,endDate,shopId,categoryName,pageHelper);

        return new Table(pageCount, pageCount, statisticOrderList);
    }

    @RequestMapping("shop-consult")
    public String shopConsult(Model model){
        List<Shop> shopList = shopMapper.findAll();
        model.addAttribute("shopList",shopList);
        List<Category> categoryList = categoryMapper.findAllFirstClass();
        model.addAttribute("categoryList",categoryList);
        return "consult_statistics/shop_consult";
    }

    @ResponseBody
    @RequestMapping("shop-query")
    public Table shopQuery(Model model, String startDate, String endDate, Long shopId,String categoryName,String type,
                             @RequestParam(value = "start", defaultValue = "0") int start,
                             @RequestParam(value = "length", defaultValue = "10") int length){

        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);
        //个人统计，共用一个业务逻辑
        int pageCount = statisticOrderMapper.countTotalPageByConsultShop(startDate,endDate,shopId,categoryName,type);

        List<StatisticOrder> statisticOrderList = statisticOrderMapper.findConsultShopStatisticOrder(startDate,endDate,shopId,categoryName,type,pageHelper);

        return new Table(pageCount, pageCount, statisticOrderList);
    }

    @RequestMapping("total")
    public String totalConsult(){

        return "consult_statistics/total_consult_statistics";
    }

    @ResponseBody
    @RequestMapping("my-month-data")
    public ResultData myConsultMonthData(){
        List<Integer> sumList = getMonthIntegers(UserUtil.getCurrentUser().getId(),null,null);
        return ResultData.ok().putDataValue("sum",sumList);
    }

    private List<Integer> getMonthIntegers(Long userId,String categoryName,Long shopId) {
        List<MonthData> monthDataList = consultOrderMapper.findByMonth(userId,categoryName,
                 shopId);
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


    @ResponseBody
    @RequestMapping("my-month-category-data")
    public ResultData myConsultCategoryMonthData(){
        List<Category> categoryList = categoryMapper.findAllFirstClass();
        List<BarMonthData> barMonthDataList = new ArrayList<>();
        for (Category category: categoryList) {
            BarMonthData barMonthData = new BarMonthData();
            barMonthData.setName(category.getName());
            List<Integer> sumList = getMonthIntegers(UserUtil.getCurrentUser().getId(),category.getName(),null);
            barMonthData.setData(sumList);
            barMonthDataList.add(barMonthData);
        }

        return ResultData.ok().putDataValue("series",barMonthDataList);

    }

    @ResponseBody
    @RequestMapping("shop-month-data")
    public ResultData shopConsultMonthData(){
        List<Integer> sumList = getMonthIntegers(null,null,UserUtil.getCurrentUser().getShop_id());
        return ResultData.ok().putDataValue("sum",sumList);
    }

    @ResponseBody
    @RequestMapping("shop-month-category-data")
    public ResultData shopConsultCategoryMonthData(){
        List<Category> categoryList = categoryMapper.findAllFirstClass();
        List<BarMonthData> barMonthDataList = new ArrayList<>();
        for (Category category: categoryList) {
            BarMonthData barMonthData = new BarMonthData();
            barMonthData.setName(category.getName());
            List<Integer> sumList = getMonthIntegers(null,category.getName(),UserUtil.getCurrentUser().getShop_id());
            barMonthData.setData(sumList);
            barMonthDataList.add(barMonthData);
        }

        return ResultData.ok().putDataValue("series",barMonthDataList);

    }

    @ResponseBody
    @RequestMapping("total-month-data")
    public ResultData totalConsultMonthData(){
        List<Integer> sumList = getMonthIntegers(null,null,null);
        return ResultData.ok().putDataValue("sum",sumList);
    }

    @ResponseBody
    @RequestMapping("total-month-category-data")
    public ResultData totalConsultCategoryMonthData(){
        List<Category> categoryList = categoryMapper.findAllFirstClass();
        List<BarMonthData> barMonthDataList = new ArrayList<>();
        for (Category category: categoryList) {
            BarMonthData barMonthData = new BarMonthData();
            barMonthData.setName(category.getName());
            List<Integer> sumList = getMonthIntegers(null,category.getName(),null);
            barMonthData.setData(sumList);
            barMonthDataList.add(barMonthData);
        }

        return ResultData.ok().putDataValue("series",barMonthDataList);

    }





    @ResponseBody
    @RequestMapping("my-data")
    public ResultData myConsultData(){
        ArrayList<Integer> sumList = new ArrayList<Integer>();
        for (int i = 0; i < 12; i++) {
            sumList.add(new Random().nextInt(100));
        }
        return ResultData.ok().putDataValue("sum",sumList);
    }
}
