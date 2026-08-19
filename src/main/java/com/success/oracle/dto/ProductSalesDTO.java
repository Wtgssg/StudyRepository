package com.success.oracle.dto;

import lombok.Data;

@Data
public class ProductSalesDTO {
    private Long productId;         // 商品ID
    private String productName;     // 商品名称
    private Long totalSoldQuantity;  // 总销量

    public ProductSalesDTO(Long productId, String productName, Long totalSoldQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.totalSoldQuantity = totalSoldQuantity;
    }

    public ProductSalesDTO() {
    }
}
