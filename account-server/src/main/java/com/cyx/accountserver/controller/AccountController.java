package com.cyx.accountserver.controller;

import com.cyx.accountserver.dto.Order;
import com.cyx.accountserver.model.Account;
import com.cyx.accountserver.repository.AccountRepository;
import com.cyx.accountserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    //============下列为基础方法

    /**
     *
     * @param order
     * @return
     */
    @RequestMapping("/decrease")
    public String decrease(@RequestBody Order order){

        Account account = accountService.findByAccountName(order.getAccountName());
        if (account == null){
            return "failed,account is not exist";
        }
        BigDecimal balance = account.getBalance();
        BigDecimal froze = account.getFroze();
        BigDecimal subtract = balance.subtract(froze);//真实余额
        BigDecimal finalBlance = subtract.subtract(order.getTotal());//消费后

        int signum = finalBlance.signum();
        if(signum == -1){
            return "failed,account's balance is not enough";
        }
        account.setBalance(balance.subtract(order.getTotal()));
        accountService.save(account);
        System.out.println(account.toString());
        return "success";
    }


    /**
     * 初始化固定账户,测试用
     * @return
     */
    @GetMapping("/init")
    @Transactional
    public String init(){
        Account account = new Account();
        account.setAccountName("cyx");
        account.setStatus(Account.STATUS_ACTIVE);
        account.setBalance(new BigDecimal("100"));
        account.setFroze(new BigDecimal("0"));
        accountRepository.save(account);
        System.out.println(account.toString());
        return "success";
    }

    /**
     * 创建
     * @param name
     * @return
     */
    @GetMapping("/create")
    public String create(String name){
        Account oldAccount = accountRepository.findByAccountName(name);
        if(oldAccount != null){
            return "failed,account is already exist;";
        }
        Account account = new Account();
        account.setAccountName(name);
        account.setStatus(Account.STATUS_ACTIVE);
        account.setBalance(new BigDecimal("0"));
        account.setFroze(new BigDecimal("0"));
        accountRepository.save(account);
        System.out.println(account.toString());
        return "success";
    }

    /**
     * 充值就能变强
     * @param name
     * @param amount
     * @return
     */
    @GetMapping("/increAmount")
    public String beStrong(String name,String amount){
        Account account = accountRepository.findByAccountName(name);
        if (account == null){
            return "failed,account is not exist";
        }
        BigDecimal balance = account.getBalance();
        BigDecimal add = balance.add(new BigDecimal(amount));
        account.setBalance(add);
        accountRepository.save(account);
        System.out.println(account.toString());
        return "success";
    }

    /**
     * 直接消费
     * @param name
     * @param amount
     * @return
     */
    @GetMapping("/decreAmount")
    public String happy(String name,String amount){
        Account account = accountRepository.findByAccountName(name);
        if (account == null){
            return "failed,account is not exist";
        }
        BigDecimal balance = account.getBalance();
        BigDecimal froze = account.getFroze();
        BigDecimal subtract = balance.subtract(froze);//真实余额
        BigDecimal finalBlance = subtract.subtract(new BigDecimal(amount));//消费后

        int signum = finalBlance.signum();
        if(signum == -1){
            return "failed,account's balance is not enough";
        }
        account.setBalance(balance.subtract(new BigDecimal(amount)));
        accountRepository.save(account);
        System.out.println(account.toString());
        return "success";
    }

}
