package com.yelot.crm.controller;

import com.yelot.crm.Util.ResultData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.entity.BarMonthData;
import com.yelot.crm.entity.Category;
import com.yelot.crm.entity.MonthData;
import com.yelot.crm.mapper.CategoryMapper;
import com.yelot.crm.mapper.ConsultOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("my")
    public String myConsult(){

        return "consult_statistics/my_consult_statistics";
    }

    @RequestMapping("shop")
    public String shopConsult(){

        return "consult_statistics/shop_consult_statistics";
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
