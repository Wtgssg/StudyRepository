package com.success.oracle.controller;

import com.success.oracle.annotation.CurrentUser;
import com.success.oracle.annotation.RequiresPermission;
import com.success.oracle.entity.Order;
import com.success.oracle.entity.User;
import com.success.oracle.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @RequiresPermission
    @GetMapping("/id")
    public Iterable<Order> getOrderByid(@CurrentUser Long userId){
       return orderRepository.findByUser_UserId(userId);
    }
}
