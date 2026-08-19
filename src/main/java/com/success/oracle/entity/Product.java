package com.success.oracle.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
@Data
@ToString(exclude = {"category", "orderItems"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "SEQ_PRODUCTS", allocationSize = 1)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 200)
    private String productName;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "STOCK_QUANTITY")
    private Integer stockQuantity = 0;

    @Column(name = "STATUS", length = 20)
    private String status = "AVAILABLE";

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

    // 多对一关联分类
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    // 一对多关联订单项
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<>();
}