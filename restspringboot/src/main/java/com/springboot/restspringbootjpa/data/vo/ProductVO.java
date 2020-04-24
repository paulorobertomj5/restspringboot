package com.springboot.restspringbootjpa.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class ProductVO extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long key;
    private String name;
    private Integer qtd;
    private Double price;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductVO)) return false;
        if (!super.equals(o)) return false;
        ProductVO productVO = (ProductVO) o;
        return getKey().equals(productVO.getKey()) &&
                getName().equals(productVO.getName()) &&
                getQtd().equals(productVO.getQtd()) &&
                getPrice().equals(productVO.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getName(), getQtd(), getPrice());
    }
}
