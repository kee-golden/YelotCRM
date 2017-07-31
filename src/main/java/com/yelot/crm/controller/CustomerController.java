package com.yelot.crm.controller;


import com.yelot.crm.Util.ResponseData;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Customer;
import com.yelot.crm.mapper.CustomerMapper;
import com.yelot.crm.service.CustomerService;
import com.yelot.crm.vo.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kee on 17/5/29.
 */
@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @RequestMapping("find")
    public Customer find(Long id){
        return customerService.find(id);
    }

    @RequestMapping("index")
    public String index(){
        return "customer/customer_index";
    }

    @RequestMapping("list")
    public String list(){
        return "customer/customer_list";
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
        int pageCount = customerMapper.countBySearch(extra_search);
        List<Customer> customerList = customerMapper.findBySearch(pageHelper);

        model.addAttribute("customerList", customerList);
        return new Table(pageCount, pageCount, customerList);
    }

    /**
     * 添加客户
     * @param model
     * @return
     */
    @RequestMapping("add")
    public String add(Model model) {
        Customer customer = new Customer();
        model.addAttribute("bean",customer);
        return "customer/customer_edit";
    }

    /**
     * 编辑客户
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("edit")
    public String edit(Model model, Long id) {
        Customer customer = customerMapper.find(id);
        model.addAttribute("bean",customer);

        return "customer/customer_edit";

    }

    @RequestMapping("save")
    public ResponseData save(Customer customer){
        return customerService.save(customer);
    }

    @RequestMapping("update")
    public ResponseData update(Customer customer){
        return customerService.update(customer);
    }

    public ResponseData updateAlive(Integer alive,Long id){
        return customerService.updateAlive(alive,id);
    }

    @RequestMapping("find-by-page")
    public List<Customer> findByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        if(page <= 0){
            page = 1;
        }
        Integer start = (page - 1) * size;
        return customerService.findByPage(start,size);
    }
}
