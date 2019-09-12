package com.github.elasticsearch.service.impl;

import com.github.elasticsearch.client.EsManager;
import com.github.elasticsearch.client.SysConfig;
import com.github.elasticsearch.domain.EsUser;
import com.github.elasticsearch.service.EsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author mahongtao
 * @version 1.0
 * @date 2019/9/12 14:32
 */
@Service
public class EsUserServiceImpl implements EsUserService {
    @Autowired
    private SysConfig sysConfig;

    @Override
    public void createIndex() {
        String appProfile = sysConfig.getAppProfile();
        EsUser esUser = new EsUser()
                .setId(1L)
                .setUserName("mahongtao")
                .setPwd("123456")
                .setAge(24)
                .setAddress("china")
                .setGmtCreate(new Date());


        EsManager esManager = new EsManager();
        esManager.createIndex(esUser, appProfile, appProfile);

        System.out.println(1111);
    }
}
