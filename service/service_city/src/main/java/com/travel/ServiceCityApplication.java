package com.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Build_start
 * @create 2022-12-16 16:18
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.travel")
public class ServiceCityApplication {
    public static void main(String[] args) {
            SpringApplication.run(ServiceCityApplication.class, args);
    }
}
