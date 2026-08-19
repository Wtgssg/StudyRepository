package com.success.oracle.dto;

import com.success.oracle.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProCateCountDTO {
    Long productId;
    Long categoryId;
    Long countNum;
}
