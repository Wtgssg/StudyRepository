package com.success.oracle;

import com.success.oracle.controller.UserController;
import com.success.oracle.dto.*;
import com.success.oracle.dto.User.interfaces.UserIdAndUsername;
import com.success.oracle.dto.User.interfaces.UserIdAndUsernameAndGender;
import com.success.oracle.entity.Category;
import com.success.oracle.entity.Order;
import com.success.oracle.entity.Product;
import com.success.oracle.entity.User;
import com.success.oracle.repository.CategoryReposrity;
import com.success.oracle.repository.OrderRepository;
import com.success.oracle.repository.ProductRepository;
import com.success.oracle.repository.UserRepository;
import com.success.oracle.service.CategoryService;


import com.success.oracle.service.impl.OrderServiceImpl;
import com.success.oracle.service.impl.ProductServiceImpl;
import com.success.oracle.service.impl.UserServiceImpl;
import com.success.oracle.util.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
@Transactional
class OracleApplicationTests {

    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserController userController;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductServiceImpl produceServiceImpl;
    @Autowired
    CategoryService categoryService;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private CategoryReposrity categoryReposrity;
    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Test
    void testfindUsersByUserId(){
        User user = userRepository.findUserByUserId(1l);
        System.out.println();
    }
//    @Test
//    void testcountPriceSegment(){
//        BigDecimal a= productRepository.countPriceSegment(BigDecimal.valueOf(0),BigDecimal.valueOf(3000));
//        System.out.println();
//    }
//
//    @Test
//    void testcelSegment(){
//        List<PriceSegment> ps = Arrays.asList(
//                new PriceSegment("A", new BigDecimal("0"), new BigDecimal("3000"), null),
//                new PriceSegment("B", new BigDecimal("3000"), new BigDecimal("6000"), null),
//                new PriceSegment("C", new BigDecimal("6000"), new BigDecimal("15000"), null)
//        );
//        produceServiceImpl.celSegment(ps);
//        System.out.println();
//    }

//    @Test
//    void testfindOrderByGroup(){
//        Pageable pageable = PageRequest.of(0,5);
//        Page<Order> orders = orderServiceImpl.findOrderByGroup(2024,1,pageable);
//        System.out.println();
//    }

//    @Test
//    void testfindYearMonth(){
//        List<OrderYearMonthDTO> orders = orderRepository.findYearMonth();
//        System.out.println();
//    }


//    @Test
//    void testfindOrderdynamicSort(){
//        OrderdynamicSort sort = new OrderdynamicSort("totalAmount", SortOrder.DESC);
//        Page<Order> orders = orderServiceImpl.findOrderdynamicSort(sort);
//        System.out.println();
//    }
//    @Test
//    void testfindOrderByUserOrProductname(){
//        OrderDynamicCriteria criteria = new OrderDynamicCriteria();
//        criteria.setPageable(PageRequest.of(0,5));
//        criteria.setProductname("Spring Boot实战");
//        Page<Order> orders = orderServiceImpl.findOrderByUserOrProductname(criteria);
//        System.out.println(orders);
//    }
//    @Test
//    void testorderTime(){
//        Page<Order> orderPage = orderService.findOrders(null);
//        System.out.println();
//    }
//    @Test
//    void testfindSameCateporyProducts(){
//        List<Product> products = produceServiceImpl.findSameCateporyProducts();
//        for (Product p : products){
//            System.out.println(p);
//        }
//    }

//    @Test
//    void testfindUserRank(){
//        List<Object> users = userRepository.findUserRank();
//        for (Object o :  users ){
//            System.out.println(o.toString());
//        }
//    }
//    @Test
//    void testinter(){
//        UserIdAndUsername user1 = userRepository.findByUserId(1l, UserIdAndUsername.class);
//        UserIdAndUsernameAndGender user2 = userRepository.findByUserId(1l, UserIdAndUsernameAndGender.class);
//
//        System.out.println(user1.getUserId()+" "+user1.getUsername());
//        System.out.println(user2.getUserId()+" "+user2.getUsername()+" "+user2.getGender());
//    }
//    @Test
//    void testfindOrdersInfo(){
//        List<OrderWithLevelDTO> orders = orderRepository.findOrdersInfo();
//        for (OrderWithLevelDTO row : orders) {
//            System.out.println(row.getOrderId());
//            System.out.println(row.getAmountLevel());  // 伪列
//        }
//    }

//    @Test
//    void testfindUserShoppingInfo(){
//        List<User> users = userRepository.findUserShoppingInfo(1l);
//        for (User u : users){
//            System.out.println(u);
//        }
//    }

//    @Test
//    void testfindUserOrdersInfo(){
//        List<UserOrdersInfo> lists = userRepository.findUserOrdersInfo(1l);
//        for (UserOrdersInfo o : lists){
//            System.out.println(o);
//        }
//
//    }
//    @Test
//    void testfindProductCategoryNumDto(){
//        List<ProductCategoryNumDto> lists = productRepository.findProductCategoryNumDto(1l);
//        for (ProductCategoryNumDto o : lists){
//            System.out.println(o);
//        }
//    }

//    @Test
//    void testfindProductTenth(){
//        List<Product> products =  productRepository.findProductTenth();
//        for (Product p : products){
//            System.out.println(p);
//
//        }
//    }
//    @Test
//    void testUsers(){
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime After7Days = now.minusDays(7l);
//        List<User> users = userRepository.findUsersByCreateTimeAfter(After7Days);
//        for (User u : users){
//            System.out.println(u);
//        }
//     }
//    @Test
//    void  testOrders(){
//        List<Order> orders = orderRepository.findOrdersByUser_UserIdAndOrderStatusAndTotalAmountGreaterThan(4L,"PENDING",BigDecimal.valueOf(10000) );
//        System.out.println();
//    }
//    @Test
//    void testProducts(){
//
//        List<Product> products = productRepository.findProductsByCategory_categoryIdAndProductNameLike(5L,"男士%");
//        for (Product p : products){
//            System.out.println(p);
//        }
//    }

//    @Test
//    void testProducts(){
//        BigDecimal minPrice = BigDecimal.valueOf(200);
//        BigDecimal maxPrice = BigDecimal.valueOf(500);
//        List<Product> products = productRepository.findProductByPriceBetweenAndStatus(minPrice,maxPrice,"AVAILABLE");
//        for (Product p : products){
//            System.out.println(p);
//        }
//    }
//    @Test
//    void testfindAllEleUser(){
//        List<User> users = userRepository.findUserByStatusOrderByCreateTime("ACTIVE");
//        for (User u : users){
//            System.out.println(u);
//        }
//    }
//    @Test
//    void testFindUserTotalSpend(){
//        List<UserTotalSpendDTO> dtos = userRepository.findUserTotalSpend();
//    }
//    @Test
//    public void testFindexistsOrder(){
//        List<User> users = userService.findexistsOrder();
//    }
//    @Test
//    void testFindOrdersByUserName(){
//        List<Order> orders = orderService.findOrdersByUserName("zhangsan");
//    }
//    @Test
//    void testfindAll() {
//        List<User> users = categoryService.getUsersWhoBoughtAllProductsInCategory("电子产品");
//        users.forEach(user -> {
//            user.getUserId();
//        });
//    }

//    @Test
//    public void testfindOrdersImessage(){
//        List<UserOrderSummaryDTO> uos = orderRepository.findOrdersImessage(1l);
//        for (UserOrderSummaryDTO userOrderSummaryDTO : uos) {
//            System.out.println(userOrderSummaryDTO);
//        }
//    }
//    @Test
//    public void testToOne() {
//        List<Order> orders = orderRepository.findByUser_UserId(1l);
//        orders.forEach(order -> {
//            System.out.println("订单ID: " + order.getOrderId());
//            System.out.println("订单号: " + order.getOrderNo());
//            System.out.println("---");
//        });
//    }
//@Test
//    public void test() {
//        List<Object[]> results = productRepository.findRawResult();
//        System.out.println("结果数量: " + results.size());
//        for (Object[] row : results) {
//            System.out.println("productId: " + row[0]);
//            System.out.println("productName: " + row[1]);
//            System.out.println("totalSold: " + row[2]);
//            System.out.println("totalSold类型: " + row[2].getClass().getName());
//        }
//    }

//    @Test
//    public void testfindTop5ByTotalSales(){
//        List<ProductSalesDTO> ProductSalesDTOS = produceServiceImpl.findTop5ByTotalSales();
//
//            for (ProductSalesDTO productSalesDTO : ProductSalesDTOS) {
//                System.out.println(productSalesDTO);
//            }
//
//
//    }



//    @Test
//    public void testfindOrdersImessage(){
//        List<Order> orders = orderService.findOrdersImessage(1l);
//        orders.forEach(order -> {
//            System.out.println("orderId: " + order.getOrderId());
//            System.out.println("orderNo: " + order.getOrderNo());
//            System.out.println("userId: " + order.getUser().getUserId());
//            System.out.println("totalAmount: " + order.getTotalAmount());
//            System.out.println("orderStatus"+order.getOrderStatus());
//            System.out.println("paymentStatus"+order.getPaymentMethod());
//            System.out.println("shippingStatus"+order.getShippingAddress());
//            System.out.println("orderTime"+order.getOrderTime());
//            System.out.println("updateTime"+order.getUpdateTime());
//            System.out.println("---");
//        });
//    }
}
