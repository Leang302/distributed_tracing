package org.example.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private Long categoryId;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .categoryId(categoryId)
                .build();
    }
}
