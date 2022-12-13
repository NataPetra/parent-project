package my.first.service;

import my.first.DataConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.http.HttpClient;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceContextConfig.class, DataConfig.class})
public class ServiceContextConfigTest {

    @Autowired
    SearchService searchService;

    @Autowired
    HttpClient httpClient;

    @Test
    public void testSearchService(){
        assertNotNull(searchService);
    }

    @Test
    public void testHttpClientBeen(){
        assertNotNull(httpClient);
    }

}