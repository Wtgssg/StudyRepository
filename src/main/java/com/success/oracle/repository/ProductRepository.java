package com.success.oracle.repository;

import com.success.oracle.dto.CategoryProductRankDTO;
import com.success.oracle.dto.PriceSegment;
import com.success.oracle.dto.ProductCategoryNumDto;
import com.success.oracle.dto.ProductSalesDTO;
import com.success.oracle.entity.Category;
import com.success.oracle.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
/**
 * SELECT
 *     p.product_id,
 *     p.product_name,
 *     SUM(o.quantity) AS totalSoldQuantity   -- 用 SUM，不是 COUNT
 * FROM products p
 * JOIN order_items o ON p.product_id = o.product_id
 * GROUP BY p.product_id, p.product_name      -- 加入 product_name
 * ORDER BY totalSoldQuantity desc;
 *
 * select NEW com.success.oracle.dto.ProductSalesDTO(
 *      p.productId,
 *      p.productName,
 *      sum(o.quantity) as totalSoldQuantity
 * )from Product p join OrderItem o
 * group by p.productId, p.productName
 * order by totalSoldQuantity desc
 * */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(nativeQuery = true,value ="WITH category_root_map AS (" +
            "    SELECT " +
            "        category_id," +
            "        CONNECT_BY_ROOT category_id AS root_category_id" +
            "    FROM categories" +
            "    START WITH parent_id IS NULL          " +
            "    CONNECT BY PRIOR category_id = parent_id\n" +
            ")" +
            "select crm.root_category_id,count(DISTINCT p.product_id),sum(oi.subtotal),sum(oi.quantity),count(DISTINCT oi.order_id)" +
            "from products p " +
            "join category_root_map crm on p.category_id = crm.category_id " +
            "join order_items oi on p.product_id = oi.product_id " +
            "group by crm.root_category_id ")
    List<CategoryProductRankDTO> getcategoryrank();

    @Query("select count(1) from Product p where p.price between ?1 and ?2 ")
    BigDecimal countPriceSegment(BigDecimal minPrice ,BigDecimal maxPrice);

    @Query("select count(*) from Product ")
    BigDecimal countAllProduct();

    List<Product> findProductsByCategory_CategoryIdIn(List<Long> categoryCategoryIds);

    @Query(value = "select NEW com.success.oracle.dto.ProductCategoryNumDto(" +
            "   p.productId," +
            "   p.category," +
            "   count(p) )" +
            "from Product p " +
            "join p.category c " +
            "join c.parentCategory cp " +
            "where c.categoryId = ?1 " +
            "or cp.categoryId = ?1 " +
            "group by p.productId, p.category ")
    List<ProductCategoryNumDto> findProductCategoryNumDto(Long categoryId);

    @Query(nativeQuery = true,value =" select t.product_id, t.product_name ,t.description,t.category_id,t.price,t.stock_quantity,t.status,t.create_time,t.update_time " +
            " from (" +
            "     select p.* , row_number() over(order by price ) as rank_num " +
            "     from products p" +
            " ) t " +
            " where t.rank_num <= 10")
    List<Product> findProductTenth();

    List<Product> findProductsByCategory_categoryIdAndProductNameLike(Long categoryId, String productName);

    List<Product> findProductByPriceBetweenAndStatus(BigDecimal minPrice, BigDecimal maxPrice,String status);

    @Query("  select NEW com.success.oracle.dto.ProductSalesDTO(" +
            "       p.productId," +
            "       p.productName," +
            "       sum(o.quantity) " +
            "  )from Product p join p.orderItems o" +
            "  group by p.productId, p.productName " +
            "  order by sum(o.quantity) desc")
    List<ProductSalesDTO> findTop5ByTotalSales();

    List<Product> findAll(Specification<Product> spec);



}
