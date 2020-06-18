package com.cyx.accountserver.service.impl;

import com.cyx.accountserver.dto.Order;
import com.cyx.accountserver.model.Account;
import com.cyx.accountserver.service.AccountService;
import com.cyx.accountserver.service.TCCIAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service("tCCAccountServiceConfirm")
public class TCCAccountServiceConfirm implements TCCIAccountService {

    @Autowired
    private AccountService accountService;

    @Transactional
    @Override
    public String decrease(Order order) {
        Account account = accountService.findByAccountName(order.getAccountName());
        //提交时将余额减去冻结,并且将冻结金额归为0
        //问题:这里减去金额使用 数据库数据 还是 order参数数据,实现幂等性?
        //    减去数据库冻结可以实现幂等性,但是有并发问题,需要排除  .使用order参数可能会重复消费
        account.setBalance(account.getBalance().subtract(account.getFroze()));
        account.setFroze(BigDecimal.ZERO);
        accountService.save(account);
        System.out.println("=====================account confrim  ok");
        return "success";
    }
}
