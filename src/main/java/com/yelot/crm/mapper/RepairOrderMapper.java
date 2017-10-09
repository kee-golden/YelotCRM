package com.yelot.crm.mapper;

import java.util.List;

import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.MonthData;
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
	Integer countTotalPage(@Param("extra_search") String extra_search, @Param("create_user_id") Long create_user_id, @Param("three_days_after") String three_days_after, @Param("type") String type);
	
	/**
	 * 分页查询
	 * @param extra_search
	 * @param pageHelper
	 * @return
	 */
	List<RepairOrder> findByPage(@Param("extra_search") String extra_search, @Param("create_user_id") Long create_user_id, @Param("three_days_after") String three_days_after, @Param("type") String type, @Param("pageHelper") PageHelper pageHelper);
	
	/**
	 * 查询服务名
	 * @param id
	 * @return
	 */
	String findServiceItemName(String id);

    int countTotalPageCheckList(@Param("extra_search") String extra_search, @Param("statusList") List<String> statusList, @Param("type") String type);

	List<RepairOrder> findByPageCheckList(@Param("extra_search") String extra_search, @Param("statusList") List<String> statusList,
										  @Param("pageHelper") PageHelper pageHelper, @Param("type") String type);

	/**
	 * 客服主管，仅仅查看自己门店的审核订单
	 * @param extra_search
	 * @param shopId
	 * @return
	 */
	int countTotalPageCheckListAndShop(@Param("extra_search") String extra_search, @Param("statusList") List<String> statusList,
									   @Param("shopId") Long shopId, @Param("type") String type);

	/**
	 * 	 * 客服主管，仅仅查看自己门店的审核订单
	 * @param extra_search
	 * @param pageHelper
	 * @param shopId
	 * @return
	 */
	List<RepairOrder> findByPageCheckListAndShop(@Param("extra_search") String extra_search, @Param("statusList") List<String> statusList,
												 @Param("pageHelper") PageHelper pageHelper,@Param("shopId") Long shopId, @Param("type") String type);

	void updateOrderStatusAndImagesPath(@Param("orderId") Long orderId,
			@Param("approveStatus") int approveStatus,
			@Param("imagesPath") String imagesPath,
			@Param("repairUserId") Long repairUserId,
			@Param("repairLastAt") String repairLastAt,
			@Param("labourPayment") Integer labourPayment,
			@Param("materialPayment") Integer materialPayment,
			@Param("discountAmountPayment") Integer discountAmountPayment,
			@Param("totalPayment") Integer totalPayment,
			@Param("discountDesc") String discountDesc);

    /**
     * 根据订单id查看订单详情
     * @param orderId
     * @return
     */
    public RepairOrder findRepairOrderByOrderId(Long orderId);

    int countByShopId(Long shopId);

	void update(RepairOrder repairOrder);

    List<MonthData> findByMonth(@Param("userId") Long userId,
								@Param("firstCategoryId") Long firstCategoryId,
								@Param("shopId")Long shopId);


	void updateTypeName(@Param("id") Long id, @Param("typeName") String typeName);
}
