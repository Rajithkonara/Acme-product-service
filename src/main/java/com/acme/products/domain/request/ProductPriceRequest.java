package com.acme.products.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPriceRequest extends RequestDto {

  private String productId;
  private int noOfProducts;
}
