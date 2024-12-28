package com.pms.test.service;

import com.pms.entity.Product;
import com.pms.exception.ResourceNotFoundException;
import com.pms.repository.ProductRepository;
import com.pms.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testSaveProduct() {
        Product product = new Product(1L, "iPhone 15", "Description1", 100000.0, 12);
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        Product createdProduct = productService.saveProduct(product);

        Assertions.assertNotNull(createdProduct);
        Assertions.assertEquals("iPhone 15", createdProduct.getName());
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    void testGetProductById() {
        Product product = new Product(1L, "iPhone 15", "Description1", 100000.0, 12);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product fetchedProduct = null;
        try {
            fetchedProduct = productService.getProductById(1L);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertNotNull(fetchedProduct);
        Assertions.assertEquals(1L, fetchedProduct.getId());
        Mockito.verify(productRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void testGetProductByIdNotFound() {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            productService.getProductById(1L);
        });
    }
}
