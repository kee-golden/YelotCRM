package com.yelot.crm.mapper;

import com.yelot.crm.entity.ChannelSource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xyzloveabc
 * @2017年10月8日
 */
@Mapper
@Repository
public interface ChannelSourceMapper {
	

    List<ChannelSource> findAll();

    ChannelSource find(Long id);
    
}
