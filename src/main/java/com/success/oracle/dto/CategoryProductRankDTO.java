package com.success.oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CategoryProductRankDTO {
    private Integer totalProducts;
    private BigDecimal totalSales;
    private Integer totalQuantity;
    private Integer totalOrders;
}