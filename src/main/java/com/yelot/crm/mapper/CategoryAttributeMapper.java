package com.yelot.crm.mapper;

import com.yelot.crm.entity.CategoryAttribute;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by kee on 17/8/8.
 */
@Mapper
@Repository
public interface CategoryAttributeMapper {
    CategoryAttribute find(Long id);

    CategoryAttribute findByCategoryId(Long categoryId);
}
