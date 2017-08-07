package com.yelot.crm.mapper;

import com.yelot.crm.entity.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kee on 17/8/7.
 */
@Mapper
@Repository
public interface BrandMapper {

    List<Brand> findAll();

}
