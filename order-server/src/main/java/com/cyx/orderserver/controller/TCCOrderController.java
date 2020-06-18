package com.cyx.orderserver.controller;

import com.cyx.orderserver.model.Order;
import com.cyx.orderserver.repository.OrderRepository;
import com.cyx.orderserver.service.AccountHttpService;
import com.cyx.orderserver.service.GoodsHttpService;
import com.cyx.orderserver.service.OrderService;
import com.cyx.orderserver.service.TCCIOrderService;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.math.BigDecimal;

@RestController
@RequestMapping("/tcc/order")
@Compensable(
        interfaceClass = TCCIOrderService.class
        , confirmableKey = "tCCOrderServiceConfirm"
        , cancellableKey = "tCCOrderServiceCancel"
)
public class TCCOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsHttpService goodsHttpService;

    @Autowired
    private AccountHttpService accountHttpService;


    @GetMapping("/add")
    @Transactional
    public String add(String orderNo)throws Exception{

        //orderRepository.findByOrderNo("1111");
        //this.jdbcTemplate.update("update t_order set total = '30.00' where order_no = '0002'");

        //1. 创建订单表
        Order order = new Order();
        order.setAccountName("cyx");
        order.setOrderNo(orderNo);
        order.setGoodsId(1);
        order.setGoodsNumber(10);
        order.setTotal(new BigDecimal("50"));
        order.setStatus(Order.STATUS_PENDING);//设置为pending
        //注:如果在远程调用service中抛异常,本地事务将直接回滚,故不执行cancel操作,然而没有回滚?
        Order save = orderService.save(order);
        System.out.println("===order try ok," + save.toString());
        //2.调用远程服务减商品库存
        goodsHttpService.decreaseGoodsTcc(order);
        //3.调用远程服务减账户金额
        accountHttpService.decreaseAccountTcc(order);

        //不抛异常
        //2.调用远程服务减商品库存
        //goodsHttpService.decreaseGoodsTccWithoutException(order);
        //3.调用远程服务减账户金额
        //accountHttpService.decreaseAccountTccWithoutException(order);


        return "successv\n" + save.toString();
    }
}
