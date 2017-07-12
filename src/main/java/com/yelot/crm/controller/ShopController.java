package com.yelot.crm.controller;

import com.yelot.crm.Util.ResultData;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Shop;
import com.yelot.crm.service.ShopService;
import com.yelot.crm.vo.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author xyzloveabc
 * @2017年7月10日
 */
@Controller
@RequestMapping("shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 门店列表主页面显示
     * @return
     */
    @RequestMapping("list")
    public String list(Model model) {
        return "shop/list";
    }

    /**
     * 门店分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping("query")
    public Table queryShop(Model model,
        @RequestParam(value = "extra_search", defaultValue = "")String extra_search,
        @RequestParam(value = "start", defaultValue = "0") int start,
        @RequestParam(value = "length", defaultValue = "10") int length) {
    	
        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);
        
        int pageCount = shopService.countTotalPage(extra_search);
        List<Shop> shopList = shopService.findByPage(extra_search, pageHelper);
        return new Table(pageCount, pageCount, shopList);
    }

    /**
     * 门店新增页面
     * @param model
     * @return
     */
    @RequestMapping("add")
    public String add(Model model) {
        return "shop/edit";
    }

    /**
     * 门店新增
     * @param shop
     * @return
     */
    @ResponseBody
    @RequestMapping("save")
    public ResultData save(Shop shop) {
        return shopService.save(shop);
    }

    /**
     * 门店修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("edit")
    public String edit(Model model, Long id) {
    	Shop shop = new Shop();
    	if (id != null && !"".equals(id)) {
			shop = shopService.findById(id);
		}
        model.addAttribute("bean", shop);
        return "shop/edit";
    }

    /**
     * 门店修改
     * @param shop
     * @return
     */
    @ResponseBody
    @RequestMapping("update")
    public ResultData update(Shop shop) {
        return shopService.update(shop);
    }

    /**
     * 门店删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("delete")
    public ResultData delete(Long id) {
        return shopService.updateAlive(0, id);
    }
    
}
