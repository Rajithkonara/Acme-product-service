package com.acme.products.domain.response;

import java.util.List;

import com.acme.products.domain.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse implements ResponseDto {

  private final List<Product> products;
}
