package com.yelot.crm.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kee on 17/9/29.
 */
public class BarMonthData {
    private String name;
    private String type = "bar";
    //显示在一根柱子上的参数设置
    private String stack = "分类";
    private  int barWidth = 10;
    private List<Integer> data;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public int getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(int barWidth) {
        this.barWidth = barWidth;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
