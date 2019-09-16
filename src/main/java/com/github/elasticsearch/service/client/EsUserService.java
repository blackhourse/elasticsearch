package com.github.elasticsearch.service.client;

import com.github.elasticsearch.domain.client.EsUser;

import java.util.List;

/**
 * @author mahongtao
 * @version 1.0
 * @date 2019/9/12 14:32
 */
public interface EsUserService {

    /**
     * 创建es
     */
    void create(List<EsUser> list);

    /**
     * 添加es对象
     */
    void addToEsUser(EsUser esUser, String index, String type);

    /**
     * 更新es用户
     *
     * @param esUser
     */
    void updateEsUser(EsUser esUser);

    /**
     * 删除 es用户
     *
     * @param esUser
     */
    void delEsUser(EsUser esUser);


}
