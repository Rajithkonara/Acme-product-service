package com.acme.products.wrapper;

import com.acme.products.domain.BaseDto;
import com.acme.products.domain.response.ResponseDto;
import com.acme.products.enums.ResponseStatusType;

import lombok.Getter;

@Getter
public class ResponseWrapper implements BaseDto {

  private final ResponseStatusType status;
  private final String message;
  private final ResponseDto data;

  public ResponseWrapper(ResponseStatusType statusType, String message, ResponseDto data) {
    this.status = statusType;
    this.message = message;
    this.data = data;
  }
}
