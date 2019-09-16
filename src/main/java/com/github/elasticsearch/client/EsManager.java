package com.github.elasticsearch.client;

import com.github.elasticsearch.domain.client.EsUser;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author mahongtao
 * @version 1.0
 * @date 2019/9/12 11:44
 */
public class EsManager {


    private final static String USER_INDEX_NAME = "es_user_index";
    private final static String USER_TYPE_NAME = "user_type";


    private final static Logger logger = LoggerFactory.getLogger(EsManager.class);
    private EsClientPoolManager esClientPoolManagerInstance = EsClientPoolManager.getIntance();


    /**
     * 创建
     *
     * @param tags
     */
    public void addTagList(List<EsUser> tags) {
        if (tags.size() == 0) {
            return;
        }
        Client client = esClientPoolManagerInstance.getClient();
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        for (EsUser tag : tags) {
            try {
                bulkRequest.add(client.prepareIndex(USER_INDEX_NAME, USER_TYPE_NAME, tag.getId() + "")
                        .setSource(XContentFactory.jsonBuilder()
                                .startObject()
                                .field("id", tag.getId())
                                .field("name", tag.getUserName())
                                .endObject()
                        )
                );
            } catch (IOException e) {
                logger.error("", e);
            }
        }
        BulkResponse bulkItemResponses = bulkRequest.get();
        esClientPoolManagerInstance.release(client);
    }

    /**
     * @param esUser
     * @param index
     * @param type
     */
    public void addToEsUser(EsUser esUser, String index, String type) {

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

    /**
     * 更新es 用户信息
     *
     * @param userInfo
     */
    public void updateUserInfo(EsUser userInfo) {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(USER_INDEX_NAME);
        updateRequest.type(USER_TYPE_NAME);
        updateRequest.id(userInfo.getId() + "");
        try {
            updateRequest.doc(XContentFactory.jsonBuilder()
                    .startObject()
                    .field("info", userInfo.getId())
                    .field("userName", userInfo.getUserName())
                    .endObject());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error("", e);
        }
        Client client = esClientPoolManagerInstance.getClient();
        try {
            client.update(updateRequest).get();
        } catch (InterruptedException e) {
            logger.error("", e);
        } catch (ExecutionException e) {
            logger.error("", e);
        } finally {
            esClientPoolManagerInstance.release(client);
        }
    }

    public void addUserInfoList(Collection<EsUser> userInfos) {
        if (userInfos.size() == 0) {
            return;
        }
        Client client = esClientPoolManagerInstance.getClient();
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        for (EsUser userInfo : userInfos) {
            try {
                bulkRequest.add(client.prepareIndex(USER_INDEX_NAME, USER_TYPE_NAME, userInfo.getId() + "")
                        .setSource(XContentFactory.jsonBuilder()
                                .startObject()
                                .field("id", userInfo.getId())
                                .field("name", userInfo.getUserName())
                                .endObject()
                        )
                );
            } catch (IOException e) {
                logger.error("", e);
            }
        }
        bulkRequest.get();
        esClientPoolManagerInstance.release(client);
    }

    public void deleteUserInfo(EsUser esUser) {

        Client client = esClientPoolManagerInstance.getClient();
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        bulkRequestBuilder.add(new DeleteRequest(USER_INDEX_NAME, USER_TYPE_NAME, esUser.getId() + ""));
        BulkResponse bulkItemResponses = bulkRequestBuilder.get();
        esClientPoolManagerInstance.release(client);
    }


    public void addQuestCommentInfo(EsUser esUser) {

        Client client = esClientPoolManagerInstance.getClient();
        BulkRequestBuilder bulkRequest = client.prepareBulk();

        HashMap<String, Object> source = new HashMap<String, Object>();
        source.put("id", esUser.getId());
        source.put("userName", esUser.getUserName());

        bulkRequest.add(
                client.prepareIndex(USER_INDEX_NAME, USER_TYPE_NAME, esUser.getId() + "")
                        .setSource(source));

        // todo
     /*   HashMap<String, Object> source1 = new HashMap<String, Object>();
        source1.put("commentCnt", questCommentInfo.getQuestCommentProtocol().getCommentOrder());
        bulkRequest.add(
                client.prepareUpdate(AorBIndexName, QuestTypeName, questCommentInfo.getQuestComment().getQuestid() + "")
                        .setDoc(source1));
        bulkRequest.get();*/

        esClientPoolManagerInstance.release(client);
    }
}