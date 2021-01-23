package com.acme.products.exception;

public class ProductNotFoundException extends ProductServiceException {
  public ProductNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
