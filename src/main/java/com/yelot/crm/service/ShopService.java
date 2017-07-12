package com.yelot.crm.service;

import com.yelot.crm.Util.ResultData;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Shop;
import com.yelot.crm.mapper.ShopMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xyzloveabc
 * @2017年7月13日
 */
@Service
public class ShopService {

	@Autowired
	private ShopMapper shopMapper;

	/**
	 * 查询总的记录条数
	 * 
	 * @return 总的记录条数
	 */
	public Integer countTotalPage(String extra_search) {
		return shopMapper.countTotalPage(extra_search);
	}

	/**
	 * 分页查询
	 * 
	 * @param pageHelper
	 * @return
	 */
	public List<Shop> findByPage(String extra_search, PageHelper pageHelper) {
		return shopMapper.findByPage(extra_search, pageHelper);
	}

	/**
	 * 新建门店 业务逻辑：先判断名称是否存在，存在则提示用户
	 * 
	 * @param shop
	 * @return
	 */
	public ResultData save(Shop shop) {
		String name = shop.getName();
		Shop tempShop = shopMapper.findByName(name);
		// 查询不到重复名称，则新建
		if (tempShop == null) {
			shop.setCreate_at(new Date());
			shopMapper.save(shop);
			return ResultData.ok();
		}
		return ResultData.errorRequest().putDataValue("msg", "门店名称已存在");
	}

	/**
	 * 根据门店Id查询门店信息
	 * @param id
	 * @return
	 */
	public Shop findById(Long id) {
		return shopMapper.findById(id);
	}
	
	/**
	 * 更新门店信息
	 * @param shop
	 * @return
	 */
	public ResultData update(Shop shop) {
		String name = shop.getName();
		Shop tempShop = shopMapper.findByNameAndId(name, shop.getId());
		if (tempShop == null) {// 查询不到重复名称，则修改 shop.setUpdate_at(new Date());
			shop.setUpdate_at(new Date());
			shopMapper.update(shop);
			return ResultData.ok();
		}
		return ResultData.errorRequest().putDataValue("msg", "门店名称已存在");
	}

	/**
	 * 门店删除（设为无效）
	 * @param is_alive
	 * @param id
	 * @return
	 */
	public ResultData updateAlive(Integer is_alive, Long id) {
		shopMapper.updateAlive(is_alive, id);
		return ResultData.ok();
	}

}
