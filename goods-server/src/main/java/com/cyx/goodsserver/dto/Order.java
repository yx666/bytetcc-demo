package com.cyx.goodsserver.dto;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 简化使用传输模型,实际使用中需要填充更多参数
 */
public class Order {

    public static final String STATUS_PENDING = "2";//未定
    public static final String STATUS_CREATED = "1";//已创建
    public static final String STATUS_CANCEL = "0";//已取消


    private int id;
    private String orderNo;
    private String accountName;
    private int goodsId;
    private int goodsNumber;
    private String status;
    private BigDecimal total;

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", accountName='" + accountName + '\'' +
                ", goodsId=" + goodsId +
                ", goodsNumber=" + goodsNumber +
                ", status='" + status + '\'' +
                ", total=" + total +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
