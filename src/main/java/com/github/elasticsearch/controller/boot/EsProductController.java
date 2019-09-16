package com.github.elasticsearch.controller.boot;

import com.github.elasticsearch.domain.boot.EsProduct;
import com.github.elasticsearch.service.boot.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 搜索商品管理Controller
 */
@Controller
@RequestMapping("/esProduct")
public class EsProductController {
    @Autowired
    private EsProductService esProductService;


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        esProductService.delete(id);
    }

    /**
     * 添加到es
     */
    @RequestMapping(value = "/create/", method = RequestMethod.POST)
    @ResponseBody
    public int create() {
        return esProductService.create();
    }

    /**
     * 添加到es
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    @ResponseBody
    public int update(@PathVariable Long id) {
       return esProductService.update(id);
    }


    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    @ResponseBody
    public Page<EsProduct> search(@RequestParam(required = false) String keyword,
                                  @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        return esProductPage;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Page<EsProduct> search(@RequestParam(required = false) String keyword,
                                  @RequestParam(required = false) Long brandId,
                                  @RequestParam(required = false) Long productCategoryId,
                                  @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                  @RequestParam(required = false, defaultValue = "0") Integer sort) {
        Page<EsProduct> esProductPage = esProductService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
        return esProductPage;
    }


}
