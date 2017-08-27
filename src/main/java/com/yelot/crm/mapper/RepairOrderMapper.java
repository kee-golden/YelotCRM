package com.yelot.crm.mapper;

import java.util.List;

import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.RepairOrder;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by kee on 17/6/5.
 */
@Mapper
@Repository
public interface RepairOrderMapper {

    RepairOrder find(Long id);

    void save(RepairOrder repairOrder);


    @Select("select * from t_repare_order where order_no = #{order_no}")
    RepairOrder findByOrderNo(String order_no);
	
	/**
	 * 查询总的记录条数
	 * @param extra_search
	 * @return
	 */
	Integer countTotalPageMy(@Param("extra_search") String extra_search, @Param("create_user_id") Long create_user_id, @Param("approve_role_id") Long approve_role_id);
	
	/**
	 * 分页查询
	 * @param extra_search
	 * @param pageHelper
	 * @return
	 */
	List<RepairOrder> findByPageMy(@Param("extra_search") String extra_search, @Param("create_user_id") Long create_user_id, @Param("approve_role_id") Long approve_role_id, @Param("pageHelper") PageHelper pageHelper);
	
	/**
	 * 查询服务名
	 * @param id
	 * @return
	 */
	String findServiceItemName(String id);

    int countTotalPageCheckList(@Param("extra_search") String extra_search, @Param("statusString") String statusString);

	List<RepairOrder> findByPageCheckList(@Param("extra_search") String extra_search, @Param("statusString") String statusString,
										  @Param("pageHelper") PageHelper pageHelper);

	/**
	 * 客服主管，仅仅查看自己门店的审核订单
	 * @param extra_search
	 * @param statusString
	 * @param shopId
	 * @return
	 */
	int countTotalPageCheckListAndShop(@Param("extra_search") String extra_search, @Param("statusString") String statusString,
									   @Param("shopId") Long shopId);

	/**
	 * 	 * 客服主管，仅仅查看自己门店的审核订单
	 * @param extra_search
	 * @param statusString
	 * @param pageHelper
	 * @param shopId
	 * @return
	 */
	List<RepairOrder> findByPageCheckListAndShop(@Param("extra_search") String extra_search, @Param("statusString") String statusString,
												 @Param("pageHelper") PageHelper pageHelper,@Param("shopId") Long shopId);
}
