package com.cyx.orderserver.controller;

import com.cyx.orderserver.model.Order;
import com.cyx.orderserver.service.AccountHttpService;
import com.cyx.orderserver.service.GoodsHttpService;
import com.cyx.orderserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsHttpService goodsHttpService;

    @Autowired
    private AccountHttpService accountHttpService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/init")
    public String init(){
        restTemplate.getForEntity("http://localhost:11000/account/init",null,Void.TYPE);
        restTemplate.getForEntity("http://localhost:12000/goods/init",null,Void.TYPE);
        return "success";
    }

    //原始操作,未引入TCC,分布式事务失效.
    @GetMapping("/addTest")
    public String addTest(){
        //远程调用初始化接口
        restTemplate.getForEntity("http://localhost:11000/account/init",null,Void.TYPE);
        restTemplate.getForEntity("http://localhost:12000/goods/init",null,Void.TYPE);
        //1. 创建订单表
        Order order = new Order();
        order.setAccountName("cyx");
        order.setOrderNo("test01");
        order.setGoodsId(1);
        order.setGoodsNumber(10);
        //order.setTotal(new BigDecimal("50"));
        order.setTotal(new BigDecimal("300"));
        order.setStatus(Order.STATUS_CREATED);//直接创建订单成功
        Order save = orderService.save(order);
        System.out.println(save.toString());
        //2.调用远程服务减商品库存
        goodsHttpService.decreaseGoods(order);
        //3.调用远程服务减账户金额
        accountHttpService.decreaseAccount(order);
        return "success";
    }
}
