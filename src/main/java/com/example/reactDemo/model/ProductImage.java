package com.example.reactDemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductImage {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String imageUrl;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "product_id")
	    private Product product;
	    
	    
	    // Default constructor (required by JPA)
	    public ProductImage() {
	    }
	    
	    // Custom constructor
	    public ProductImage(String imageUrl, Product product) {
	        this.imageUrl = imageUrl;
	        this.product = product;
	    }
	    
	    // Getters and setters
	    public Long getId() {
	        return id;
	    }

	    public String getImageUrl() {
	        return imageUrl;
	    }

	    public void setImageUrl(String imageUrl) {
	        this.imageUrl = imageUrl;
	    }

	    public Product getProduct() {
	        return product;
	    }

	    public void setProduct(Product product) {
	        this.product = product;
	    }
	    
	}
