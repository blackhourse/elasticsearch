package com.github.elasticsearch.client;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author mahongtao
 * @version 1.0
 * @date 2019/9/12 10:32
 */
public class EsClientPoolManager {

    private static Logger logger = LoggerFactory.getLogger(EsClientPoolManager.class);
    private static EsClientPoolManager intance;

    private Queue<Client> clientPool = new ConcurrentLinkedQueue<Client>();

    public static EsClientPoolManager getIntance() {
        if (intance == null) {
            intance = new EsClientPoolManager();
        }
        return intance;
    }

    private EsClientPoolManager() {

    }

    public Client getClient() {
        Client client = clientPool.poll();

        Settings esSettings = Settings.builder()
                .put("cluster.name", "my-application") //设置ES实例的名称
                .put("client.transport.sniff", true) //自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                .put("client.transport.ignore_cluster_name", true)  //如果集群名不对，也能连接
                .build();
        if (client == null) {
            try {
                client = new PreBuiltTransportClient(esSettings)
                        .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
            } catch (UnknownHostException e) {
                logger.error("e:{}", e);
            }
        }

        return client;

    }

    public void release(Client client) {
        clientPool.add(client);
    }


}
