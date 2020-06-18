package com.cyx.orderserver.service;

import com.cyx.orderserver.model.Order;
import com.cyx.orderserver.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order save(Order order){
        Order save = orderRepository.save(order);
        return save;
    }

    public Order findByOrderNo(String orderNo){
        return orderRepository.findByOrderNo(orderNo);
    }

}
