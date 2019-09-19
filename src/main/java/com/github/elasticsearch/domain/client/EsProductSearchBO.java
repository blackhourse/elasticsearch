package com.github.elasticsearch.domain.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mahongtao
 * @version 1.0
 * @date 2019/9/2 11:00
 */
@Data
@Accessors(chain = true)
public class EsProductSearchBO {

    private Long productId;
    private String productSn;
    private Long brandId;
    private String brandName;
    private Long productCategoryId;
    private String productCategoryName;
    private String pic;
    private String albumPics;
    private String name;
    private String subTitle;
    private Integer sale;
    private Double maxPrice;
    private Double minPrice;
    private Integer commentCount;
    private Integer deleteStatus;
    private Integer publishStatus;
    private Integer recommandStatus;
    private Integer sort;
    private String detailDesc;
    private String detailHtml;
    private String detailMobileHtml;
    private String detailAttribute;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModify;
}
