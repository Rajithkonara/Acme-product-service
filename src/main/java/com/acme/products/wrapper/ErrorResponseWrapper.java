package com.acme.products.wrapper;

import com.acme.products.domain.response.ResponseDto;
import com.acme.products.enums.ResponseStatusType;

import lombok.Getter;

/** ErrorResponseWrapper */
@Getter
public class ErrorResponseWrapper extends ResponseWrapper {

  public ErrorResponseWrapper(String message, ResponseDto data) {
    super(ResponseStatusType.ERROR, message, data);
  }
}
