package com.acme.products.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acme.products.domain.request.ProductCartonPriceRequest;
import com.acme.products.domain.request.ProductPriceRequest;
import com.acme.products.service.PriceCalculatorService;
import com.acme.products.service.ProductService;

class ProductControllerTest {

  private static final String SUCCESS = "SUCCESS";
  private static final String PRODUCT_URI = "/api/v1/product";
  @Mock private ProductService productService;
  @Mock private PriceCalculatorService priceCalculatorService;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    ProductController productController =
        new ProductController(productService, priceCalculatorService);
    mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
  }

  @Test
  void Should_ReturnOk_When_GetAllProductsSuccess() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get(PRODUCT_URI))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("$.status").value(SUCCESS));
  }

  @Test
  void Should_ReturnOk_When_GetAllItemPriceSuccess() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get(PRODUCT_URI + "/price/1/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("$.status").value(SUCCESS));
  }

  @Test
  void Should_ReturnOk_When_CalculateProductItemPriceSuccess() throws Exception {
    ProductPriceRequest priceRequest = new ProductPriceRequest("1", 1);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post(PRODUCT_URI + "/price")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(priceRequest.toJson()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("$.status").value(SUCCESS));
  }

  @Test
  void Should_ReturnOk_When_CalculateCartonPricePriceSuccess() throws Exception {
    ProductCartonPriceRequest priceRequest = new ProductCartonPriceRequest("1", 1);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post(PRODUCT_URI + "/price/carton")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(priceRequest.toJson()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("$.status").value(SUCCESS));
  }
}
