package com.success.oracle.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDER_ITEMS")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
    @SequenceGenerator(name = "order_item_seq", sequenceName = "SEQ_ORDER_ITEMS", allocationSize = 1)
    @Column(name = "ITEM_ID")
    private Long itemId;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "UNIT_PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    // 虚拟列（数据库自动计算）
    @Column(name = "SUBTOTAL", insertable = false, updatable = false)
    private BigDecimal subtotal;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    // 多对一关联订单
    @ManyToOne()
    @JoinColumn(name = "ORDER_ID", nullable = false)
    @JsonIgnore
    private Order order;

    // 多对一关联商品
    @ManyToOne()
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

//    @PrePersist
//    protected void onCreate() {
//        createTime = new Date();
//    }

//    // 计算小计的方法
//    public BigDecimal getSubtotal() {
//        if (unitPrice != null && quantity != null) {
//            return unitPrice.multiply(BigDecimal.valueOf(quantity));
//        }
//        return BigDecimal.ZERO;
//    }
}