package com.acme.products.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.products.domain.entity.Product;
import com.acme.products.domain.request.ProductCartonPriceRequest;
import com.acme.products.domain.request.ProductPriceRequest;
import com.acme.products.domain.response.PriceDisplayResponse;
import com.acme.products.domain.response.PriceListResponse;
import com.acme.products.domain.response.PriceResponse;
import com.acme.products.domain.response.ProductResponse;
import com.acme.products.exception.ProductServiceException;
import com.acme.products.service.PriceCalculatorService;
import com.acme.products.service.ProductService;
import com.acme.products.wrapper.ErrorResponseWrapper;
import com.acme.products.wrapper.ResponseWrapper;
import com.acme.products.wrapper.SuccessResponseWrapper;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

  private static final String SUCCESS = "Success";
  private final ProductService productService;
  private final PriceCalculatorService priceCalculatorService;

  @GetMapping(path = "")
  public ResponseEntity<ResponseWrapper> getAllProducts() {
    try {
      List<Product> products = productService.getProducts();
      ProductResponse productResponse = new ProductResponse(products);

      ResponseWrapper responseWrapper = new SuccessResponseWrapper(SUCCESS, productResponse);
      return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    } catch (ProductServiceException e) {
      return new ResponseEntity<>(
          new ErrorResponseWrapper("Error occurred while getting products", null),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(path = "price/{id}/{units}")
  public ResponseEntity<ResponseWrapper> getAllPriceByUnits(
      @PathVariable String id, @PathVariable int units) {
    try {
      List<PriceDisplayResponse> products = priceCalculatorService.getPriceList(id, units);
      PriceListResponse priceListResponse = new PriceListResponse(products);
      ResponseWrapper responseWrapper = new SuccessResponseWrapper(SUCCESS, priceListResponse);
      return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    } catch (ProductServiceException e) {
      return new ResponseEntity<>(
          new ErrorResponseWrapper("Error occurred while getting product price by units", null),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping(path = "price")
  public ResponseEntity<ResponseWrapper> calculateProductItemPrice(
      @RequestBody ProductPriceRequest priceRequest) {
    try {
      Double totalPrice =
          priceCalculatorService.calculateProductItemPrice(
              priceRequest.getProductId(), priceRequest.getNoOfProducts());
      PriceResponse priceResponse = new PriceResponse(totalPrice);
      ResponseWrapper responseWrapper = new SuccessResponseWrapper(SUCCESS, priceResponse);
      return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    } catch (ProductServiceException e) {
      return new ResponseEntity<>(
          new ErrorResponseWrapper("Error occurred while calculating price", null),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping(path = "price/carton")
  public ResponseEntity<ResponseWrapper> calculateCartonPrice(
      @RequestBody ProductCartonPriceRequest priceRequest) {
    try {
      double totalPrice =
          priceCalculatorService.calculateProductCartonPrice(
              priceRequest.getProductId(), priceRequest.getNoOfCartons());
      PriceResponse priceResponse = new PriceResponse(totalPrice);
      ResponseWrapper responseWrapper = new SuccessResponseWrapper(SUCCESS, priceResponse);
      return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    } catch (ProductServiceException e) {
      return new ResponseEntity<>(
          new ErrorResponseWrapper("Error occurred while calculating price", null),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
