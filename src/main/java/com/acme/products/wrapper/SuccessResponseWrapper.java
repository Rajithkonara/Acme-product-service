package com.acme.products.wrapper;

import com.acme.products.domain.response.ResponseDto;
import com.acme.products.enums.ResponseStatusType;

import lombok.Getter;

/** Success Response Wrapper */
@Getter
public class SuccessResponseWrapper extends ResponseWrapper {

  public SuccessResponseWrapper(String message, ResponseDto data) {
    super(ResponseStatusType.SUCCESS, message, data);
  }
}
