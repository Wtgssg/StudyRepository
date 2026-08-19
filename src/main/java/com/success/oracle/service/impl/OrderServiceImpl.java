package com.success.oracle.service.impl;

import com.success.oracle.dto.*;
import com.success.oracle.entity.Order;
import com.success.oracle.entity.OrderItem;
import com.success.oracle.entity.Product;
import com.success.oracle.entity.User;
import com.success.oracle.repository.OrderRepository;
import com.success.oracle.service.OrderService;
import com.success.oracle.service.ProductService;
import com.success.oracle.util.CheckUtil;
import com.success.oracle.util.SortOrder;
import com.success.oracle.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @PersistenceContext
    EntityManager em;


    @Override
    public Page<Order> findOrderByGroup(Integer Year,Integer Month,Pageable pageable) {
        Specification spec = (root, query, criteriaBuilder) -> {
          Predicate predicate = criteriaBuilder.conjunction();
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(
                    criteriaBuilder.function("YEAR", Integer.class,root.get("orderTime")),Year)
            );

            predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(
                    criteriaBuilder.function("MONTH", Integer.class,root.get("orderTime")),Month)
            );
          return predicate;
        };

        return orderRepository.findAll(spec,pageable);


//        List<OrderYearMonthDTO> ordersyms =orderRepository.findYearMonth();
//        List<Long> ids = ordersyms.stream()
//                .filter(dto -> dto.getYear().equals(Year))
//                .filter(dto -> dto.getMonth().equals(Month))
//                .map(OrderYearMonthDTO::getOrderId)
//                .collect(Collectors.toList());
//
//        Specification spec = (root, query, criteriaBuilder) -> {
//            Predicate predicate = criteriaBuilder.conjunction();
//            predicate = criteriaBuilder.and(criteriaBuilder.in(root.get("orderId")).value(ids));
//            return predicate;
//        };
//        return orderRepository.findAll(spec,pageable);
    }

    @Override
    public Page<Order> findOrderdynamicSort(OrderdynamicSort p) {
       Pageable pageable;
        Sort sort;
        if (p.getSortBy() != null){
            if (p.getOrder() == SortOrder.DESC) {
                 sort = Sort.by(p.getSortBy()).descending();
            }else{
                 sort = Sort.by(p.getSortBy()).ascending();
            }
            pageable = PageRequest.of(p.getPageNum(),p.getPageSize(),sort);
        }else{
            pageable = PageRequest.of(p.getPageNum(),p.getPageSize());
        }


//        Pageable pageable;
//        if (p.getSortBy() != null && !p.getSortBy().isEmpty()) {
//            Sort sort = p.getOrder() == SortOrder.DESC
//                    ? Sort.by(p.getSortBy()).descending()
//                    : Sort.by(p.getSortBy()).ascending();
//            pageable = PageRequest.of(p.getPageNum(), p.getPageSize(), sort);
//        } else {
//            pageable = PageRequest.of(p.getPageNum(), p.getPageSize());
//        }

//        Specification spec = (root, query, criteriaBuilder) -> {
//            Predicate predicate = criteriaBuilder.conjunction();
//            if (p.getTotalAmount() != null && !"".equals(p.getTotalAmount())) {
//                predicate = criteriaBuilder.and(predicate,criteriaBuilder.);
//            }
//            if ( != null && !"".equals()) {
//
//                predicate = criteriaBuilder.and(predicate,);
//            }
//
//            return predicate;
//        };
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> findOrderByUserOrProductname(OrderDynamicCriteria criteria) {
        String username = criteria.getUsername();
        String productname = criteria.getProductname();
        Pageable pageable = criteria.getPageable();

        Specification spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (username != null && !"".equals(username)) {
                Join<Order, User> JoinUser = root.join("user");
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(JoinUser.get("username"),username));
            }
            if (productname != null && !"".equals(productname)) {
                Join<Order,OrderItem> JoinOrderItem = root.join("orderItems");
                Join<OrderItem, Product> JoinProduct = JoinOrderItem.join("product");
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(JoinProduct.get("productName"),productname));
            }
            return predicate;
        };
        return orderRepository.findAll(spec,pageable);
    }



    @Override
    public Page<Order> findOrders(Pageable pageable) {
        Sort.Order Order = Sort.Order.asc("orderTime").nullsFirst();
        if (pageable == null){
            pageable = PageRequest.of(0,20,Sort.by("orderTime").descending());
        }

        return orderRepository.findAll(pageable);
    }


    public List<Order> findOrdersImessages(Long userId){
        Specification spec = (root, query, criteriaBuilder) ->{
            Predicate predicate = criteriaBuilder.conjunction();
            return  predicate;
        };

        return orderRepository.findAll(spec);
    }


    @Override
    public List<UserOrderSummaryDTO> findOrdersImessage(Long userId) {
        return orderRepository.findOrdersImessage(userId);
    }
    @Override
    public List<Order> findOrdersByUserName(String username){
        Specification<Order> spec = (root, query, criteriaBuilder) ->{
            Predicate predicate = criteriaBuilder.conjunction();
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<User> userRoot = subquery.from(User.class);

            subquery.select(userRoot.get("userId"));
            subquery.where(criteriaBuilder.equal(userRoot.get("username"), username));

            //predicate = criteriaBuilder.in(root.get("user").get("userId")).value(subquery);
            predicate = criteriaBuilder.and(root.get("user").get("userId").in(subquery));
            return predicate;
        };
        return orderRepository.findAll(spec);
    }
}
