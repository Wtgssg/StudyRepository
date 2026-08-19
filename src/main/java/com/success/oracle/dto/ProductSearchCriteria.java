package com.success.oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
public class ProductSearchCriteria {
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private List<Long> categoryIds;  // 支持多分类
    private List<String> statuses;    // 支持多状态
    private Integer minStock;
    private Integer maxStock;
    private String productName;


}
