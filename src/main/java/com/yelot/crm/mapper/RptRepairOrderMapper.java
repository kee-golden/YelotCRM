package com.yelot.crm.mapper;

import java.util.List;

import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.RptRepairOrder;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xyzloveabc
 * @2017年9月16日
 */
@Mapper
@Repository
public interface RptRepairOrderMapper {
	/**
	 * 查询总的记录条数
	 * 
	 * @param extra_search
	 * @return
	 */
	Integer countTotalPage(@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("firstCategory") String firstCategory,
			@Param("secondCategory") String secondCategory,
			@Param("shopId") String shopId,
			@Param("customerType") String customerType,
			@Param("status") String status, @Param("typeName") String typeName);

	/**
	 * 分页查询
	 * 
	 * @param pageHelper
	 * @return
	 */
	List<RptRepairOrder> findByPage(@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("firstCategory") String firstCategory,
			@Param("secondCategory") String secondCategory,
			@Param("shopId") String shopId,
			@Param("customerType") String customerType,
			@Param("status") String status, @Param("typeName") String typeName,
			@Param("pageHelper") PageHelper pageHelper);

	/**
	 * 查询服务名
	 * 
	 * @param id
	 * @return
	 */
	String findServiceItemName(String id);

}
