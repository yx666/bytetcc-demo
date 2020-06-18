package com.cyx.accountserver.service;

import com.cyx.accountserver.dto.Order;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 操作接口类,confirm/cancel需要实现同名方法进行操作,故继承同一个接口并定义在try中
 */
public interface TCCIAccountService {

     String decrease(Order order);
}
