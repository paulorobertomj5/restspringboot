package com.springboot.restspringbootjpa.repository;

import com.springboot.restspringbootjpa.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
