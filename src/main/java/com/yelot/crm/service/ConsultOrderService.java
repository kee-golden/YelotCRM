package com.yelot.crm.service;

import com.yelot.crm.Util.DateUtil;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.ConsultOrder;
import com.yelot.crm.entity.User;
import com.yelot.crm.enums.ConsultOrderStatus;
import com.yelot.crm.mapper.ConsultOrderMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by kee on 17/8/22.
 */
@Service
public class ConsultOrderService {

    @Autowired
    private ConsultOrderMapper consultOrderMapper;


	public int countTotalPageAll(String startDate, String endDate,
			String createUserName, String extra_search) {
        return consultOrderMapper.countTotalPageAll(startDate, endDate, createUserName, extra_search);
    }

    public List<ConsultOrder> findByPageAll(String startDate, String endDate,
			String createUserName, String extra_search, PageHelper pageHelper) {
        return consultOrderMapper.findByPageAll(startDate, endDate, createUserName, extra_search, pageHelper);
    }

    public int countTotalPageAllByPhone(String phone) {
        return consultOrderMapper.countTotalPageAllByPhone(phone);
    }

    public List<ConsultOrder> findByPageAllByPhone(String phone, PageHelper pageHelper) {
        return consultOrderMapper.findByPageAllByPhone(phone,pageHelper);
    }
    
    public ConsultOrder findConsultOrderByConsultOrderNo(String consultOrderNo){
    	return consultOrderMapper.findConsultOrderByConsultOrderNo(consultOrderNo);
    }

    public void save(ConsultOrder consultOrder){
        User currentUser = UserUtil.getCurrentUser();
        
        String strDate = DateUtil.toString(new Date(),"yyyyMMdd");
        int todayCount = consultOrderMapper.findTodayCount();
        consultOrder.setOrderNo(strDate + todayCount);
        
        consultOrder.setCreateUserId(currentUser.getId());
        consultOrder.setCreateAt(new Date());
        consultOrder.setShopId(currentUser.getShop_id());
        consultOrder.setStatus(ConsultOrderStatus.OnGoing.getCode());
        consultOrderMapper.save(consultOrder);
    }

    public void update(ConsultOrder consultOrder) {
        consultOrderMapper.update(consultOrder);
    }
}
