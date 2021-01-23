package com.acme.products.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acme.products.domain.entity.Product;
import com.acme.products.domain.request.MultipleProductPriceRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceCalculatorService {

  private final ProductService productService;

  /***
   * Returns the total price of individual product items
   * @param productId productId
   * @param noOfItems noOfItems
   * @return price
   */
  public Double calculateProductItemPrice(String productId, int noOfItems) {

    Product product = productService.getProduct(productId);

    double totalPrice;
    int individualItems = noOfItems % product.getItemsPerCarton();
    double singleUnitsPrice = getSingleUnitsPrice(product, individualItems);
    log.info("Single unit prices : {} of the product {}", singleUnitsPrice, product.getName());

    int noOfCartons = noOfItems / product.getItemsPerCarton();
    log.info("No of cartons : {} of the product {}", noOfCartons, product.getName());

    double cartonPrice = noOfCartons * product.getCartonPrice();
    log.info("CartonPrice : {} of the product {}", cartonPrice, product.getName());
    totalPrice = cartonPrice + singleUnitsPrice;

    if (noOfCartons >= product.getDiscountMargin()) {
      totalPrice = getCartonPrice(product, totalPrice, noOfCartons);
    }

    log.info("Total price : {} of the product {}", totalPrice, product.getName());
    return totalPrice;
  }

  /**
   * Returns total price for multiple product request
   *
   * @param products products
   * @return total price
   */
  public double calculateMultipleProductPrice(List<MultipleProductPriceRequest> products) {
    double totalPrice = 0;
    for (MultipleProductPriceRequest product : products) {
      totalPrice =
          totalPrice + calculateProductItemPrice(product.getProductId(), product.getNoOfProducts());
    }
    return totalPrice;
  }

  /**
   * Calculate cartons total price
   *
   * @param productId productId
   * @param noOfCartons noOfCartons
   * @return total Price
   */
  public double calculateProductCartonPrice(String productId, int noOfCartons) {
    double totalPrice = 0;
    Product product = productService.getProduct(productId);
    double cartonPrice = noOfCartons * product.getCartonPrice();
    totalPrice = totalPrice + cartonPrice;
    if (noOfCartons >= product.getDiscountMargin()) {
      totalPrice = getCartonPrice(product, totalPrice, noOfCartons);
    }
    log.info(
        "Total price for {} cartons of product {} is {} ",
        noOfCartons,
        product.getName(),
        totalPrice);
    return totalPrice;
  }

  private double getCartonPrice(Product product, double totalPrice, int noOfCartons) {
    double discount = product.getCartonPrice() * product.getDiscount();
    log.info("Discount price : {} of the product {}", discount, product.getName());
    totalPrice = totalPrice - (discount * noOfCartons);
    return totalPrice;
  }

  private Double getUnitPrice(Double cartonPrice, int noOfItemsInACarton, double addingPercentage) {
    return (cartonPrice / noOfItemsInACarton)
        + (cartonPrice / noOfItemsInACarton * addingPercentage);
  }

  private double getSingleUnitsPrice(Product product, int individualItems) {
    return individualItems
        * getUnitPrice(
            product.getCartonPrice(), product.getItemsPerCarton(), product.getAddingPercentage());
  }
}
