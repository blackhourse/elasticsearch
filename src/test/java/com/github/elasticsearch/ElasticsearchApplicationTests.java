package com.github.elasticsearch;

import com.github.elasticsearch.domain.client.EsUser;
import com.github.elasticsearch.service.client.EsUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

    @Autowired
    private EsUserService esUserService;


    @Test
    public void addListUser() {
        List<EsUser> list = new ArrayList<>();
        EsUser esUser = new EsUser().setId(1L).setUserName("sb");
        list.add(esUser);
        esUserService.create(list);
        System.out.println("aaa");
    }

    @Test
    public void updateEsUserInfo() {
        EsUser esUser = new EsUser().setId(1L).setUserName("mht");
        esUserService.updateEsUser(esUser);
        System.out.println("[update to es]");
    }

    @Test
    public void delEsUserInfo() {
        EsUser esUser = new EsUser().setId(1L).setUserName("mht");
        esUserService.delEsUser(esUser);
        System.out.println("[del to es]");
    }

}
