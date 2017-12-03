package com.yelot.crm.mapper;

import com.yelot.crm.entity.ChannelSource;
import com.yelot.crm.entity.DateNumber;
import com.yelot.crm.entity.MonthData;
import com.yelot.crm.entity.StatisticOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xyzloveabc
 * @2017年10月8日
 */
@Mapper
@Repository
public interface DataReportMapper {


    /**
     * 根据时间段查询出，对应的数量
     * 1,咨询来源数量
     * @param startDate
     * @param endDate
     * @param channelSourceId
     * @param type
     * @return
     */
    List<DateNumber> findConsultChannelDataByType(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                                  @Param("channelSourceId") String channelSourceId, @Param("type") String type);

    // 2,
    List<DateNumber> findRepairChannelDataByType(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                                 @Param("channelSourceId") String channelSourceId, @Param("type") String type);
//3.	全部成交地域占比--件数，金额
    List<StatisticOrder> findRepairProvince(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                            @Param("type") String type);
//3.	全部成交地域占比--件数
    List<String> findRepairAllProvince(@Param("startDate") String startDate, @Param("endDate") String endDate);
//3.	全部成交地域占比--件数,金额
    List<StatisticOrder> repairOrderAllProvinceRatio(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("type")String type);

    List<StatisticOrder> repairOrderByShopAndProvinceRatio(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                                           @Param("shopId")Long shopId,@Param("type") String type,@Param("province")String province);


    StatisticOrder repairOrderShopCity(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("shopId")Long shopId,@Param("type") String type,
                                       @Param("province")String province,@Param("city")String city);

    StatisticOrder repairOrderShopProvince(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("shopId")Long shopId,@Param("type") String type,
                                       @Param("province")String province);

    List<DateNumber> findRepairByDateSendType(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("sendType") String sendType, @Param("type") String type);
//6,
    List<StatisticOrder> consultOrderByProvince(@Param("startDate") String startDate, @Param("endDate") String endDate);

    //7,
    List<DateNumber> findRepairByDateCategoryType(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("firstCategoryId") Long firstCategoryId,
                                                  @Param("secondCategoryId") Long secondCategoryId,@Param("categoryType")String categoryType,@Param("type")String type);

    //8,
    List<DateNumber> findConsultByDateCategoryType(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("firstCategoryName") String firstCategoryName,
                                                   @Param("secondCategoryName") String secondCategoryName,@Param("type")String type);

    //9,品牌占比
    List<StatisticOrder> orderByBrand(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("orderType")String orderType);
    //10,
    List<DateNumber> findConsultFinishedDays(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("type") String dateType);
    //11,
}
