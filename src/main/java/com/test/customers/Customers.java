package com.test.customers;

import com.test.customers.domain.Account;
import com.test.customers.domain.CreateOrUpdateCustomerRequest;
import com.test.customers.domain.Customer;
import com.test.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class Customers {

    private final CustomerRepository customerRepository;

    private final AccountsService accountsService;

    @Autowired
    public Customers(CustomerRepository customerRepository, AccountsService accountsService) {
        this.customerRepository = customerRepository;
        this.accountsService = accountsService;
    }

    @ResponseStatus(OK)
    @RequestMapping(value = "/customer/{id}", method = GET)
    public Customer customers(@PathVariable("id") Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @ResponseStatus(CREATED)
    @RequestMapping(value = "/customer", method = POST)
    public Customer createCustomer(@RequestBody CreateOrUpdateCustomerRequest createOrUpdateCustomerRequest) {
        Customer customer = new Customer();
        customer.setCreatedDate(new Date());
        customer.setAccountOwner(createOrUpdateCustomerRequest.getAccountOwner());
        customer.setName(createOrUpdateCustomerRequest.getName());
        customer.setAggregatedRevenue(0L);
        return customerRepository.save(customer);
    }

    @ResponseStatus(OK)
    @RequestMapping(value = "/customer/{id}", method = DELETE)
    public void deleteCustomer(@PathVariable("id") Long id, @RequestHeader("X-USER") String user) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        optionalCustomer.map(customer -> {
            List<Account> accountsForUser = accountsService.getAccountsForUser(user);
            return accountsForUser.stream()
                    .filter(account -> account.getOwnedBy().equalsIgnoreCase(customer.getAccountOwner()))
                    .findFirst().orElse(null);
        }).ifPresent(account -> customerRepository.deleteById(id));
    }

    @ResponseStatus(OK)
    @RequestMapping(value = "/customer/{id}", method = PUT)
    public Customer updateCustomer(@RequestBody CreateOrUpdateCustomerRequest createOrUpdateCustomerRequest,
                                   @PathVariable("id") Long id,
                                   @RequestHeader("X-USER") String user) {
        return customerRepository.findById(id).map(customer -> {
                    List<Account> accountsForUser = accountsService.getAccountsForUser(user);
                    return accountsForUser.stream()
                            .filter(account -> account.getOwnedBy().equalsIgnoreCase(customer.getAccountOwner()))
                            .findFirst().map(account -> {
                                customer.setName(createOrUpdateCustomerRequest.getName());
                                customer.setAccountOwner(createOrUpdateCustomerRequest.getAccountOwner());
                                return customerRepository.save(customer);
                            }).orElse(null);
                }
        ).orElse(null);
    }
}