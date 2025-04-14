package com.example.demo.authUsers.Store.repository;

import com.example.demo.authUsers.Store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
