package com.springboot.restspringbootjpa.repository;

import com.springboot.restspringbootjpa.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
