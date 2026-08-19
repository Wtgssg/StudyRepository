package com.success.oracle.dto;

import com.success.oracle.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCategoryNumDto {
    Long productId;
    Category category;
    Long productCount;

}
