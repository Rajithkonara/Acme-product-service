package com.acme.products.exception;

public class ProductServiceException extends RuntimeException {

  public ProductServiceException(String errorMessage) {
    super(errorMessage);
  }

  public ProductServiceException(String errorMessage, Throwable error) {
    super(errorMessage, error);
  }
}
