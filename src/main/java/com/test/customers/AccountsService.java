package com.test.customers;

import com.test.customers.domain.Account;
import com.test.customers.domain.AccountWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class AccountsService {

    private final RestTemplate accountsServiceRestTemplate;

    @Value("${accounts.service.baseurl}")
    private String accountsServiceUrl;

    public AccountsService(RestTemplate accountsServiceRestTemplate) {
        this.accountsServiceRestTemplate = accountsServiceRestTemplate;
    }

    public List<Account> getAccountsForUser(String user) {
        return accountsServiceRestTemplate.getForEntity(accountsServiceUrl, AccountWrapper.class, user)
                .getBody()
                .getAccounts();
    }
}
