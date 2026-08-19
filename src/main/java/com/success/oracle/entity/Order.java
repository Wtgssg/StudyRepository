package com.success.oracle.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ORDERS")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "SEQ_ORDERS", allocationSize = 1)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "ORDER_NO", nullable = false, unique = true, length = 50)
    private String orderNo;

    @Column(name = "TOTAL_AMOUNT", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "ORDER_STATUS", length = 30)
    private String orderStatus = "PENDING";

    @Column(name = "PAYMENT_METHOD", length = 50)
    private String paymentMethod;

    @Column(name = "SHIPPING_ADDRESS", length = 500)
    private String shippingAddress;

    @Column(name = "ORDER_TIME")
    private LocalDateTime orderTime;

    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;


    // 多对一关联用户
    @JsonIgnore
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @JsonIgnore
    // 一对多关联订单项
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

//    // 业务方法：计算订单总金额
//    @PostLoad
//    @PostPersist
//    @PostUpdate
//    public void calculateTotalAmount() {
//        if (orderItems != null && !orderItems.isEmpty()) {
//            totalAmount = orderItems.stream()
//                    .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
//                    .reduce(BigDecimal.ZERO, BigDecimal::add);
//        } else {
//            totalAmount = BigDecimal.ZERO;
//        }
//    }
//
//    @PrePersist
//    protected void onCreate() {
//        orderTime = new Date();
//        updateTime = new Date();
//        calculateTotalAmount();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        updateTime = new Date();
//        calculateTotalAmount();
//    }
//
//    // 添加订单项的便捷方法
//    public void addOrderItem(OrderItem item) {
//        orderItems.add(item);
//        item.setOrder(this);
//        calculateTotalAmount();
//    }

}