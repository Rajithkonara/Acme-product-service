package com.acme.products.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductCartonPriceRequest extends RequestDto {
  private final String productId;
  private final int noOfCartons;
}
