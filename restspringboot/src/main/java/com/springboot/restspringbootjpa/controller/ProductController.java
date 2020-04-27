package com.springboot.restspringbootjpa.controller;

import com.springboot.restspringbootjpa.data.vo.ProductVO;
import com.springboot.restspringbootjpa.service.ProductService;
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


@Api(value = "Product API", description = "Product API description", tags = "Product")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @ApiOperation(value = "findById")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {

        ProductVO productVO = service.findById(id);

        if (productVO != null) {
            productVO.add(linkTo(methodOn(ProductController.class).findById(id)).withSelfRel());
            return ResponseEntity.status(HttpStatus.OK).body(productVO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "findByAll")
    @GetMapping(produces = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> findByAll() {

        List<ProductVO> productVOs = service.findByAll();

        if (productVOs != null && productVOs.size() > 0) {
            productVOs.stream().forEach(p -> p.add(linkTo(methodOn(ProductController.class).findById(p.getKey())).withSelfRel()));
            return ResponseEntity.status(HttpStatus.OK).body(productVOs);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "create")
    @PostMapping(produces = {"application/json", "application/x-yaml"}, consumes = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> create(@Valid @RequestBody ProductVO product) {

        ProductVO productVO = service.create(product);

        if (productVO != null) {
            productVO.add(linkTo(methodOn(ProductController.class).findById(productVO.getKey())).withSelfRel());
            return ResponseEntity.status(HttpStatus.CREATED).body(productVO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "update")
    @PutMapping(produces = {"application/json", "application/x-yaml"}, consumes = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> update(@Valid @RequestBody ProductVO product) {
        ProductVO productVO = service.update(product);
        if (productVO != null) {
            productVO.add(linkTo(methodOn(ProductController.class).findById(productVO.getKey())).withSelfRel());
            return ResponseEntity.status(HttpStatus.OK).body(productVO);
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
