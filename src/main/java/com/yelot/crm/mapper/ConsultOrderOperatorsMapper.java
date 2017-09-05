package com.yelot.crm.mapper;

import com.yelot.crm.entity.ConsultOrderOperators;
import com.yelot.crm.entity.RepairOrderOperators;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kee on 17/9/6.
 */
@Mapper
@Repository
public interface ConsultOrderOperatorsMapper {
    void save(ConsultOrderOperators consultOrderOperators);

    List<ConsultOrderOperators> findByOrderId(Long orderId);
}
