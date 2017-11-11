package com.yelot.crm.controller;


import com.alibaba.fastjson.JSON;
import com.yelot.crm.Util.Constants;
import com.yelot.crm.Util.DateUtil;
import com.yelot.crm.Util.ResponseData;
import com.yelot.crm.Util.ResultData;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.ChannelSource;
import com.yelot.crm.entity.Customer;
import com.yelot.crm.enums.AliveStatus;
import com.yelot.crm.mapper.ChannelSourceMapper;
import com.yelot.crm.mapper.CustomerMapper;
import com.yelot.crm.service.CustomerService;
import com.yelot.crm.service.RepairOrderService;
import com.yelot.crm.vo.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
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

    @Autowired
    private RepairOrderService repairOrderService;
    @Autowired
    private ChannelSourceMapper channelSourceMapper;

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
        List<Customer> customerList = customerMapper.findBySearch(pageHelper,extra_search);

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
        List<ChannelSource> channelSourceList = channelSourceMapper.findAll();
        model.addAttribute("channelSourceList",channelSourceList);

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
        List<ChannelSource> channelSourceList = channelSourceMapper.findAll();
        model.addAttribute("channelSourceList",channelSourceList);
        model.addAttribute("bean",customer);

        return "customer/customer_edit";

    }

    /**
     *
     * @param customer
     * @param firstConsultTime 涉及到时间字段，需要添加一个接受参数，否则无法自动转化date类型
     * @return
     */
    @ResponseBody
    @RequestMapping("save")
    public ResultData save(Customer customer,String firstConsultTime){
        Date date = new Date();
        if(firstConsultTime != null && firstConsultTime.trim() != ""){
            date = DateUtil.toDate(firstConsultTime, Constants.DefaultDateFormate);
        }
        customer.setFirstConsultAt(date);
         customerService.save(customer);
         ResultData resultData = ResultData.ok();
         resultData.putDataValue("customer",customer);
         return resultData;

    }

    @ResponseBody
    @RequestMapping("update")
    public ResponseData update(Customer customer){
        return customerService.update(customer);
    }

    public ResponseData updateAlive(Integer alive,Long id){
        return customerService.updateAlive(alive,id);
    }

    @RequestMapping("check-phone")
    public String checkPhone(String phone, HttpServletResponse response) throws IOException {
        List<Customer> customerList = customerMapper.findByPhone(phone);
        if (customerList != null && customerList.size() == 0) {//表示可用
            response.getWriter().write("true");
        } else {
            response.getWriter().write("false");
        }
        return null;
    }

    @RequestMapping("find-by-page")
    public List<Customer> findByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        if(page <= 0){
            page = 1;
        }
        Integer start = (page - 1) * size;
        return customerService.findByPage(start,size);
    }

    @ResponseBody
    @RequestMapping("delete")
    public ResultData delete(Long id){
        customerMapper.updateAlive(AliveStatus.DEAD.getCode(),id);
        return ResultData.ok();
    }

    /**
     * 该函数，在维修单创建时使用
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping("search")
    public ResultData search(String phone){

        List<Customer> customerList = customerMapper.findByPhone(phone);
        List<String> userOrderNo = repairOrderService.findUserOrderNoByPhone(phone, null);
        
        if(customerList != null && customerList.size() > 0){
            return ResultData.ok().putDataValue("customer",customerList.get(0)).putDataValue("userOrderNoJson", userOrderNo);
        }
        return ResultData.notFound();

    }

    @ResponseBody
    @RequestMapping("updateByRepairOrder")
    public ResponseData updateByRepairOrder(Long id, String otherPhone, String province, String city, String address){
        return customerService.updateByRepairOrder(id, otherPhone, province, city, address);
    }
}
