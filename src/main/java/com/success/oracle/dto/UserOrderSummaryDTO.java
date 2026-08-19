package com.success.oracle.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserOrderSummaryDTO {
    Long userId;
    String userName;
    Long orderCount;
    BigDecimal totalAmount;

    public UserOrderSummaryDTO(Long userId, String userName, Long orderCount, BigDecimal totalAmount) {
        this.userId = userId;
        this.userName = userName;
        this.orderCount = orderCount;
        this.totalAmount = totalAmount;
    }

    public UserOrderSummaryDTO() {
    }
}
