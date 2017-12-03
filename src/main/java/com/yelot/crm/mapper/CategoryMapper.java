package com.yelot.crm.mapper;

import com.yelot.crm.entity.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kee on 17/5/30.
 */
@Mapper
@Repository
public interface CategoryMapper {


    /**
     * 获取第一级分类
     * @return
     */
    List<Category> findAllFirstClass();

    /**
     * 获取某一个分类下的所有分类
     * @param parentId
     * @return
     */
    List<Category> findChildren(Long parentId);

    /**
     * 获取二级分类
     * @param parentName
     * @return
     */
    List<Category> findChildrenByParentName(String parentName);

    //获取二级分类对象
    Category findByName(@Param("firstCategory") String firstCategory, @Param("secondCategory") String secondCategory);

    Category find(Long id);
    Category findFirstCategory(String name);
}
