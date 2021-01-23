package com.acme.products.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PriceResponse implements ResponseDto {
  private final double totalPrice;
}
