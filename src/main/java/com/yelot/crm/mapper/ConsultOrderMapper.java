package com.yelot.crm.mapper;

import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.ConsultOrder;
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

    int countTotalPageAll(@Param("extra_search")String extra_search);

    List<ConsultOrder> findByPageAll(@Param("extra_search") String extra_search, @Param("pageHelper") PageHelper pageHelper);

    void save(ConsultOrder consultOrder);

    void update(ConsultOrder consultOrder);
}
