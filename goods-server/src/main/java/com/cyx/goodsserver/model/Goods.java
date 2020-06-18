package com.cyx.goodsserver.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "t_goods")
public class Goods {

    public static final String STATUS_FROZE = "2";
    public static final String STATUS_ACTIVE = "1";
    public static final String STATUS_DEL = "0";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "goods_name")
    private String goodsName;
    private BigDecimal price;
    private int number;
    private int froze;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFroze() {
        return froze;
    }

    public void setFroze(int froze) {
        this.froze = froze;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Goods() {
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", froze=" + froze +
                ", status='" + status + '\'' +
                '}';
    }
}
