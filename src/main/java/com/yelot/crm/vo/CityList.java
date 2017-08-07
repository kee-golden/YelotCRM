package com.yelot.crm.vo;

import java.util.ArrayList;
import java.util.List;

public class CityList{
    private String p;
    private List<City> c = new ArrayList<City>();

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public List<City> getC() {
        return c;
    }

    public void setC(List<City> c) {
        this.c = c;
    }
}