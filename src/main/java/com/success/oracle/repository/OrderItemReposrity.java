package com.success.oracle.repository;

import com.success.oracle.entity.Category;
import com.success.oracle.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderItemReposrity  extends JpaRepository<OrderItem, Long>, JpaSpecificationExecutor<OrderItem> {

}
