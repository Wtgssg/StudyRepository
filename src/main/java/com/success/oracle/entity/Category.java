package com.success.oracle.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "CATEGORIES")
@Data
@ToString(exclude = {"subCategories","products"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "SEQ_CATEGORIES", allocationSize = 1)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_NAME", nullable = false, length = 100)
    private String categoryName;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    // 自关联 - 父分类
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Category parentCategory;

    // 自关联 - 子分类
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> subCategories = new ArrayList<>();

    // 一对多关联商品
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

//    @PrePersist
//    protected void onCreate() {
//        createTime = new Date();
//    }
}