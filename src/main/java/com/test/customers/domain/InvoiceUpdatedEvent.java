package com.test.customers.domain;

public class InvoiceUpdatedEvent {

    private Long customerId;

    private Long aggregatedRevenue;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAggregatedRevenue() {
        return aggregatedRevenue;
    }

    public void setAggregatedRevenue(Long aggregatedRevenue) {
        this.aggregatedRevenue = aggregatedRevenue;
    }
}
