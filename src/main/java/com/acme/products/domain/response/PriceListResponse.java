package com.acme.products.domain.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PriceListResponse implements ResponseDto {

  private final List<PriceDisplayResponse> priceList;
}
