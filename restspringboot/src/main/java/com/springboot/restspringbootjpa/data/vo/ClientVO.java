package com.springboot.restspringbootjpa.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "fistName", "lastName", "address", "gender"})
public class ClientVO extends ResourceSupport implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonProperty("id")
    private Long key;
    @NotNull
    @NotBlank
    private String fistName;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    @NotBlank
    private String address;
    @NotNull
    @NotBlank
    @Size(max = 6)
    private String gender;

    public ClientVO() {
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientVO)) return false;
        ClientVO person = (ClientVO) o;
        return getKey().equals(person.getKey()) &&
                getFistName().equals(person.getFistName()) &&
                getLastName().equals(person.getLastName()) &&
                getAddress().equals(person.getAddress()) &&
                getGender().equals(person.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getFistName(), getLastName(), getAddress(), getGender());
    }
}
