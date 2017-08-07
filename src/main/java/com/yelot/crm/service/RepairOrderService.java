package com.yelot.crm.service;

import com.alibaba.fastjson.JSON;
import com.yelot.crm.entity.Category;
import com.yelot.crm.entity.RepareOrder;
import com.yelot.crm.entity.RepareOrderItem;
import com.yelot.crm.entity.RepareOrderItemImage;
import com.yelot.crm.mapper.CategoryMapper;
import com.yelot.crm.mapper.RepareOrderItemImgMapper;
import com.yelot.crm.mapper.RepareOrderItemMapper;
import com.yelot.crm.mapper.RepareOrderMapper;
import com.yelot.crm.vo.City;
import com.yelot.crm.vo.CityList;
import com.yelot.crm.vo.CityListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kee on 17/6/5.
 */
@Service
@Transactional
public class RepairOrderService {

    @Autowired
    private RepareOrderMapper repareOrderMapper;

    @Autowired
    private RepareOrderItemMapper repareOrderItemMapper;

    @Autowired
    private RepareOrderItemImgMapper repareOrderItemImgMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    RepareOrder find(Long id){
        return repareOrderMapper.find(id);
    }

    /**
     * 保存订单业务逻辑
     * @param repareOrder
     * @param repareOrderItem
     * @param repareOrderItemImage
     */
    void save(RepareOrder repareOrder, RepareOrderItem repareOrderItem, RepareOrderItemImage repareOrderItemImage){
        //step 1:首先保存订单表
        repareOrderMapper.save(repareOrder);
        RepareOrder repareOrder1 = repareOrderMapper.findByOrderNo(repareOrder.getOrder_no());

        //step 2:保存item表
        repareOrderItem.setRepare_order_id(repareOrder1.getId());
        repareOrderItemMapper.save(repareOrderItem);

        //step 3:保存图片
        List<RepareOrderItem> repareOrderItemList = repareOrderItemMapper.findByOrderId(repareOrder1.getId());
        if(repareOrderItemList != null && repareOrderItemList.size()>0){
            //当前不用修改，一个订单中只有一个item,取第一个也是唯一的一个即可。后期如果扩展成多个，需要修改.
            repareOrderItemImage.setRepare_order_item_id(repareOrderItemList.get(0).getId());
        }
        repareOrderItemImgMapper.save(repareOrderItemImage);
    }


    /**
     * 分类转化为CityList
     * @return
     */
    public CityListVo convertToCityListVo() {
        List<Category> categoryList = categoryMapper.findAllFirstClass();

        for (int i = 0; categoryList!= null && i < categoryList.size(); i++) {
            List<Category> children = categoryMapper.findChildren(categoryList.get(i).getId());
            categoryList.get(i).setChildren(children);
        }

        /**
         * 以下对象转化为，需要的层次结构
         */
        CityListVo cityListVo = new CityListVo();

        for (int i = 0; categoryList != null && i < categoryList.size(); i++) {
            List<Category> children = categoryList.get(i).getChildren();
            String p = categoryList.get(i).getName();
            CityList cityList = new CityList();
            cityList.setP(p);
            if(children != null && children.size() > 0){
                for (int j = 0; j < children.size(); j++) {
                    City city = new City();
                    city.setN(children.get(j).getName());
                    cityList.getC().add(city);
                }
            }
            cityListVo.getCitylist().add(cityList);
        }

        return cityListVo;
    }
}
