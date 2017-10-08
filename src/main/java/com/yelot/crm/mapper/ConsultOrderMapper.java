package com.yelot.crm.mapper;

import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.ConsultOrder;

import com.yelot.crm.entity.MonthData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kee on 17/8/22.
 */
@Mapper
@Repository
public interface ConsultOrderMapper {
    ConsultOrder find(Long id);

	int countTotalPageAll(@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("createUserName") String createUserName,
			@Param("extra_search") String extra_search);

	List<ConsultOrder> findByPageAll(@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("createUserName") String createUserName,
			@Param("extra_search") String extra_search,
			@Param("pageHelper") PageHelper pageHelper);

    int countTotalPageAllByPhone(@Param("phone")String phone);

    List<ConsultOrder> findByPageAllByPhone(@Param("phone") String phone, @Param("pageHelper") PageHelper pageHelper);

    void save(ConsultOrder consultOrder);

    void update(ConsultOrder consultOrder);

    void updateStatus(@Param("id") Long id, @Param("status") int status);

    List<MonthData> findByMonth(@Param("userId") Long userId,
								@Param("firstCategoryName") String firstCategoryName,
								@Param("shopId")Long shopId);

	/**
	 * 我的成交率统计
	 * @param userId
	 * @param shopId
	 * @return
	 */
	List<MonthData> findByMonthMyRadio(@Param("userId") Long userId,
									 @Param("firstCategoryName") String firstCategoryName,
									 @Param("shopId")Long shopId,@Param("status")Integer status);
}
