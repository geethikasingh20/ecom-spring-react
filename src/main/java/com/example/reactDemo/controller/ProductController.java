package com.example.reactDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.reactDemo.model.Product;
import com.example.reactDemo.model.ProductImage;
import com.example.reactDemo.repo.ProductImageRepository;
import com.example.reactDemo.repo.ProductRepository;

@RestController
@RequestMapping("/api/products")
//@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductImageRepository productImageRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updated) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updated.getName());
                    product.setPrice(updated.getPrice());
                    product.setImages(updated.getImages());
                    Product saved = productRepository.save(product);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
        	productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    
	@GetMapping("/search") 
	public List<Product> searchProducts(@RequestParam String query) { 
		
		return productRepository.findByNameContainingIgnoreCase(query); 
		}
	
	
	@PostMapping("/{id}/images")
	public ResponseEntity<?> addImages(@PathVariable Long id, @RequestBody List<String> imageUrls) {

		Product product = productRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

		List<ProductImage> images = imageUrls.stream()
	            .map(url -> new ProductImage(url, product))
	            .toList();

	    productImageRepository.saveAll(images);
	    return ResponseEntity.ok("Images added");
	}
	 
}
