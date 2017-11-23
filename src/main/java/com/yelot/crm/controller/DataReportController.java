package com.yelot.crm.controller;

import com.alibaba.fastjson.JSON;
import com.yelot.crm.Util.DateUtil;
import com.yelot.crm.Util.ResultData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.entity.*;
import com.yelot.crm.mapper.ChannelSourceMapper;
import com.yelot.crm.mapper.DataReportMapper;
import com.yelot.crm.mapper.ShopMapper;
import com.yelot.crm.vo.ChartPieVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.*;

/**
 * 该类是根据最新的报表需求新增的统计类，参考的样本为"神策"分析
 * Created by kee on 17/11/11.
 */

@Controller
@RequestMapping("data-report")
public class DataReportController {
    @Autowired
    private ChannelSourceMapper channelSourceMapper;
    @Autowired
    private DataReportMapper dataReportMapper;
    @Autowired
    private ShopMapper shopMapper;

    private Logger log = LoggerFactory.getLogger(DataReportController.class);


    @RequestMapping("list")
    public String list(Model model){
        List<Shop> shopList = shopMapper.findAll();
        model.addAttribute("shopList",shopList);
        return "report/data_report";
    }

    /**
     * 咨询单来源统计数量
     * @param dateArea
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping("consult-channel-query")
    public ResultData consultChannelQuery(String dateArea, String type){

        List<String> xAxisList = getXAxis(dateArea,type);
        List<ChannelSource> channelSourceList = channelSourceMapper.findAll();
        List<String> channelSourceNameList = new ArrayList<String>();
        List<BarMonthData> barMonthDataList = new ArrayList<>();
        //查询逻辑，根据选择的日期分别来针对channelSource汇总
        for (int i = 0; i < channelSourceList.size(); i++) {
            channelSourceNameList.add(channelSourceList.get(i).getName());

            BarMonthData barMonthData = new BarMonthData();
            barMonthData.setName(channelSourceList.get(i).getName());
            barMonthData.setBarWidth(5);
            barMonthData.setStack("来源");
            List<Integer> sumList = getAmountByChannelSourceByConsultOrder(xAxisList,dateArea,channelSourceList.get(i).getId(),type);
            barMonthData.setData(sumList);
            barMonthDataList.add(barMonthData);
        }

//        log.info(JSON.toJSONString(barMonthDataList));

        return ResultData.ok().putDataValue("legend",channelSourceNameList).putDataValue("series",barMonthDataList).putDataValue("xAxis",xAxisList);

    }

    /**
     * 2,维修单来源数量统计
     * @param dateArea
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping("repair-channel-query")
    public ResultData repairChannelQuery(String dateArea, String type){

        List<String> xAxisList = getXAxis(dateArea,type);
        List<ChannelSource> channelSourceList = channelSourceMapper.findAll();
        List<String> channelSourceNameList = new ArrayList<String>();
        List<BarMonthData> barMonthDataList = new ArrayList<>();
        //查询逻辑，根据选择的日期分别来针对channelSource汇总
        for (int i = 0; i < channelSourceList.size(); i++) {
            channelSourceNameList.add(channelSourceList.get(i).getName());

            BarMonthData barMonthData = new BarMonthData();
            barMonthData.setName(channelSourceList.get(i).getName());
            barMonthData.setBarWidth(5);
            barMonthData.setStack("来源");
            List<Integer> sumList = getAmountByChannelSourceByRepairOrder(xAxisList,dateArea,channelSourceList.get(i).getId(),type);
            barMonthData.setData(sumList);
            barMonthDataList.add(barMonthData);
        }

        return ResultData.ok().putDataValue("legend",channelSourceNameList).putDataValue("series",barMonthDataList).putDataValue("xAxis",xAxisList);

    }

    /**
     * 3.	全部成交地域占比--件数，金额
     * @param dateArea
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping("repair-all-province-ratio")
    public ResultData repairOrderAllProvinceRatioQuery(String dateArea,String type){
//        List<String> allProvinces = dataReportMapper.findRepairAllProvince(dateArea.split("-")[0] + " 00:00:00",dateArea.split("-")[1] + " 23:59:59");
        List<StatisticOrder> statisticOrderList = dataReportMapper.repairOrderAllProvinceRatio(dateArea.split("-")[0] + " 00:00:00",dateArea.split("-")[1] + " 23:59:59",type);
        List<ChartPieVo> chartPieVoList = new ArrayList<>();
        for (StatisticOrder statisticOrder: statisticOrderList) {
            if(type.equals("TotalAmount")){
                ChartPieVo chartPieVo = new ChartPieVo(statisticOrder.getProvince(),statisticOrder.getTotalCount());
                chartPieVoList.add(chartPieVo);
            }else if(type.equals("TotalPayment")){
                ChartPieVo chartPieVo = new ChartPieVo(statisticOrder.getProvince(),statisticOrder.getTotalPayment());
                chartPieVoList.add(chartPieVo);
            }
        }

        if(chartPieVoList.size() == 0){
            ChartPieVo chartPieVo = new ChartPieVo("无数据",0);
            chartPieVoList.add(chartPieVo);
        }

        return ResultData.ok().putDataValue("chartPieVoList",chartPieVoList);

    }

    /**
     * 4.	门店成交地域占比--件数，金额：本市，本省（除本市外），全国范围（到省，直辖市级）
     * @param dateArea
     * @param shopId
     * @return
     */
    @ResponseBody
    @RequestMapping("repair-shop-province-ratio")
    public ResultData repairOrderByShopAndProvinceRatio(String dateArea,Long shopId,String type){
        Shop myShop = shopMapper.findById(shopId);
        String province = myShop.getProvince();
        String city = myShop.getCity();
        //查询门店本市的值，门店不包含4个直辖市
        StatisticOrder shopCityOrder;
        //查询本省的
        StatisticOrder shopProvinceOrder  = dataReportMapper.repairOrderShopProvince(dateArea.split("-")[0] + " 00:00:00",dateArea.split("-")[1] + " 23:59:59",shopId,type,province);
        ;
        //默认查询的除了门店本省的其他所有的，根据省统计
        List<StatisticOrder> statisticOrderList = dataReportMapper.repairOrderByShopAndProvinceRatio(dateArea.split("-")[0] + " 00:00:00",dateArea.split("-")[1] + " 23:59:59",shopId,type,province);

        List<ChartPieVo> chartPieVoList = new ArrayList<>();

        for (StatisticOrder statisticOrder: statisticOrderList) {
            if(type.equals("TotalAmount")){
                ChartPieVo chartPieVo = new ChartPieVo(statisticOrder.getProvince(),statisticOrder.getTotalCount());
                chartPieVoList.add(chartPieVo);
            }else if(type.equals("TotalPayment")){
                ChartPieVo chartPieVo = new ChartPieVo(statisticOrder.getProvince(),statisticOrder.getTotalPayment());
                chartPieVoList.add(chartPieVo);
            }
        }

        if(province.contains("上海") || province.contains("北京") || province.contains("天津") || province.contains("重庆")){
            //
            if(type.equals("TotalAmount") && shopProvinceOrder != null){
                ChartPieVo chartPieVo2 = new ChartPieVo(shopProvinceOrder.getProvince(),shopProvinceOrder.getTotalCount());
                chartPieVoList.add(chartPieVo2);

            }else if(type.equals("TotalPayment") && shopProvinceOrder != null){
                ChartPieVo chartPieVo2 = new ChartPieVo(shopProvinceOrder.getProvince(),shopProvinceOrder.getTotalPayment());
                chartPieVoList.add(chartPieVo2);
            }

        }else{//不是直辖市，需要算本市，和除了本市的本省的总计
            shopCityOrder = dataReportMapper.repairOrderShopCity(dateArea.split("-")[0] + " 00:00:00",dateArea.split("-")[1] + " 23:59:59",shopId,type,province,city);

            if(type.equals("TotalAmount") && shopCityOrder != null && shopProvinceOrder != null){
                ChartPieVo chartPieVo = new ChartPieVo(shopCityOrder.getCity(),shopCityOrder.getTotalCount());
                chartPieVoList.add(chartPieVo);
                ChartPieVo chartPieVo2 = new ChartPieVo(shopProvinceOrder.getProvince(),shopProvinceOrder.getTotalCount());
                chartPieVoList.add(chartPieVo2);

            }else if(type.equals("TotalPayment") && shopCityOrder != null && shopProvinceOrder != null ){
                ChartPieVo chartPieVo = new ChartPieVo(shopCityOrder.getCity(),shopCityOrder.getTotalPayment());
                chartPieVoList.add(chartPieVo);
                ChartPieVo chartPieVo2 = new ChartPieVo(shopProvinceOrder.getProvince(),shopProvinceOrder.getTotalPayment());
                chartPieVoList.add(chartPieVo2);
            }


        }

        if(chartPieVoList.size() == 0){
            ChartPieVo chartPieVo = new ChartPieVo("无数据",0);
            chartPieVoList.add(chartPieVo);
        }
        return ResultData.ok().putDataValue("chartPieVoList",chartPieVoList);

    }

