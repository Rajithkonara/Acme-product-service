package com.acme.products.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
@Getter
public class Product {

  @Id private String id;
  private String imageUrl;
  private String name;
  private Double cartonPrice;
  private int itemsPerCarton;
  private Double discount;
  private int discountMargin;
  private Double addingPercentage;
  private Date createdAt;
  private Date updatedAt;
  private boolean isDeleted = false;
}
