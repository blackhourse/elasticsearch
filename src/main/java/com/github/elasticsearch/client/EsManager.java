package com.github.elasticsearch.client;

import com.github.elasticsearch.domain.EsUser;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mahongtao
 * @version 1.0
 * @date 2019/9/12 11:44
 */
public class EsManager {


    private final static String IndesName = "es_user";
    private final static String TypeName = "user";

    private final static Logger logger = LoggerFactory.getLogger(EsManager.class);
    private EsClientPoolManager esClientPoolManagerInstance = EsClientPoolManager.getIntance();

    public void createIndex(EsUser esUser, String index, String type) {

        Client client = esClientPoolManagerInstance.getClient();
        try {
            client.prepareIndex(index, type, esUser.getId() + "")
                    .setSource(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("id", esUser.getId())
                            .field("userName", esUser.getUserName())
                            .field("pwd", esUser.getPwd())
                            .field("age", esUser.getAge())
                            .field("address", esUser.getAddress())
                            .field("gmt_create", esUser.getGmtCreate())
                            .endObject()
                    )
                    .get();
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            esClientPoolManagerInstance.release(client);
        }

    }




}