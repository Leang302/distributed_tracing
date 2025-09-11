package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.dto.response.CategoryResponse;
import org.example.entity.dto.response.ProductResponse;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "product_name")
    private String name;
    private Long categoryId;

    public ProductResponse toResponse(CategoryResponse categoryById) {
        return ProductResponse.builder()
                .id(id)
                .productName(name)
                .category(
                        CategoryResponse.builder()
                                .id(categoryId)
                                .name(categoryById.getName())
                                .build()
                )
                .build();
    }
}
