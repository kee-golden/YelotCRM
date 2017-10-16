package com.yelot.crm.mapper;

import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Express;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kee on 17/10/8.
 */
@Mapper
@Repository
public interface ExpressMapper {
    int countBySearch(@Param("extra_search")String extra_search);

    List<Express> findBySearch(@Param("pageHelper") PageHelper pageHelper, @Param("extra_search")String extra_search);

    Express find(Long id);

    void save(Express express);

    void update(Express express);

    void delete(Long id);
    
    Express findExpressByNameAndNo(@Param("expressName")String expressName, @Param("expressNo")String expressNo);
}
