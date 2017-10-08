package com.yelot.crm.controller;

import com.yelot.crm.Util.Constants;
import com.yelot.crm.Util.DateUtil;
import com.yelot.crm.Util.ResultData;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Customer;
import com.yelot.crm.entity.Express;
import com.yelot.crm.mapper.ExpressMapper;
import com.yelot.crm.vo.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by kee on 17/10/8.
 */
@Controller
@RequestMapping("express")
public class ExpressController {
    @Autowired
    private ExpressMapper expressMapper;

    @RequestMapping("index")
    public String index(){
        return "express/express_index";
    }

    @RequestMapping("list")
    public String list(){
        return "express/express_list";
    }

    @ResponseBody
    @RequestMapping("list-data")
    public Table userListData(Model model,
                              @RequestParam(value = "extra_search", defaultValue = "")String extra_search,
                              @RequestParam(value = "start", defaultValue = "0") int start,
                              @RequestParam(value = "length", defaultValue = "10") int length) {

        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);
        int pageCount = expressMapper.countBySearch(extra_search);
        List<Express> expressList = expressMapper.findBySearch(pageHelper,extra_search);

        model.addAttribute("expressList", expressList);

        return new Table(pageCount, pageCount, expressList);
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("add")
    public String add(Model model) {
        Express express = new Express();
        model.addAttribute("bean",express);
        return "express/express_edit";
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("edit")
    public String edit(Model model, Long id) {
        Express express = expressMapper.find(id);
        model.addAttribute("bean",express);

        return "express/express_edit";

    }

    @ResponseBody
    @RequestMapping("save")
    public ResultData save(Express express, String firstConsultTime){
        Date date = new Date();
        if(firstConsultTime != null && firstConsultTime.trim() != ""){
            date = DateUtil.toDate(firstConsultTime, Constants.DefaultDateFormate);
        }
        expressMapper.save(express);
        ResultData resultData = ResultData.ok();
        resultData.putDataValue("express",express);
        return resultData;

    }
}
