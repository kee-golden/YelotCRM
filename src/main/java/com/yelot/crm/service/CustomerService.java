package com.yelot.crm.service;

import com.yelot.crm.Util.ResponseData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.entity.Customer;
import com.yelot.crm.enums.AliveStatus;
import com.yelot.crm.mapper.CategoryMapper;
import com.yelot.crm.mapper.CustomerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by kee on 17/5/28.
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    public Customer find(Long id) {
        return customerMapper.find(id);
    }

    public Customer save(Customer customer) {
        Customer resultTemp = customer;
        Long id = customer.getId();
        if(id == null){
            Date now = new Date();
            customer.setCreateAt(now);
            customer.setUpdateAt(now);
            customer.setIs_alive(AliveStatus.ALIVE.getCode());
            customer.setCreateUserId(UserUtil.getCurrentUser().getId());
            customerMapper.save(customer);
        }else{
            customerMapper.updateCustomer(customer);
        }
        return resultTemp;
    }


    public List<Customer> findByPage(Integer start, Integer size) {
        return customerMapper.findByPage(start,size);
    }

    public ResponseData update(Customer customer) {
        customerMapper.updateCustomer(customer);
        return new ResponseData(ResponseData.SUCCESS,ResponseData.SUCCESS_MESSAGE);
    }

    public ResponseData updateAlive(Integer alive, Long id) {
        customerMapper.updateAlive(alive,id);
        return new ResponseData(ResponseData.SUCCESS,ResponseData.SUCCESS_MESSAGE);
    }

    public ResponseData updateByRepairOrder(Long id, String otherPhone, String province, String city, String address) {
        customerMapper.updateByRepairOrder(id, otherPhone, province, city, address);
        return new ResponseData(ResponseData.SUCCESS,ResponseData.SUCCESS_MESSAGE);
    }

}
