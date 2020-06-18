package com.cyx.accountserver.repository;

import com.cyx.accountserver.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account,Integer>{
    Account findByAccountName(String accountName);
}
