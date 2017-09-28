package com.yelot.crm.controller;

import com.yelot.crm.Util.ResultData;
import com.yelot.crm.mapper.ConsultOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kee on 17/9/28.
 */
@Controller
@RequestMapping("consult-statistics")
public class ConsultStatisticsController {

    @Autowired
    private ConsultOrderMapper consultOrderMapper;

    @RequestMapping("my")
    public String myConsult(){

        return "consult_statistics/my_consult_statistics";
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
