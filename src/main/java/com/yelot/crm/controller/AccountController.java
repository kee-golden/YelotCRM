package com.yelot.crm.controller;

import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Account;
import com.yelot.crm.mapper.AccountMapper;
import com.yelot.crm.vo.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kee on 17/9/20.
 */
@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountMapper accountMapper;

    @RequestMapping("index")
    public String index(){
        return "account/account_index";
    }

    @RequestMapping("list")
    public String list(){
        return "account/account_list";
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
        int pageCount = accountMapper.countBySearch(extra_search);
        List<Account> customerList = accountMapper.findBySearch(pageHelper,extra_search);

        model.addAttribute("customerList", customerList);

        return new Table(pageCount, pageCount, customerList);
    }
}
