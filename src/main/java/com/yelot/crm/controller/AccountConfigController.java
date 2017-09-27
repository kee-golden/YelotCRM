package com.yelot.crm.controller;

import com.yelot.crm.Util.ResultData;
import com.yelot.crm.entity.AccountConfig;
import com.yelot.crm.mapper.AccountConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kee on 17/9/20.
 */
@Controller
@RequestMapping("account-config")
public class AccountConfigController {

    @Autowired
    private AccountConfigMapper accountConfigMapper;

    @ResponseBody
    @RequestMapping("save")
    public ResultData save(AccountConfig accountConfig){
        accountConfigMapper.update(accountConfig);
        return ResultData.ok();
    }

    @RequestMapping("find")
    public String find(@RequestParam(value = "id", defaultValue = "1")Long id, Model model){
        AccountConfig accountConfig = accountConfigMapper.find(id);
        model.addAttribute("bean",accountConfig);
        return "account_config/account_config_edit";
    }
}
