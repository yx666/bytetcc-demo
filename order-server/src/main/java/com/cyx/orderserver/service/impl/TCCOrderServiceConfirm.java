package com.cyx.orderserver.service.impl;

import com.cyx.orderserver.model.Order;
import com.cyx.orderserver.service.OrderService;
import com.cyx.orderserver.service.TCCIOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tCCOrderServiceConfirm")
public class TCCOrderServiceConfirm implements TCCIOrderService {
    @Autowired
    private OrderService orderService;

    @Transactional
    @Override
    public String add(String orderNo) {

        Order order = orderService.findByOrderNo(orderNo);
        order.setStatus(Order.STATUS_CREATED);
        orderService.save(order);
        System.out.println("=====================order confrim  ok");
        return "success";
    }
}
