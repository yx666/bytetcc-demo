package com.cyx.orderserver.repository;

import com.cyx.orderserver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    Order findByOrderNo(String orderNo);
}
