package com.test.customers.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_rev")
public class CustomerRevenue {

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    private Long aggrev;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAggrev() {
        return aggrev;
    }

    public void setAggrev(Long aggrev) {
        this.aggrev = aggrev;
    }
}
