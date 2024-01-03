package com.jojo.customer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Value("${eureka.client.service-url.defaultZone}")
    private String serviceUrl;

    @Bean
    public void printUrl() {
        System.out.println("-----------:" + serviceUrl);
    }

    @Bean
    public void printUrl1() {
        System.out.println("-----------1111111");
    }
}
