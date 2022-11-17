package my.search.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.net.http.HttpClient;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
@ComponentScan(basePackages = "my.search.service")
public class ServiceContextConfig {

    @Bean(name = "httpClientWithTimeout")
    //какой бин использовать из интерфейса, но @Qualifier главнее
    @Primary
    public HttpClient httpClient(){
        return HttpClient
                .newBuilder()
                .connectTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
    }

    @Bean(name = "simpleHttpClient")
    public HttpClient httpClient1(){
        return HttpClient.newHttpClient();
    }
}
