package com.test.customers.repository;

import com.test.customers.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByAccountOwnerIn(Collection<Long> accountOwner);
}
