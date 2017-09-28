package com.yelot.crm.controller;

import com.yelot.crm.Util.ResultData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.entity.BarMonthData;
import com.yelot.crm.entity.Category;
import com.yelot.crm.entity.MonthData;
import com.yelot.crm.mapper.CategoryMapper;
import com.yelot.crm.mapper.RepairOrderMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("my")
    public String myMoney(){
        return "money_statistics/my_money_statistics";
    }

    @RequestMapping("shop")
    public String shopMoney(){

        return "money_statistics/shop_money_statistics";
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
}
