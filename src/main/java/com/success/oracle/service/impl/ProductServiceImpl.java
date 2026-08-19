package com.success.oracle.service.impl;

import com.success.oracle.dto.*;
import com.success.oracle.entity.Order;
import com.success.oracle.entity.Product;
import com.success.oracle.repository.CategoryReposrity;
import com.success.oracle.repository.ProductRepository;
import com.success.oracle.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Array;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    protected CategoryReposrity categoryReposrity;


    @Override
    public List<PriceSegment> celSegment(List<PriceSegment> segments) {
        BigDecimal countProductNum = productRepository.countAllProduct();
        for (PriceSegment p:segments) {
            BigDecimal countPrice = productRepository.countPriceSegment(p.getMinPrice(),p.getMaxPrice());
            p.setPercentage(countPrice.divide(countProductNum,4, RoundingMode.HALF_UP));//四舍五入
        }
        return segments;
    }

    @Override
    public List<Product> multiConditionFilter(ProductSearchCriteria psc) {

        if (psc == null) return null;

        BigDecimal minPrice = psc.getMinPrice();
        BigDecimal maxPrice = psc.getMaxPrice();
        List<Long> categoryIds = psc.getCategoryIds();
        List<String> statuses = psc.getStatuses();
        Integer minStock = psc.getMinStock();
        Integer maxStock = psc.getMaxStock();
        String productName = psc.getProductName();

        Specification spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (minPrice != null && maxPrice != null){
                //价格区间（动态）
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.between(root.get("price"),minPrice,maxPrice));
            }
            if (categoryIds != null && !categoryIds.isEmpty()){
                //多个分类 ID（IN 查询）
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.in(root.get("category").get("categoryId")).value(categoryIds));
            }

            if (statuses != null && !statuses.isEmpty()) {
                //多个状态（IN 查询）
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.in(root.get("status")).value(statuses));
            }

            if (minStock != null && maxStock != null) {
                //库存范围
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.between(root.get("stockQuantity"),minStock,maxStock));
            }
            if (productName != null){
                predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(root.get("productName"),productName));
            }
            return predicate;
        };
        return productRepository.findAll(spec);
    }

    @Override
    public List<Product> findSameCateporyProducts() {
        List<Long> categoryIds = categoryReposrity.findAllSameCategory(1l);
        return productRepository.findProductsByCategory_CategoryIdIn(categoryIds);
    }

    @Override
    public List<ProductSalesDTO> findTop5ByTotalSales() {
        List<ProductSalesDTO> productSalesDTOS = productRepository.findTop5ByTotalSales();
        List<ProductSalesDTO> topFive = productSalesDTOS.subList(0, Math.min(5, productSalesDTOS.size()));
        return topFive;
    }

    public List<Product> testOverAvg(){
        Specification<Product> spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            Subquery<Double> subquery = query.subquery(Double.class);
            Root<Product> product = subquery.from(Product.class);
            subquery.select(criteriaBuilder.avg(product.get("price")));

            return criteriaBuilder.greaterThan(root.get("price"), subquery);

        };
        return productRepository.findAll(spec);
    }

}
