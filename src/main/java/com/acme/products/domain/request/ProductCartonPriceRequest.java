package com.acme.products.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartonPriceRequest extends RequestDto {
  private String productId;
  private int noOfCartons;
}
