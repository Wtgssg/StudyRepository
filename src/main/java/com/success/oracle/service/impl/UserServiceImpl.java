package com.success.oracle.service.impl;
import com.success.oracle.Factory.SpecificationFactory;
import com.success.oracle.dto.UserTotalSpendDTO;
import com.success.oracle.entity.Order;
import com.success.oracle.entity.User;
import com.success.oracle.repository.UserRepository;
import com.success.oracle.service.UserService;
import com.success.oracle.util.CheckUtil;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.persistence.criteria.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public User login(String username, String password) {
        // 根据用户名查询用户（需要你在 UserRepository 中添加这个方法）
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        // 明文密码比对
        if (!password.equals(user.getPassword())) {
            return null;
        }
        return user;
    }

    @Override
    public List<User> findUserSepc(User user) {
        Specification<User> spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (user.getUserId() != null) {
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(root.get("userId"), user.getUserId()));
            }
            if (user.getUsername() != null) {
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(root.get("username"), "%" + user.getUsername() + "%"));
            }
            if (user.getEmail() != null) {
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(root.get("email"), "%" + user.getEmail() + "%"));
            }
            if (user.getPhone() != null) {
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(root.get("phone"), user.getPhone()));
            }
            if (user.getGender() != null) {
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(root.get("gender"), user.getGender()));
            }
            if (user.getStatus() != null){
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(root.get("status"),user.getStatus()));
            }

                return predicate;
        };

        return userRepository.findAll(spec);
    }

    public List<User> findAllCoUser(){
        Specification spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            return predicate;
        };
        return userRepository.findAll(spec);
    }




//    public List<UserTotalSpendDTO> findUserTotalSpend(){
//
//        return userRepository.findUserTotalSpend();
//    }


    public List<User> findexistsOrder(){
        Specification spec = (root, query, criteriaBuilder) ->{
            Predicate predicate = criteriaBuilder.conjunction();
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Order> subRoot = subquery.from(Order.class);

            subquery.select(criteriaBuilder.literal(1l));
            subquery.where(criteriaBuilder.equal(root.get("userId"),subRoot.get("user").get("userId")));

            predicate = criteriaBuilder.exists(subquery);

            return predicate;
        };
        return userRepository.findAll(spec);
    };


    @Override
    public List<User> findUserJse(User user) {

       Specification<User> specification = null;
//        Predicate predicate  ;
//        if (user.getUserId() != null){
//            specification = specification.and(
//                    UserSpecificationFactory.UserEquals("userId",user.getUserId())
//            );
//        }
//        if (user.getUsername() != null){
//            specification = specification.and(
//                   UserSpecificationFactory.UserEquals("username",user.getUsername())
//            );
//        }
//
//        if (user.getStatus() != null){
//            specification = specification.and(
//                    UserSpecificationFactory.UserEquals("status",user.getStatus())
//            );
//        }
//
//
//
       return userRepository.findAll(specification);
    }

    @Override
    public Specification<User> findUser2(User user){
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            if (user.getUserId() != null){
                expressions.add(criteriaBuilder.equal(root.get("userId"),user.getUserId()));
            }
            if (user.getUsername() != null){
                expressions.add(criteriaBuilder.equal(root.get("username"),user.getUsername()));
            }
            if (user.getStatus() != null){
                expressions.add(criteriaBuilder.equal(root.get("status"),user.getStatus()));
            }
            return predicate;
        };


//        return new Specification<User>() {
//        @Override
//        public Predicate toPredicate(Root<User> root,
//                                     CriteriaQuery<?> query,
//                                     CriteriaBuilder criteriaBuilder) {
//            List<Predicate> predicates = new ArrayList<>();
//
//            if (user.getUserId() != null) {
//                predicates.add(criteriaBuilder.equal(root.get("userId"), user.getUserId()));
//            }
//            if (user.getUsername() != null) {
//                predicates.add(criteriaBuilder.equal(root.get("username"), user.getUsername()));
//            }
//            if (user.getStatus() != null) {
//                predicates.add(criteriaBuilder.equal(root.get("status"), user.getStatus()));
//            }
//
//            if (predicates.isEmpty()) {
//                return criteriaBuilder.conjunction();
//            }
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        }
//    };

    }

    @Override
    public Page<User> findUserPage(Pageable pageable){

        return userRepository.findUserPage(pageable);
    }

    @Override
    public List<User> findUserList() {
        return userRepository.findAll();
    }


    @Override
    public List<User> findUserOrders() {
        Specification spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            Join<User,Order> UserOrderJoin = root.join("orders");

            //criteriaBuilder.equal(UserOrderJoin.get("orderId"),);
            return predicate;
        };

        return userRepository.findAll(spec);
    }
}
