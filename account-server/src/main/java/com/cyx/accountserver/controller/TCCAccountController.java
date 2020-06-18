package com.cyx.accountserver.controller;

import com.cyx.accountserver.dto.Order;
import com.cyx.accountserver.model.Account;
import com.cyx.accountserver.service.AccountService;
import com.cyx.accountserver.service.TCCIAccountService;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.math.BigDecimal;

@RestController
@RequestMapping("/tcc/account")
@Compensable(
        interfaceClass = TCCIAccountService.class
        , confirmableKey = "tCCAccountServiceConfirm"
        , cancellableKey = "tCCAccountServiceCancel"
)
public class TCCAccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/decrease")
    @Transactional(transactionManager = "jtaTransactionManager")
    public String decrease(@RequestBody Order order) throws Exception {

        Account account = accountService.findByAccountName(order.getAccountName());
        if (account == null){
            return "failed,account is not exist";
        }
        BigDecimal balance = account.getBalance();
        BigDecimal froze = account.getFroze();
        BigDecimal subtract = balance.subtract(froze);//真实余额
        BigDecimal finalBlance = subtract.subtract(order.getTotal());//消费后

        int signum = finalBlance.signum();
        //此时添加冻结金额

        System.out.println("=====" + account.toString());

        account.setFroze(order.getTotal());
        accountService.save(account);
        Account byAccountName = accountService.findByAccountName(order.getAccountName());
        System.out.println(" ==================== account try end account = " + byAccountName.toString());
        if(signum == -1){
            //throw new Exception("hehe");
            return "failed,account's balance is not enough";
        }
        return "success";
    }
}
