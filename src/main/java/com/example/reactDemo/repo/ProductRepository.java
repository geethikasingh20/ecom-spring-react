package com.example.reactDemo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reactDemo.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByNameContainingIgnoreCase(String name);

}