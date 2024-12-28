package com.pms.service;

import com.pms.entity.Product;
import com.pms.exception.ResourceNotFoundException;
import com.pms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Cacheable("products")
    public Product getProductById(Long id) throws ResourceNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    }

    @CachePut(value = "products", key = "#product.id")
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @CachePut(value = "products", key = "#product.id")
    public Product updateProduct(Long id, Product productDetails) throws ResourceNotFoundException {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        return repository.save(product);
    }

    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) throws ResourceNotFoundException {
        Product product = getProductById(id);
        repository.delete(product);
    }
}
