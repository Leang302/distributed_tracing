package org.example.client;

import org.example.entity.dto.response.CategoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "category-service")
public interface CategoryClient {
    @GetMapping("/api/v1/categories/{id}")
    CategoryResponse getCategoryById(@PathVariable Long id);

}
