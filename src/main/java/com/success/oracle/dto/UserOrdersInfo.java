package com.success.oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class UserOrdersInfo {
    Long userId;
    Long orderId;
    String productName;
    BigDecimal productPrice;
    BigDecimal totalAmount;

}
