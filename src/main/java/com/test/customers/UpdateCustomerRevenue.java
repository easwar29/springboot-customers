package com.test.customers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.customers.domain.CustomerRevenue;
import com.test.customers.domain.CustomerRevenueUpdatedEvent;
import com.test.customers.domain.InvoiceUpdatedEvent;
import com.test.customers.repository.CustomerRepository;
import com.test.customers.repository.CustomerRevenueRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UpdateCustomerRevenue {

    private final CustomerRevenueRepository customerRevenueRepository;

    private final CustomerRepository customerRepository;

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public UpdateCustomerRevenue(CustomerRevenueRepository customerRevenueRepository, CustomerRepository customerRepository, RabbitTemplate rabbitTemplate) {
        this.customerRevenueRepository = customerRevenueRepository;
        this.customerRepository = customerRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void updateRevenue(String message) throws IOException {
        InvoiceUpdatedEvent invoiceUpdatedEvent = objectMapper.readValue(message, InvoiceUpdatedEvent.class);
        CustomerRevenue customerRevenue = new CustomerRevenue();
        customerRevenue.setAggrev(invoiceUpdatedEvent.getAggregatedRevenue());
        customerRevenue.setCustomerId(invoiceUpdatedEvent.getCustomerId());
        customerRevenueRepository.save(customerRevenue);
        Long aggregatedRevenue = customerRepository.sumOfInvoicePurchaseAmountsForAccountOfCustomerId(customerRevenue.getCustomerId());
        CustomerRevenueUpdatedEvent customerRevenueUpdatedEvent = new CustomerRevenueUpdatedEvent();
        customerRevenueUpdatedEvent.setAccountId(customerRepository.findById(customerRevenue.getCustomerId()).get().getAccountOwner());
        customerRevenueUpdatedEvent.setAggregatedRevenue(aggregatedRevenue);
        String customerRevenueUpdatedAsString = objectMapper.writeValueAsString(customerRevenueUpdatedEvent);
        rabbitTemplate.convertAndSend("customer.updated", "customer.updated." + customerRevenue.getCustomerId(), customerRevenueUpdatedAsString);
    }


}
