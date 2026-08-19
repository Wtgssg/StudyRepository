package com.success.oracle.dto;

import com.success.oracle.util.PageParam;
import com.success.oracle.util.SortOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderdynamicSort extends PageParam {
//    Long userId;
//    BigDecimal totalAmount;
//    String orderStatus;
    String SortBy;
    SortOrder order;
}
