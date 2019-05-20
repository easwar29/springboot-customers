package com.test.customers.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer")
@SecondaryTable(name = "customer_rev", pkJoinColumns=@PrimaryKeyJoinColumn(name="customer_id", referencedColumnName="id"))
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(table="customer_rev", name="aggrev")
    private Long aggregatedRevenue;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "account_owner")
    private Long accountOwner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAggregatedRevenue() {
        return aggregatedRevenue;
    }

    public void setAggregatedRevenue(Long aggregatedRevenue) {
        this.aggregatedRevenue = aggregatedRevenue;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(Long accountOwner) {
        this.accountOwner = accountOwner;
    }
}
