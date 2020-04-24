package com.springboot.restspringbootjpa.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "fistName", "lastName", "address", "gender"})
public class OrderVO extends ResourceSupport implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonProperty("id")
    private Long key;
    private Integer clientId;
    private Integer productId;
    private Integer qtd;
    private Double price;


    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
        if (!(o instanceof OrderVO)) return false;
        if (!super.equals(o)) return false;
        OrderVO orderVO = (OrderVO) o;
        return getKey().equals(orderVO.getKey()) &&
                getClientId().equals(orderVO.getClientId()) &&
                getProductId().equals(orderVO.getProductId()) &&
                getQtd().equals(orderVO.getQtd()) &&
                getPrice().equals(orderVO.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getClientId(), getProductId(), getQtd(), getPrice());
    }
}