    /**
     * 根据来源条件过滤，根据时间进行分类汇总，如：udesk下，时间从11-1到11-7 者7天每天的来源总量
     * @param xAxisList
     * @param dateArea
     * @param channelSourceId
     * @param type
     * @return
     */
    private List<Integer> getAmountByChannelSourceByConsultOrder(List<String> xAxisList,String dateArea, Long channelSourceId, String type) {

        List<DateNumber> dataList = dataReportMapper.findConsultChannelDataByType(dateArea.split("-")[0] + " 00:00:00",dateArea.split("-")[1] + " 23:59:59",
                channelSourceId+"",type);

        return setAllDateNumberList(xAxisList,dataList);

    }

    private List<Integer> getAmountByChannelSourceByRepairOrder(List<String> xAxisList,String dateArea, Long channelSourceId, String type) {

        List<DateNumber> dataList = dataReportMapper.findRepairChannelDataByType(dateArea.split("-")[0] + " 00:00:00",dateArea.split("-")[1] + " 23:59:59",
                channelSourceId+"",type);

        return setAllDateNumberList(xAxisList,dataList);

    }

    /**
     * 通过循环进行，缺失的日期进行补0
     * @param xAxis
     * @param dateNumberList
     * @return
     */
    private List<Integer> setAllDateNumberList(List<String> xAxis, List<DateNumber> dateNumberList){
        List<Integer> resultList = new ArrayList<Integer>();

        for (String str : xAxis) {
            DateNumber dateNumber = new DateNumber();
            dateNumber.setDate(str);
            dateNumber.setNumber(0);

            for (DateNumber dateNumber1 : dateNumberList) {
                if (str.equals(dateNumber1.getDate())) {
                    dateNumber = dateNumber1;
                    break;
                }
            }
            resultList.add(dateNumber.getNumber());
        }

        return resultList;
    }

    public List<String> getXAxis(String dateArea,String type){
        List<String> axisList = new ArrayList<>();
        try {
            if ("Day".equals(type)) {
                axisList = DateUtil.getDayBetweenStartAndEnd(dateArea.split("-")[0], dateArea.split("-")[1]);

            } else if ("Week".equals(type)) {
                axisList = DateUtil.getWeekBetweenStartAndEnd(dateArea.split("-")[0], dateArea.split("-")[1]);

            } else if ("Month".equals(type)) {
                axisList = DateUtil.getMonthBetweenStartAndEnd(dateArea.split("-")[0], dateArea.split("-")[1]);

            }
        }catch (ParseException e){

        }
        return axisList;
    }



}
