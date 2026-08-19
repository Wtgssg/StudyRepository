package com.success.oracle.repository;


import com.success.oracle.dto.OrderWithLevelDTO;
import com.success.oracle.dto.OrderYearMonthDTO;
import com.success.oracle.dto.UserOrderSummaryDTO;
import com.success.oracle.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> , JpaSpecificationExecutor<Order> {
    @Query(value = "select NEW com.success.oracle.dto.OrderYearMonthDTO( " +
            "o.orderId , extract(YEAR from o.orderTime) , extract(MONTH from o.orderTime))" +
            "from Order o" )
    List<OrderYearMonthDTO> findYearMonth();

    Page<Order> findAll(Specification spec, Pageable pageable);

    @Query(nativeQuery = true,value = "" +
            "select  o.ORDER_ID as orderId,o.TOTAL_AMOUNT as amount, " +
            "           CASE " +
            "               WHEN TOTAL_AMOUNT>10000 then 'HIGH' " +
            "               else 'LOW'" +
            "           END as amountLevel" +
            " from ORDERS o")
    List<OrderWithLevelDTO> findOrdersInfo();

    List<Order> findOrdersByUser_UserIdAndOrderStatusAndTotalAmountGreaterThan(Long userUserId, String orderStatus, BigDecimal totalAmountIsGreaterThan);

    List<Order> findByUser_UserId(Long UserId);

    @Query(value = "SELECT new com.success.oracle.dto.UserOrderSummaryDTO(" +
            "               u.userId," +
            "               u.username," +
            "               COUNT(o.orderId)," +
            "               SUM(o.totalAmount)" +
            "           )" +
            "           FROM User u" +
            "           JOIN u.orders o" +
            "           GROUP BY u.userId, u.username")
    List<UserOrderSummaryDTO> findOrdersImessage(Long UserId);
}
