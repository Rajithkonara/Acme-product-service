package com.acme.products.domain;

import com.acme.products.exception.ProductServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface BaseDto {

  /**
   * This method converts object to json string.
   *
   * @return json string
   */
  default String toJson() {
    try {
      return new ObjectMapper().writeValueAsString(this);
    } catch (JsonProcessingException e) {
      throw new ProductServiceException("Object to json conversion was failed.", e);
    }
  }
}
