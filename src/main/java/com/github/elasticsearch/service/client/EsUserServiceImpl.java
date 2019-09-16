package com.github.elasticsearch.service.client;

import com.github.elasticsearch.client.EsManager;
import com.github.elasticsearch.client.SysConfig;
import com.github.elasticsearch.domain.client.EsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void create(List<EsUser> list) {
        EsManager esManager = new EsManager();
        esManager.addTagList(list);
    }

    @Override
    public void addToEsUser(EsUser esUser, String index, String type) {
        String appProfile = sysConfig.getAppProfile();
        EsManager esManager = new EsManager();
        esManager.addToEsUser(esUser, appProfile, appProfile);
        System.out.println(1111);
    }

    @Override
    public void updateEsUser(EsUser esUser) {
        EsManager esManager = new EsManager();
        esManager.updateUserInfo(esUser);
    }

    @Override
    public void delEsUser(EsUser esUser) {
        EsManager esManager = new EsManager();
        esManager.deleteUserInfo(esUser);
        System.out.println("del");
    }
}
