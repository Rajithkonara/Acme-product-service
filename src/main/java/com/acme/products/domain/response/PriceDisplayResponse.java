package com.acme.products.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PriceDisplayResponse implements ResponseDto{

  private int unit;
  private double totalPrice;

}
