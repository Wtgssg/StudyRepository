package com.success.oracle.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.success.oracle.Enum.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity()        //声明此类是一个实体类
@Table(name = "USERS")      //配置实体类与表的映射关系
@Data
@ToString(exclude = "orders")
@NamedEntityGraph(
        name = "User.withOrdersAndItems",
        attributeNodes = {
                @NamedAttributeNode(
                        value = "orders",
                        subgraph = "orderItems"
		)
                },
subgraphs = {
@NamedSubgraph(
        name = "orderItems",
        attributeNodes = @NamedAttributeNode("orderItems")
)
	    }
                )
public class User {


    /**
     * strategy:id生成策略
     * GenerationType(TABLE,SEQUENCE,IDENTITY,ATUO)
     * TABLE    :通过表产生主键，框架由表模拟序列产生主键
     * SEQUENCE :通过序列产生主键，@SequenceGenerator指定序列名（不支持MySql）
     * IDENTITY :数据库id自增，通常用于MySql
     * ATUO     :自动选择（default）
     */
    /**
     * generator:通过Sequences产生id
     * 常见的是oracle数据库id生成规则需要配合@SequenceGenerator使用
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "SEQ_USERS", allocationSize = 1)
    @Column(name = "USER_ID")
    private Long userId;

    /**
     * @Basic的optional作用于java端
     * @Column的nullable作用于数据库端
     * 两者默认值都为true
     */
    @Basic
    @Column(name = "USERNAME", nullable = false, unique = true, length = 50)
    private String username;

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @Basic
    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String email;

    @Basic
    @Column(name = "PHONE", length = 20)
    private String phone;

    @Basic
    @Column(name = "GENDER", length = 10)
    private String gender;

    @Basic
    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;//数据库对应date类型

    @Basic
    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;//数据库对应timestamp类型

    @Basic
    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

    @Basic
    @Column(name = "STATUS", length = 20)
    private String status = "ACTIVE";

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", length = 20)
    private Role role;

    // 一对多关联订单
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();


//    // 预持久化回调
//    @PrePersist
//    protected void onCreate() {
//        createTime = new Date();
//        updateTime = new Date();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        updateTime = new Date();
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) && Objects.equals(gender, user.gender) && Objects.equals(birthDate, user.birthDate) && Objects.equals(createTime, user.createTime) && Objects.equals(updateTime, user.updateTime) && Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, email, phone, gender, birthDate, createTime, updateTime, status);
    }


}