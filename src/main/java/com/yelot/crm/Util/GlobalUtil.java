package com.yelot.crm.Util;

import com.yelot.crm.entity.StatisticOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kee on 17/10/8.
 */
public class GlobalUtil {


    public static List<String> getPersonList(List<StatisticOrder> statisticOrderList) {
        List<String> list = new ArrayList<>();
        for (StatisticOrder order:statisticOrderList) {
            list.add(order.getUserName());
            list.add(order.getRealname());
            list.add(order.getPhone());
            list.add(order.getShopName());
            list.add(order.getTotalPayment()+"");
            list.add(order.getTotalCount()+"");

        }
        return list;
    }

    public static List<String> getPersonConsultList(List<StatisticOrder> statisticOrderList) {
        List<String> list = new ArrayList<>();
        for (StatisticOrder order:statisticOrderList) {
            list.add(order.getUserName());
            list.add(order.getRealname());
            list.add(order.getPhone());
            list.add(order.getShopName());
            list.add(order.getTotalCount()+"");

        }
        return list;
    }

    public static List<String> getTimeConsultList(List<StatisticOrder> statisticOrderList) {
        List<String> list = new ArrayList<>();
        for (StatisticOrder order:statisticOrderList) {
            list.add(order.getTime());
            list.add(order.getTotalCount()+"");

        }
        return list;
    }

    public static List<String> getTimeRatioList(List<StatisticOrder> statisticOrderList) {
        List<String> list = new ArrayList<>();
        for (StatisticOrder order:statisticOrderList) {
            list.add(order.getTime());
            list.add(order.getTotalCount()+"");
            list.add(order.getTotalCount2()+"");

        }
        return list;
    }

    public static List<String> getTimeList(List<StatisticOrder> statisticOrderList) {
        List<String> list = new ArrayList<>();
        for (StatisticOrder order:statisticOrderList) {
            list.add(order.getTime());
            list.add(order.getTotalPayment()+"");
            list.add(order.getTotalCount()+"");

        }
        return list;
    }
}
