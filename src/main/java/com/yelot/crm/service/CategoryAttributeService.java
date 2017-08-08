package com.yelot.crm.service;

import com.yelot.crm.entity.Attribute;
import com.yelot.crm.entity.CategoryAttribute;
import com.yelot.crm.mapper.AttributeMapper;
import com.yelot.crm.mapper.CategoryAttributeMapper;
import com.yelot.crm.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kee on 17/8/8.
 */
@Service
public class CategoryAttributeService {

    @Autowired
    private CategoryAttributeMapper categoryAttributeMapper;

    @Autowired
    private AttributeMapper attributeMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 通过二级分类的名称，获取属性列表，二级分类必须在系统中唯一
     * @param secondCategory
     * @return
     */
    public List<Attribute> findAttributes(String firstCategory,String secondCategory){

        Long categoryId = categoryMapper.findByName(firstCategory,secondCategory).getId();
        CategoryAttribute categoryAttribute = categoryAttributeMapper.findByCategoryId(categoryId);
        if(categoryAttribute == null){
            return null;
        }
        String attributeIds [] = categoryAttribute.getAttribute_ids().split(",");
        List<Attribute> attributeList = new ArrayList<Attribute>();
        for (int i = 0; attributeIds != null && i < attributeIds.length; i++) {
            Attribute attribute = attributeMapper.find(Long.valueOf(attributeIds[i]));
            attributeList.add(attribute);
        }

        return attributeList;

    }
}
