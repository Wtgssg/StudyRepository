package com.success.oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDynamicCriteria {
    String username;
    String productname;
    Pageable pageable;
}
