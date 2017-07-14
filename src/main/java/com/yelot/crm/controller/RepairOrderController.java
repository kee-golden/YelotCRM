package com.yelot.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kee on 17/7/13.
 */
@Controller
@RequestMapping("repair-order")
public class RepairOrderController {

    @RequestMapping("add")
    public String add(){

        return "repair_order/repair_order_add";
    }

    @RequestMapping("list")
    public String list(){
        return "repair_order/repair_order_list";
    }
}
