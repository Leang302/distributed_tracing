package org.example.controller;


import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.example.client.CategoryClient;
import org.example.entity.Product;
import org.example.entity.dto.request.ProductRequest;
import org.example.entity.dto.response.CategoryResponse;
import org.example.entity.dto.response.ProductResponse;
import org.example.exception.NotFoundException;
import org.example.repository.ProductRepository;
import org.example.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryClient categoryClient;
    private final ProductServiceImpl productServiceImpl;

    @Retry(name = "default")
    @GetMapping("{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productServiceImpl.getProductById(id);
    }

    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequest categoryRequest) {
        Long categoryId = categoryRequest.getCategoryId();
        CategoryResponse categoryById = categoryClient.getCategoryById(categoryId);
        return productRepository.save(categoryRequest.toEntity()).toResponse(categoryById);
    }

    @PutMapping("{id}")
    public ProductResponse updateProductById(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        Long categoryId = productRequest.getCategoryId();
        CategoryResponse categoryById = categoryClient.getCategoryById(categoryId);
        Product productById = findProductById(id);
        productById.setName(productRequest.getName());
        productById.setCategoryId(categoryId);
        return productRepository.save(productById).toResponse(categoryById);
    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable Long id) {
        Product productById = findProductById(id);
        productRepository.delete(productById);
    }
    private Product findProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with id `"+id+"' not found."));
    }

}

