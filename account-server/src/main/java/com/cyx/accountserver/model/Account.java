package com.cyx.accountserver.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 账户表
 */
@Entity
@Table(name = "t_account")
public class Account implements Serializable{

    public static final String STATUS_ACTIVE = "1";
    public static final String STATUS_DEL = "0";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "account_name")
    private String accountName;
    private BigDecimal balance;
    private BigDecimal froze;
    private String status;

    public Account(String accountName, BigDecimal balance, BigDecimal froze, String status) {
        this.accountName = accountName;
        this.balance = balance;
        this.froze = froze;
        this.status = status;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFroze() {
        return froze;
    }

    public void setFroze(BigDecimal froze) {
        this.froze = froze;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", balance=" + balance +
                ", froze=" + froze +
                ", status='" + status + '\'' +
                '}';
    }
}
