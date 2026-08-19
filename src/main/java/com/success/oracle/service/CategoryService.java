package com.success.oracle.service;

import com.success.oracle.entity.Category;
import com.success.oracle.entity.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryService {

    List<User> getUsersWhoBoughtAllProductsInCategory( String categoryName);

    List<Long> findSameCategory(Category category);
}
