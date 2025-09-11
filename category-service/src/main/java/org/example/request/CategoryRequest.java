package org.example.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.entity.Category;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CategoryRequest {
    private String name;

    public Category toEntity() {
        return new Category(null,name);
    }
}
