//
//package com.github.elasticsearch.client.alone;
//
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//
///**
// * @author mahongtao
// * @version 1.0
// * @date 2019/9/18 10:37
// */
//
//@Configuration
//public class EsAloneConfig {
//
//
//    /**
//     * es的构造
//     *
//     * @return
//     * @throws UnknownHostException
//     */
//
//    @Bean("TransportClient")
//    public TransportClient client() throws UnknownHostException {
//
//        // 机器IP 端口号
//        TransportAddress node = new TransportAddress(
//                InetAddress.getByName("127.0.0.1"),
//                9300
//        );
//
//        // 集群名称
//        Settings settings = Settings.builder()
//                .put("cluster.name", "my-application")
//                .build();
//
//        TransportClient client = new PreBuiltTransportClient(settings);
//        client.addTransportAddress(node);
//        return client;
//    }
//
//}
//
