package com.yelot.crm.mapper;

import com.yelot.crm.entity.RepairOrder;
import com.yelot.crm.entity.RepairOrderOperators;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kee on 17/8/18.
 */
@Mapper
@Repository
public interface RepairOrderOperatorsMapper {

    void save(RepairOrderOperators repairOrderOperators);

    List<RepairOrderOperators> findByOrderId(Long orderId);
}
