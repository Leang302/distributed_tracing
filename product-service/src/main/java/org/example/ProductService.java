package org.example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.example.entity.Product;
import org.example.entity.dto.response.CategoryResponse;
import org.example.entity.dto.response.ProductResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(
        servers = {@Server(url = "/")}
)
public class ProductService {
    public static void main(String[] args) {
        SpringApplication.run(ProductService.class,args);
    }


}