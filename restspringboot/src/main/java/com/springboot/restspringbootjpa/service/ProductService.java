package com.springboot.restspringbootjpa.service;

import com.springboot.restspringbootjpa.data.vo.ProductVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    ProductVO create(ProductVO product);

    List<ProductVO> findByAll();

    ProductVO findById(Long id);

    ProductVO update(ProductVO product);

    void delete(Long id);

}
