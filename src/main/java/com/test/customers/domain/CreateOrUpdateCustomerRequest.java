package com.test.customers.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrUpdateCustomerRequest {

    private String name;

    private Long accountOwner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(Long accountOwner) {
        this.accountOwner = accountOwner;
    }

}
