package com.yelot.crm.vo;

/**
 * Created by kee on 17/11/18.
 */
public class ChartPieVo {
    private Integer value;
    private String name;

    public ChartPieVo(){

    }

    public ChartPieVo(String name,Integer value){
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
