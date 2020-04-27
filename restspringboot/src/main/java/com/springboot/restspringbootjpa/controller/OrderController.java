package com.springboot.restspringbootjpa.controller;

import com.springboot.restspringbootjpa.data.vo.OrderVO;
import com.springboot.restspringbootjpa.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Api(value = "Order API", description = "Order API description", tags = "Order")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @ApiOperation(value = "findById")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {

        OrderVO orderVO = service.findById(id);

        if (orderVO != null) {
            orderVO.add(linkTo(methodOn(OrderController.class).findById(id)).withSelfRel());
            return ResponseEntity.status(HttpStatus.OK).body(orderVO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "findByAll")
    @GetMapping(produces = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> findByAll() {

        List<OrderVO> orderVOs = service.findByAll();

        if (orderVOs != null && orderVOs.size() > 0) {
            orderVOs.stream().forEach(p -> p.add(linkTo(methodOn(OrderController.class).findById(p.getKey())).withSelfRel()));
            return ResponseEntity.status(HttpStatus.OK).body(orderVOs);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "create")
    @PostMapping(produces = {"application/json", "application/x-yaml"}, consumes = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> create(@Valid @RequestBody OrderVO order) {

        OrderVO orderVO = service.create(order);

        if (orderVO != null) {
            orderVO.add(linkTo(methodOn(OrderController.class).findById(orderVO.getKey())).withSelfRel());
            return ResponseEntity.status(HttpStatus.CREATED).body(orderVO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "update")
    @PutMapping(produces = {"application/json", "application/x-yaml"}, consumes = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> update(@Valid @RequestBody OrderVO order) {
        OrderVO orderVO = service.update(order);
        if (orderVO != null) {
            orderVO.add(linkTo(methodOn(OrderController.class).findById(orderVO.getKey())).withSelfRel());
            return ResponseEntity.status(HttpStatus.OK).body(orderVO);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @ApiOperation(value = "delete")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
