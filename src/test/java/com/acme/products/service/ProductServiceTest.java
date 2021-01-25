package com.acme.products.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;

import com.acme.products.domain.entity.Product;
import com.acme.products.exception.ProductServiceException;
import com.acme.products.repository.ProductRepository;

class ProductServiceTest {

  private static final String P_ID = "001";

  @InjectMocks private ProductService productService;
  @Mock private ProductRepository productRepository;
  @Mock private Product product;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void Should_Return_ProductList() {
    when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
    List<Product> products = productService.getProducts();
    assertEquals(product, products.get(0));
  }

  @Test
  void Should_Throw_Exception_When_GetProductListFailed() {
    when(productRepository.findAll()).thenThrow(new DataAccessException("failed") {});
    assertThrows(ProductServiceException.class, () -> productService.getProducts());
  }

  @Test
  void Should_Return_ProductById() {
    when(productRepository.findById(P_ID)).thenReturn(java.util.Optional.of(product));
    Product serviceProduct = productService.getProduct(P_ID);
    assertEquals(product, serviceProduct);
  }

  @Test
  void Should_Throw_Exception_When_GetProductByIdFailed() {
    when(productRepository.findById(P_ID)).thenThrow(new DataAccessException("failed") {});
    assertThrows(ProductServiceException.class, () -> productService.getProduct(P_ID));
  }

  @Test
  void Should_Throw_Exception_When_GetProductByIdNotFound() {
    assertThrows(ProductServiceException.class, () -> productService.getProduct(P_ID));
  }
}
