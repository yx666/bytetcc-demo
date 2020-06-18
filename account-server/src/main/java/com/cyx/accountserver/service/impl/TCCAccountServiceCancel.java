package com.cyx.accountserver.service.impl;

import com.cyx.accountserver.dto.Order;
import com.cyx.accountserver.model.Account;
import com.cyx.accountserver.service.AccountService;
import com.cyx.accountserver.service.TCCIAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service("tCCAccountServiceCancel")
public class TCCAccountServiceCancel implements TCCIAccountService {

    @Autowired
    private AccountService accountService;

    @Transactional
    @Override
    public String decrease(Order order) {
        Account account = accountService.findByAccountName(order.getAccountName());
        //撤销是将冻结金额归为0
        account.setFroze(BigDecimal.ZERO);
        accountService.save(account);
        System.out.println("=====================account cancel  ok");
        return "success";
    }
}
