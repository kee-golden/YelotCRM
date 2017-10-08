package com.yelot.crm.service;

import com.yelot.crm.Util.ResultData;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Brand;
import com.yelot.crm.mapper.BrandMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xyzloveabc
 * @2017年10月8日
 */
@Service
public class BrandService {

	@Autowired
	private BrandMapper brandMapper;

	/**
	 * 查询总的记录条数
	 * 
	 * @return 总的记录条数
	 */
	public Integer countTotalPage(String extra_search) {
		return brandMapper.countTotalPage(extra_search);
	}

	/**
	 * 分页查询
	 * 
	 * @param pageHelper
	 * @return
	 */
	public List<Brand> findByPage(String extra_search, PageHelper pageHelper) {
		return brandMapper.findByPage(extra_search, pageHelper);
	}
	
	/**
	 * 新建品牌 业务逻辑：先判断名称是否存在，存在则提示用户
	 * 
	 * @param brand
	 * @return
	 */
	public ResultData save(Brand brand) {
		String name = brand.getName();
		Brand tempBrand = brandMapper.findByName(name);
		
		// 查询不到重复名称，则新建
		if (tempBrand == null) {
			brandMapper.save(brand);
			return ResultData.ok();
		}
		return ResultData.errorRequest().putDataValue("msg", "品牌名称已存在");
	}

	/**
	 * 根据品牌Id查询品牌信息
	 * @param id
	 * @return
	 */
	public Brand findById(Long id) {
		return brandMapper.findById(id);
	}
	
	/**
	 * 更新品牌信息
	 * @param brand
	 * @return
	 */
	public ResultData update(Brand brand) {
		String name = brand.getName();
		Brand tempBrand = brandMapper.findByNameAndId(name, brand.getId());
		
		// 查询不到重复名称，新增
		if (tempBrand == null) {
			brandMapper.update(brand);
			return ResultData.ok();
		}
		return ResultData.errorRequest().putDataValue("msg", "品牌名称已存在");
	}

	/**
	 * 品牌删除（设为无效）
	 * @param is_alive
	 * @param id
	 * @return
	 */
	public ResultData updateAlive(Integer is_alive, Long id) {
		brandMapper.updateAlive(is_alive, id);
		return ResultData.ok();
	}

}
