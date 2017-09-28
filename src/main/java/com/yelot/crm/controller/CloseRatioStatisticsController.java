package com.yelot.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kee on 17/9/29.
 */
@Controller
@RequestMapping("close-ratio-statistics")
public class CloseRatioStatisticsController {

    @RequestMapping("my")
    public String myCloseRadio(){

        return "close_ratio_statistics/my_close_ratio_statistics";
    }

    @RequestMapping("shop")
    public String shopCloseRadio(){

        return "close_ratio_statistics/shop_close_ratio_statistics";
    }

    @RequestMapping("total")
    public String totalCloseRadio(){

        return "close_ratio_statistics/total_close_ratio_statistics";
    }

}
