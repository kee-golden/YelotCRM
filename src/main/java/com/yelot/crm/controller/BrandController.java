package com.yelot.crm.controller;

import com.yelot.crm.Util.ResultData;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Brand;
import com.yelot.crm.service.BrandService;
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
 * @2017年10月8日
 */
@Controller
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 品牌列表主页面显示
     * @return
     */
    @RequestMapping("list")
    public String list(Model model) {
        return "brand/list";
    }

    /**
     * 品牌分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping("query")
    public Table queryBrand(Model model,
        @RequestParam(value = "extra_search", defaultValue = "")String extra_search,
        @RequestParam(value = "start", defaultValue = "0") int start,
        @RequestParam(value = "length", defaultValue = "10") int length) {
    	
        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);
        
        int pageCount = brandService.countTotalPage(extra_search);
        List<Brand> brandList = brandService.findByPage(extra_search, pageHelper);
        return new Table(pageCount, pageCount, brandList);
    }
    
    /**
     * 品牌新增页面
     * @param model
     * @return
     */
    @RequestMapping("add")
    public String add(Model model) {
        return "brand/edit";
    }

    /**
     * 品牌新增
     * @param brand
     * @return
     */
    @ResponseBody
    @RequestMapping("save")
    public ResultData save(Brand brand) {
        return brandService.save(brand);
    }

    /**
     * 品牌修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("edit")
    public String edit(Model model, Long id) {
    	Brand brand = new Brand();
    	if (id != null && !"".equals(id)) {
			brand = brandService.findById(id);
		}
        model.addAttribute("bean", brand);
        return "brand/edit";
    }

    /**
     * 品牌修改
     * @param brand
     * @return
     */
    @ResponseBody
    @RequestMapping("update")
    public ResultData update(Brand brand) {
        return brandService.update(brand);
    }

    /**
     * 品牌删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("delete")
    public ResultData delete(Long id) {
        return brandService.updateAlive(0, id);
    }
    
}
