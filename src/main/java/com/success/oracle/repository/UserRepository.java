package com.success.oracle.repository;

import com.success.oracle.dto.UserOrdersInfo;
import com.success.oracle.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long>, QueryByExampleExecutor<User> , JpaSpecificationExecutor<User> , CrudRepository<User,Long> {




    @Query(nativeQuery = true ,value = "" +
            " with rank_amount as( " +
            "     select row_number() over(order by o.total_amount) as rank_amounts, o.order_id " +
            "     from orders o" +
            " ) " +
            " select u.user_id," +
            "       u.username," +
            "       u.password," +
            "       u.email," +
            "       u.phone," +
            "       u.gender," +
            "       u.birth_date," +
            "       u.create_time," +
            "       u.update_time," +
            "       u.status," +
            "       ra.rank_amounts" +
            " from users u " +
            " join orders o " +
            " on u.user_id = o.user_id " +
            " join rank_amount ra " +
            " on ra.order_id = o.order_id " +
            " where rank_amounts <= 5 " +
            " order by rank_amounts ")
    List<Object> findUserRank();

    <T> T findByUserId(Long userId, Class<T> tClass);

    @Query(value = "select u " +
            "       from User u " +
            "       join u.orders o " +
            "       join o.orderItems oi " +
            "       join oi.product p " +
            "       where p.productId = ?1 ")
    List<User> findUserShoppingInfo(Long productId);

    @Query(value = "select NEW com.success.oracle.dto.UserOrdersInfo( " +
            " u.userId, " +
            " o.orderId, " +
            " p.productName, " +
            " p.price, " +
            " oi.unitPrice * oi.quantity )" +
            " from User u " +
            " join u.orders o " +
            " join o.orderItems oi " +
            " join oi.product p " +
            " where u.userId = ?1 ")
    List<UserOrdersInfo> findUserOrdersInfo(Long userId);

    List<User> findUsersByCreateTimeAfter(LocalDateTime createTimeAfter);

    List<User> findUserByStatusOrderByCreateTime(String status);

    User findByUsername(String username,Sort sort);

    User findByUsername(String username);


    @Query(nativeQuery = true,value = "select username from users where user_id = :userId")
    String findUserById(@Param("userId") Long id);


    @Query(nativeQuery = true,value = "select * from users where username like :likename || '%'")
    User findUserLikeusername(@Param("likename") String username);

    @Query(nativeQuery = true,value = "select * from users")
    Page<User> findUserPage(Pageable pageable);

    User findUserByUserId(Long userId);


}
