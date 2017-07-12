package com.yelot.crm.mapper;

import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Shop;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kee on 17/5/26.
 */
@Mapper
@Repository
public interface ShopMapper {
	
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
	List<Shop> findByPage(@Param("extra_search") String extra_search, @Param("pageHelper") PageHelper pageHelper);
	
    /**
     * 按门店名字查询
     * @param name
     * @return
     */
    Shop findByName(String name);
    
    /**
     * 新增门店
     * @param shop
     */
    void save(Shop shop);
    
    /**
     * 查询门店信息
     * @param id
     * @return
     */
    Shop findById(Long id);

    /**
     * 查询除此id以外是否有重复的name
     * @param name
     * @param id
     * @return
     */
    Shop findByNameAndId(@Param("name") String name, @Param("id") Long id);
    
    /**
     * 修改门店信息
     * @param shop
     */
    void update(Shop shop);
    
    /**
     * 删除门店信息
     * @param is_alive
     * @param id
     */
    void updateAlive(@Param("is_alive") Integer is_alive, @Param("id") Long id);

    /**
     * 查询所有有效门店信息
     * @return
     */
    List<Shop> findAll();
    
}
