package com.yelot.crm.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yelot.crm.entity.CustomImages;
import com.yelot.crm.mapper.CustomImagesMapper;


/**
 * @author xyzloveabc
 * @2017年11月13日
 */
@Service
public class CustomImagesService {

	@Autowired
	private CustomImagesMapper customImagesMapper;
	
	public List<CustomImages> findByOrderId(Long orderId){
		
		List<CustomImages> customImagesList = customImagesMapper.findByOrderId(orderId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (CustomImages customImages : customImagesList) {
			customImages.setImagesList((customImages.getImagesJson() == null || "".equals(customImages.getImagesJson())) ? new ArrayList<String>() : Arrays.asList(customImages.getImagesJson().split(",")));
			customImages.setUpdateAtStr(sdf.format(customImages.getUpdateAt()));
		}
		
		return customImagesList;
	}

    public void saveCustomImages(CustomImages customImages) {
    	customImagesMapper.saveCustomImages(customImages);
    }
}
