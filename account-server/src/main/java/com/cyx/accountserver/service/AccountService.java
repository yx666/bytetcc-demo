package com.cyx.accountserver.service;

import com.cyx.accountserver.model.Account;
import com.cyx.accountserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account findByAccountName(String accountName){
        return accountRepository.findByAccountName(accountName);
    }

    public Account save(Account account){
        return accountRepository.save(account);
    }
}
