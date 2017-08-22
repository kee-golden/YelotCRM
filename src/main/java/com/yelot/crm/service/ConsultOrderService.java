package com.yelot.crm.service;

import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.ConsultOrder;
import com.yelot.crm.entity.RepairOrder;
import com.yelot.crm.mapper.ConsultOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kee on 17/8/22.
 */
@Service
public class ConsultOrderService {

    @Autowired
    private ConsultOrderMapper consultOrderMapper;


    public int countTotalPageAll(String extra_search) {
        return consultOrderMapper.countTotalPageAll(extra_search);
    }

    public List<ConsultOrder> findByPageAll(String extra_search, PageHelper pageHelper) {
        return consultOrderMapper.findByPageAll(extra_search,pageHelper);
    }
}
