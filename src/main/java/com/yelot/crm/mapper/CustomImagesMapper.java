package com.yelot.crm.mapper;

import com.yelot.crm.entity.CustomImages;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xyzloveabc
 * @2017年11月13日
 */
@Mapper
@Repository
public interface CustomImagesMapper {
	
    List<CustomImages> findByOrderId(Long orderId);

	void saveCustomImages(CustomImages customImages);
}
