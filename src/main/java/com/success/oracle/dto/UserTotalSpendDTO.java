package com.success.oracle.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class UserTotalSpendDTO {
    String username;
    BigDecimal totalAmount;
    public UserTotalSpendDTO(String username, BigDecimal totalAmount) {
        this.username = username;
        this.totalAmount = totalAmount;
    }
}
