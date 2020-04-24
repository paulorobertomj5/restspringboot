package com.springboot.restspringbootjpa.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name= "client_id", nullable = false)
    private Integer clientId;
    @Column(name= "product_id", nullable = false)
    private Integer productId;
    @Column(nullable = false)
    private Integer qtd;
    @Column(nullable = false)
    private Double price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId().equals(order.getId()) &&
                getClientId().equals(order.getClientId()) &&
                getProductId().equals(order.getProductId()) &&
                getQtd().equals(order.getQtd()) &&
                getPrice().equals(order.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getClientId(), getProductId(), getQtd(), getPrice());
    }
}
