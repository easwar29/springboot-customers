package com.test.customers.repository;

import com.test.customers.domain.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByAccountOwnerIn(Collection<Long> accountOwner);

    @Query("SELECT SUM(customer.aggregatedRevenue) FROM Customer customer where customer.accountOwner " +
            "= (select customer.accountOwner FROM Customer customer where customer.id = :customerId)")
    Long sumOfInvoicePurchaseAmountsForAccountOfCustomerId(@Param("customerId") Long customerId);
}
