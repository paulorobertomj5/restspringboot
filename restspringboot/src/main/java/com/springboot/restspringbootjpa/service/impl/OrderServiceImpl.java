package com.springboot.restspringbootjpa.service.impl;

import com.springboot.restspringbootjpa.converter.DozerConverter;
import com.springboot.restspringbootjpa.data.model.Order;
import com.springboot.restspringbootjpa.data.vo.OrderVO;
import com.springboot.restspringbootjpa.exception.ResourceNotFoundException;
import com.springboot.restspringbootjpa.repository.OrderRepository;
import com.springboot.restspringbootjpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    public OrderVO create(OrderVO client) {
        var entity = DozerConverter.parseObject(client, Order.class);
        if(entity != null) {
            return DozerConverter.parseObject(repository.save(entity), OrderVO.class);
        }else{
            return null;
        }
    }

    public List<OrderVO> findByAll() {
        var list = repository.findAll();
        if(list != null && !list.isEmpty()) {
            return DozerConverter.parseListObject(repository.findAll(), OrderVO.class);
        }else{
            return  null;
        }
    }

    public OrderVO findById(Long id) {
        var entity = repository.findById(id);
        if(entity != null && !entity.isEmpty()) {
            return DozerConverter.parseObject(entity, OrderVO.class);
        }else{
            return null;
        }
    }

    public OrderVO update(OrderVO client) {
        var entity = repository.findById(client.getKey()).orElseThrow(() -> new ResourceNotFoundException("no records found for this ID"));

        if(entity != null) {

            entity.setClientId(client.getClientId());
            entity.setProductId(client.getProductId());
            entity.setQtd(client.getQtd());
            entity.setPrice(client.getPrice());

            return DozerConverter.parseObject(repository.save(entity), OrderVO.class);
        }else{
            return null;
        }
    }

    public void delete(Long id) {
        Order entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no records found for this ID"));
        repository.delete(entity);
    }

}
