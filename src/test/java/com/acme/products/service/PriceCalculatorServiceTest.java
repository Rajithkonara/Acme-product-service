package com.acme.products.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.acme.products.domain.entity.Product;
import com.acme.products.domain.request.MultipleProductPriceRequest;

class PriceCalculatorServiceTest {

  private static final String PRODUCT_ID = "001";
  private static final String PRODUCT_ID_2 = "002";

  @InjectMocks private PriceCalculatorService priceCalculatorService;
  @Mock private ProductService productService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @ParameterizedTest
  @CsvSource({"1, 11.375", "20, 175.0", "60, 472.5"})
  void Should_Return_SingleProductPrice_When_OneProductIsAdded(int onOfItems, double expected) {
    Product product = getProduct();
    when(productService.getProduct(PRODUCT_ID)).thenReturn(product);
    Double price = priceCalculatorService.calculateProductItemPrice(PRODUCT_ID, onOfItems);
    assertEquals(expected, price);
  }

  @Test
  void Should_Return_SingleProductPrice_When_OneProductIsAdded() {
    Product product = getProduct();
    when(productService.getProduct(PRODUCT_ID)).thenReturn(product);
    Double price = priceCalculatorService.calculateProductItemPrice(PRODUCT_ID, 1);
    assertEquals(11.375, price);
  }

  @Test
  void Should_Return_CartonProductPrice_When_NoOfProductItemsAreEqualToACarton() {
    Product product = getProduct();
    when(productService.getProduct(PRODUCT_ID)).thenReturn(product);
    Double price = priceCalculatorService.calculateProductItemPrice(PRODUCT_ID, 20);
    assertEquals(175.0, price);
  }

  @Test
  void
      Should_Return_CartonProductPrice_WithDiscount_When_NoOfProductItemsAre_Eligible_For_Discount() {
    Product product = getProduct();
    when(productService.getProduct(PRODUCT_ID)).thenReturn(product);
    Double price = priceCalculatorService.calculateProductItemPrice(PRODUCT_ID, 60);
    assertEquals(472.5, price);
  }

  @Test
  void
      Should_Return_CartonProductPrice_WithDiscount_When_NoOfProductItemsAre_Eligible_For_Discount_2() {
    Product product = getProductTwo();
    when(productService.getProduct(PRODUCT_ID_2)).thenReturn(product);
    Double price = priceCalculatorService.calculateProductItemPrice(PRODUCT_ID_2, 15);
    assertEquals(2227.5, price);
  }

  @ParameterizedTest
  @CsvSource({"3, 472.5", "1, 175.0"} )
  void Should_Return_CartonProductPrice_For_ProductOne(int noOfCartons, double expected) {
    Product product = getProduct();
    when(productService.getProduct(PRODUCT_ID)).thenReturn(product);
    Double price = priceCalculatorService.calculateProductCartonPrice(PRODUCT_ID, noOfCartons);
    assertEquals(expected, price);
  }

  @ParameterizedTest
  @CsvSource({"1, 825.00", "3, 2227.5", "4, 2970"} )
  void Should_Return_CartonProductPrice_For_ProductTwo(int noOfCartons, double expected) {
    Product product = getProductTwo();
    when(productService.getProduct(PRODUCT_ID_2)).thenReturn(product);
    Double price = priceCalculatorService.calculateProductCartonPrice(PRODUCT_ID_2, noOfCartons);
    assertEquals(expected, price);
  }

  @Test
  void testM() {

    MultipleProductPriceRequest priceRequest_1 = new MultipleProductPriceRequest(PRODUCT_ID, 60);
    MultipleProductPriceRequest priceRequest_2 = new MultipleProductPriceRequest(PRODUCT_ID_2, 5);

    List<MultipleProductPriceRequest> multipleProductPriceRequests = new ArrayList<>();
    multipleProductPriceRequests.add(priceRequest_1);
    multipleProductPriceRequests.add(priceRequest_2);

    when(productService.getProduct(PRODUCT_ID)).thenReturn(getProduct());
    when(productService.getProduct(PRODUCT_ID_2)).thenReturn(getProductTwo());

    double v = priceCalculatorService.calculateMultipleProductPrice(multipleProductPriceRequests);

    System.out.println("Total " + v);
  }

  private Product getProduct() {
    Product product = new Product();
    product.setId(PRODUCT_ID);
    product.setName("Penguin ears");
    product.setCartonPrice(175.00);
    product.setAddingPercentage(0.3);
    product.setDiscount(0.1);
    product.setDiscountMargin(3);
    product.setItemsPerCarton(20);
    return product;
  }

  private Product getProductTwo() {
    Product product_2 = new Product();
    product_2.setId(PRODUCT_ID_2);
    product_2.setName("Horseshoe");
    product_2.setCartonPrice(825.00);
    product_2.setAddingPercentage(0.3);
    product_2.setDiscount(0.1);
    product_2.setDiscountMargin(3);
    product_2.setItemsPerCarton(5);
    return product_2;
  }
}
