package com.springboot.restspringbootjpa.service.impl;

import com.springboot.restspringbootjpa.converter.DozerConverter;
import com.springboot.restspringbootjpa.data.model.Product;
import com.springboot.restspringbootjpa.data.vo.ProductVO;
import com.springboot.restspringbootjpa.exception.ResourceNotFoundException;
import com.springboot.restspringbootjpa.repository.ProductRepository;
import com.springboot.restspringbootjpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductVO create(ProductVO product) {
        var entity = DozerConverter.parseObject(product, Product.class);
        if(entity != null) {
            return DozerConverter.parseObject(repository.save(entity), ProductVO.class);
        }else{
            return null;
        }
    }

    public List<ProductVO> findByAll() {
        var list = repository.findAll();
        if(list != null && !list.isEmpty()) {
            return DozerConverter.parseListObject(repository.findAll(), ProductVO.class);
        }else{
            return  null;
        }
    }

    public ProductVO findById(Long id) {
        var entity = repository.findById(id);
        if(entity != null && !entity.isEmpty()) {
            return DozerConverter.parseObject(entity, ProductVO.class);
        }else{
            return null;
        }
    }

    public ProductVO update(ProductVO product) {
        var entity = repository.findById(product.getKey()).orElseThrow(() -> new ResourceNotFoundException("no records found for this ID"));

        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setQtd(product.getQtd());

        if(entity != null) {
            return DozerConverter.parseObject(repository.save(entity), ProductVO.class);
        }else{
            return null;
        }
    }

    public void delete(Long id) {
        Product entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no records found for this ID"));
        repository.delete(entity);
    }

}
