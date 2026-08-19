package com.success.oracle.repository;

import com.success.oracle.dto.SameCategoryProductSaleDTO;
import com.success.oracle.entity.Category;
import com.success.oracle.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryReposrity extends JpaRepository<Category, Long> , JpaSpecificationExecutor<Category> {

    @Query(nativeQuery = true,value = "" +
            "with cate_all as ( " +
            "     SELECT " +
            "         category_id, " +
            "         CONNECT_BY_ROOT category_id as root_category_id " +
            "     FROM categories " +
            "     CONNECT BY PRIOR category_id = parent_id " +
            "     start WITH parent_id IS NULL " +
            " ), " +
            "product_sale as(  " +
            "     select ot.product_id,p.category_id, " +
            "            sum(quantity) as sum_quantity, " +
            "            sum(unit_price) as sum_price " +
            "     from order_items ot join products p on ot.product_id = p.product_id  " +
            "     group by ot.product_id,p.category_id " +
            ")  " +
            "select  ca.root_category_id, " +
            "        c.category_name , " +
            "        SUM(ps.sum_price) as total_sales " +
            "from cate_all ca " +
            "LEFT JOIN product_sale ps ON ca.category_id = ps.category_id " +
            "LEFT JOIN categories c ON ca.root_category_id = c.category_id " +
            "GROUP BY ca.root_category_id, c.category_name " +
            "ORDER BY total_sales DESC")
    List<SameCategoryProductSaleDTO> findSameCategorySale();

    @Query(nativeQuery = true ,value = " select category_id " +
            " from categories " +
            " start with category_id =  ?1 " +
            " connect by prior category_id = parent_id " +
            " ORDER SIBLINGS BY category_id ")
    List<Long> findAllSameCategory(Long categoryId);

    Category findCategoryByCategoryId(Long categoryId);
    
    @Query(value = "select u" +
                    " from Category c" +
                    " join c.products p" +
                    " join p.orderItems ot" +
                    " join ot.order o " +
                    " join o.user u " +
                    " where c.categoryId in ?1 ")
    List<User> getUsersWhoBoughtAllProductsInCategory(List<Long> categoryId);
    
    Category findByCategoryName(String categoryName);

    List<Category> findAll(Specification spec);
}
