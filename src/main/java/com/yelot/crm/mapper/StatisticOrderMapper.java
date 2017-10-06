package com.yelot.crm.mapper;

import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.StatisticOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kee on 17/10/4.
 */
@Mapper
@Repository
public interface StatisticOrderMapper {

    /**
     * 个人统计，分页查询
     * @param startDate
     * @param endDate
     * @param shopId
     * @return
     */
    int countTotalPageByPerson(@Param("startDate") String startDate, @Param("endDate") String endDate,
                               @Param("shopId") Long shopId);

    List<StatisticOrder> findPersonStatisticOrder(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                                  @Param("shopId") Long shopId,@Param("categoryId")Long categoryId, @Param("pageHelper") PageHelper pageHelper);


    int countTotalPageByShop(@Param("startDate") String startDate, @Param("endDate") String endDate,
                             @Param("shopId")Long shopId, @Param("categoryId")Long categoryId,@Param("type") String type);

    List<StatisticOrder> findShopStatisticOrder(
            @Param("startDate") String startDate, @Param("endDate") String endDate,
            @Param("shopId") Long shopId, @Param("categoryId")Long categoryId,@Param("type")String type, @Param("pageHelper") PageHelper pageHelper);

    /**
     * 咨询单，个人统计
     * @param startDate
     * @param endDate
     * @param shopId
     * @return
     */
    int countTotalPageByConsultPerson(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                      @Param("shopId") Long shopId,@Param("categoryName")String  categoryName);

    /**
     * 咨询单，个人统计
     * @param startDate
     * @param endDate
     * @param shopId
     * @param pageHelper
     * @return
     */
    List<StatisticOrder> findConsultPersonStatisticOrder(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                                  @Param("shopId") Long shopId, @Param("categoryName")String categoryName,@Param("pageHelper") PageHelper pageHelper);

    /**
     * 咨询单，门店统计
     * @param startDate
     * @param endDate
     * @param shopId
     * @param type
     * @return
     */
    int countTotalPageByConsultShop(@Param("startDate") String startDate, @Param("endDate") String endDate,
                             @Param("shopId")Long shopId, @Param("categoryName")String categoryName,@Param("type") String type);

    List<StatisticOrder> findConsultShopStatisticOrder(
            @Param("startDate") String startDate, @Param("endDate") String endDate,
            @Param("shopId") Long shopId, @Param("categoryName")String categoryName,@Param("type")String type, @Param("pageHelper") PageHelper pageHelper);
}
