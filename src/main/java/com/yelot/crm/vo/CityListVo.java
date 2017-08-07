package com.yelot.crm.vo;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用CitySelect.js 插件，来展示二级联动菜单
 * Created by kee on 17/8/6.
 *
 * {"citylist":[
 {"p":"前端技术","c":[{"n":"HTML"},{"n":"CSS"},{"n":"JAVASCIPT"}]},
 {"p":"编程语言","c":[{"n":"C"},{"n":"C++"},{"n":"Objective-C"},{"n":"PHP"},{"n":"JAVA"}]},
 {"p":"数据库","c":[{"n":"Mysql"},{"n":"SqlServer"},{"n":"Oracle"},{"n":"DB2"}]},
 ]}
 *
 */
public class CityListVo {

    private List<CityList> citylist = new ArrayList<CityList>();

    public List<CityList> getCitylist() {
        return citylist;
    }

    public void setCitylist(List<CityList> citylist) {
        this.citylist = citylist;
    }

    public static void main(String[] args) {
        City html = new City("html");
        City css = new City("css");
        City js = new City("js");
        City c = new City("C");
        City cp = new City("C++");
        City php = new City("php");
        City mysql = new City("mysql");
        City oracle = new City("oracle");
        City sqlserver = new City("sqlserver");

        CityList cityList = new CityList();
        cityList.setP("前端技术");
        cityList.getC().add(html);
        cityList.getC().add(css);
        cityList.getC().add(js);

        CityList cityList1 = new CityList();
        cityList1.setP("编程语言");
        cityList1.getC().add(c);
        cityList1.getC().add(cp);
        cityList1.getC().add(php);

        CityList cityList2 = new CityList();
        cityList2.setP("数据库");
        cityList2.getC().add(mysql);
        cityList2.getC().add(oracle);
        cityList2.getC().add(sqlserver);

        CityListVo cityListVo = new CityListVo();
        cityListVo.getCitylist().add(cityList);
        cityListVo.getCitylist().add(cityList1);
        cityListVo.getCitylist().add(cityList2);

        String jsonStr = JSON.toJSONString(cityListVo);
        System.out.println(jsonStr);
    }
}




