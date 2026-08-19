package com.success.oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderYearMonthDTO {
    Long orderId;
    Integer Year;
    Integer Month;
}
