package org.example.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.example.client.CategoryClient;
import org.example.entity.dto.response.CategoryResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryClient categoryClient;

    @Retry(name = "")
    public CategoryResponse getCategoryById(Long categoryId) {
        return categoryClient.getCategoryById(categoryId);
    }

    public CategoryResponse getCategoryByIdFallback(Long categoryId, Throwable t) {
        System.out.println("Fallback triggered for category id " + categoryId + ": " + t.getMessage());
        return CategoryResponse.builder()
                .id(null)
                .name("Fallback Category")
                .build();
    }
}