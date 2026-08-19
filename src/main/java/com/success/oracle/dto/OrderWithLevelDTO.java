package com.success.oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

public interface OrderWithLevelDTO {
     Long getOrderId();
     BigDecimal getAmount();
     String getAmountLevel();
}
