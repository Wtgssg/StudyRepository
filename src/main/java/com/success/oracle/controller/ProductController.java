package com.success.oracle.controller;


import com.success.oracle.entity.Product;
import com.success.oracle.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/product")
public class ProductController  {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/findall")
    public Page<Product> findAll(Pageable pageable){
        //Pageable pageable = new PageRequest(pageNumber,pageSize,sort);
        return productRepository.findAll(pageable);
    }

}
