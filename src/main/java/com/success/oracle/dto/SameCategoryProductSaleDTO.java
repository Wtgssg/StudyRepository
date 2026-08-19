package com.success.oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class SameCategoryProductSaleDTO {
    Long categoryId;
    String categroyName;
    BigDecimal totalSale;
}
