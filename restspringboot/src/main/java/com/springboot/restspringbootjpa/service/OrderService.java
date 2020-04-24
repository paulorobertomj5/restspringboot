package com.springboot.restspringbootjpa.service;

import com.springboot.restspringbootjpa.data.vo.OrderVO;

import java.util.List;


public interface OrderService {


    OrderVO create(OrderVO person);

    List<OrderVO> findByAll();

    OrderVO findById(Long id);

    OrderVO update(OrderVO person);

    void delete(Long id);

}
