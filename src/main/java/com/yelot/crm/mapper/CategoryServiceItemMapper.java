package com.yelot.crm.mapper;

import com.yelot.crm.entity.Attribute;
import com.yelot.crm.entity.CategoryServiceItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kee on 17/8/8.
 */
@Mapper
@Repository
public interface CategoryServiceItemMapper {
    List<CategoryServiceItem> findByCategoryId(Long categoryId);
}
