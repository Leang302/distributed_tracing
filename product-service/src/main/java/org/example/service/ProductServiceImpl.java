package org.example.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.example.entity.Product;
import org.example.entity.dto.response.CategoryResponse;
import org.example.entity.dto.response.ProductResponse;
import org.example.exception.NotFoundException;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
//    @CircuitBreaker(name = "category-service",fallbackMethod ="getProductByIdFallback" )
    public ProductResponse getProductById(Long id) {
            Product productById = findProductById(id);
            Long categoryId = productById.getCategoryId();
       CategoryResponse categoryById= categoryService.getCategoryById(categoryId);
            return productById.toResponse(categoryById);
    }

    private Product findProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with id `"+id+"' not found."));
    }
    // fallback method
    public ProductResponse getProductByIdFallback(Long id, Throwable t) {
        Product product = findProductById(id);
        CategoryResponse fallbackCategory = new CategoryResponse(null, "Fallback Category");
        return product.toResponse(fallbackCategory);
//        throw new NotFoundException("Failed to fetch category for product id `" + id + "`");
    }
}
