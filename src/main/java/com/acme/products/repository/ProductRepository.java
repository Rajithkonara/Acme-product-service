package com.acme.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.products.domain.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {}
