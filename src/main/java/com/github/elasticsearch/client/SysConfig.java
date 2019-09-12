package com.github.elasticsearch.client;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author mahongtao
 * @version 1.0
 * @date 2019/9/12 14:48
 */
@Component
@Data
public class SysConfig {
    //系统环境
    @Value("${app.profile}")
    private String appProfile;

}
