package com.success.oracle.service;

import com.success.oracle.dto.OrderDynamicCriteria;
import com.success.oracle.dto.OrderdynamicSort;
import com.success.oracle.dto.ProductSearchCriteria;
import com.success.oracle.dto.UserOrderSummaryDTO;
import com.success.oracle.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderService {

    Page<Order> findOrderByGroup(Integer Year,Integer Month ,Pageable pageable);

    Page<Order> findOrderdynamicSort(OrderdynamicSort dynamicSort);

    Page<Order> findOrderByUserOrProductname(OrderDynamicCriteria criteria);

    public Page<Order> findOrders(Pageable pageable);

    public List<Order> findOrdersImessages(Long userId);

    public List<UserOrderSummaryDTO> findOrdersImessage(Long userId);

    public List<Order> findOrdersByUserName(String username);
}
