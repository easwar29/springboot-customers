package com.test.customers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.customers.domain.CustomerRevenue;
import com.test.customers.domain.InvoiceUpdatedEvent;
import com.test.customers.repository.CustomerRevenueRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UpdateCustomerRevenue {

    private final CustomerRevenueRepository customerRevenueRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public UpdateCustomerRevenue(CustomerRevenueRepository customerRevenueRepository) {
        this.customerRevenueRepository = customerRevenueRepository;
    }

    public void updateRevenue(String message) throws IOException {
        InvoiceUpdatedEvent invoiceUpdatedEvent = objectMapper.readValue(message, InvoiceUpdatedEvent.class);
        CustomerRevenue customerRevenue = new CustomerRevenue();
        customerRevenue.setAggrev(invoiceUpdatedEvent.getAggregatedRevenue());
        customerRevenue.setCustomerId(invoiceUpdatedEvent.getCustomerId());
        customerRevenueRepository.save(customerRevenue);
    }


}
