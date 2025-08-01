package com.example.reactDemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reactDemo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
