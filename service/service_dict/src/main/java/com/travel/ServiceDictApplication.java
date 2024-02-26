package com.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Build_start
 * @create 2022-12-18 17:00
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceDictApplication {
    public static void main(String[] args) {
            SpringApplication.run(ServiceDictApplication.class, args);
    }
}
