package com.success.oracle.service.impl;

import com.success.oracle.entity.Category;
import com.success.oracle.entity.Product;
import com.success.oracle.entity.User;
import com.success.oracle.repository.CategoryReposrity;
import com.success.oracle.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Subquery;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CateporyServiceImpl implements CategoryService {
    @Autowired
    CategoryReposrity categoryReposrity;

    @Override
    public List<Long> findSameCategory(Category category) {
        if (category.getParentCategory() == null){
            return categoryReposrity.findAllSameCategory(category.getCategoryId());
        }else{
            return categoryReposrity.findAllSameCategory(category.getParentCategory().getCategoryId());
        }
    }

    @Override
    public List<User> getUsersWhoBoughtAllProductsInCategory(String categoryName) {

        Category category = categoryReposrity.findByCategoryName(categoryName);

        Specification spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            predicate = criteriaBuilder.equal(root.get("categoryName"), categoryName);
            predicate = criteriaBuilder.equal(root.get("parentCategory"),category);

            return predicate;
        };
        List<Category> categories = categoryReposrity.findAll(spec);
        List<Long> ids = categories.stream()
                .map(Category::getCategoryId)
                .collect(Collectors.toList());

        return categoryReposrity.getUsersWhoBoughtAllProductsInCategory(ids);
    }
}