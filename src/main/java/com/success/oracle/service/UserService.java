package com.success.oracle.service;

import com.success.oracle.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface UserService {

    List<User> findUserSepc(User user);

    List<User> findUserJse(User user);

    Specification<User> findUser2(User user);

    Page<User> findUserPage(Pageable pageable);

    List<User> findUserList();

    List<User> findUserOrders();

    List<User> findUserSe(Long id);
}
