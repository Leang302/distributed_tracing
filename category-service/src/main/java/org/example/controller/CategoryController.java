package org.example.controller;

import lombok.RequiredArgsConstructor;

import org.example.entity.Category;
import org.example.exception.NotFoundException;
import org.example.repository.CategoryRepository;
import org.example.request.CategoryRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    @Value("${server.port}")
    private String port;
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        throw new RuntimeException("error");
//        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category with id `"+id+"' not found."));
    }

    @PostMapping
    public Category createCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryRepository.save(categoryRequest.toEntity());
    }

    @PutMapping("{id}")
    public Category updateCategoryById(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        Category categoryById = getCategoryById(id);
        categoryById.setName(categoryRequest.getName());
        return categoryRepository.save(categoryById);
    }

    @DeleteMapping("{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        Category categoryById = getCategoryById(id);
       categoryRepository.delete(categoryById);
    }
}
