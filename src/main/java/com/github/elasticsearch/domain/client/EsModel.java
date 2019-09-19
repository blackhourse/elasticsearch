package com.github.elasticsearch.domain.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName EsModel
 * @Author lihaodong
 * @Date 2018/12/20 09:27
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
public class EsModel {

    private String id;
    private Integer age;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
}
