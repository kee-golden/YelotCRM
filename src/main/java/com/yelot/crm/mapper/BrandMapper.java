package com.yelot.crm.mapper;

import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Brand;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xyzloveabc
 * @2017年10月8日
 */
@Mapper
@Repository
public interface BrandMapper {
	
	/**
	 * 查询总的记录条数
	 * @param extra_search
	 * @return
	 */
	Integer countTotalPage(@Param("extra_search") String extra_search);
	
	/**
	 * 分页查询
	 * @param extra_search
	 * @param pageHelper
	 * @return
	 */
	List<Brand> findByPage(@Param("extra_search") String extra_search, @Param("pageHelper") PageHelper pageHelper);
	
    /**
     * 按品牌名字查询
     * @param name
     * @return
     */
    Brand findByName(String name);
    
    /**
     * 新增品牌
     * @param brand
     */
    void save(Brand brand);
    
    /**
     * 查询品牌信息
     * @param id
     * @return
     */
    Brand findById(Long id);

    /**
     * 查询除此id以外是否有重复的name
     * @param name
     * @param id
     * @return
     */
    Brand findByNameAndId(@Param("name") String name, @Param("id") Long id);
    
    /**
     * 修改品牌信息
     * @param brand
     */
    void update(Brand brand);
    
    /**
     * 删除品牌信息
     * @param is_alive
     * @param id
     */
    void updateAlive(@Param("is_alive") Integer is_alive, @Param("id") Long id);

    /**
     * 查询所有有效品牌信息
     * @return
     */
    List<Brand> findAll();

    Brand find(Long id);
    
}
