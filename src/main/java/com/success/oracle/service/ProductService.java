package com.success.oracle.service;

import com.success.oracle.dto.PriceSegment;
import com.success.oracle.dto.ProCateCountDTO;
import com.success.oracle.dto.ProductSalesDTO;
import com.success.oracle.dto.ProductSearchCriteria;
import com.success.oracle.entity.Order;
import com.success.oracle.entity.Product;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

     List<PriceSegment> celSegment(List<PriceSegment> segments);

     List<Product> multiConditionFilter(ProductSearchCriteria productSearchCriteria);

     List<Product> findSameCateporyProducts();

     List<ProductSalesDTO> findTop5ByTotalSales();


}
