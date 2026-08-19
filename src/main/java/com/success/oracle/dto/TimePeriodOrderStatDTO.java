package com.success.oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class TimePeriodOrderStatDTO {

    private String timePeriod;


    private Integer activeUserCount;


    private Integer orderCount;


    private BigDecimal avgOrderAmount;


    private BigDecimal totalAmount;


    private String orderRatio;
}
