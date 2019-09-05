package com.github.elasticsearch.service;

import com.github.elasticsearch.domain.EsProduct;
import org.springframework.data.domain.Page;

/**
 * 商品搜索管理Service
 */
public interface EsProductService {

    int create();
    /**
     * 删除商品
     */
    void delete(Long id);

    /**
     * 修改
     * @param id
     * @return
     */
    int update(Long id);

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 根据关键字搜索名称或者副标题复合查询
     */
    Page<EsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort);

}
