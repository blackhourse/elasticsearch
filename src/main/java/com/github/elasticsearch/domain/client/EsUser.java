package com.github.elasticsearch.domain.client;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author mahongtao
 * @version 1.0
 * @date 2019/9/12 14:09
 */
@Data
@Accessors(chain = true)
public class EsUser {

    private Long id;
    private String userName;
    private String pwd;
    private Integer age;
    private String address;
    private Date gmtCreate;
}
