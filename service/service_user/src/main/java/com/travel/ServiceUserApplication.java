package com.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Build_start
 * @create 2022-12-22 17:04
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.travel")
public class ServiceUserApplication {
    public static void main(String[] args) {
            SpringApplication.run(ServiceUserApplication.class, args);
    }
}
