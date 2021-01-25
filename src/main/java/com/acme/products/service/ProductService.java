package com.acme.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.acme.products.domain.entity.Product;
import com.acme.products.exception.ProductNotFoundException;
import com.acme.products.exception.ProductServiceException;
import com.acme.products.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public List<Product> getProducts() {
    try {
      return productRepository.findAll();
    } catch (DataAccessException e) {
      throw new ProductServiceException("Error occurred while getting product list :" + e);
    }
  }

  /**
   * Return a single product
   *
   * @param productId productId
   * @return Product
   */
  public Product getProduct(String productId) {
    try {
      Optional<Product> product = productRepository.findById(productId);
      if (product.isPresent()) {
        return product.get();
      } else {
        throw new ProductNotFoundException("Product not found, product id :" + productId);
      }
    } catch (DataAccessException e) {
      throw new ProductServiceException("Error occurred while getting product : " + e);
    }
  }
}
