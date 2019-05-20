package com.test.customers.domain;

public class CustomerRevenueUpdatedEvent {

    private Long accountId;

    private Long aggregatedRevenue;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAggregatedRevenue() {
        return aggregatedRevenue;
    }

    public void setAggregatedRevenue(Long aggregatedRevenue) {
        this.aggregatedRevenue = aggregatedRevenue;
    }
}
