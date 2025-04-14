package com.example.demo.authUsers.Store.service;

import com.example.demo.authUsers.Store.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProduct(Long id);
    List<Product> getAllProducts();
    void deleteProduct(Long id);
}
