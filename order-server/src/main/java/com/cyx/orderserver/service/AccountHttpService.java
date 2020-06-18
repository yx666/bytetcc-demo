package com.cyx.orderserver.service;

import com.cyx.orderserver.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountHttpService {

    @Autowired
    private RestTemplate restTemplate;

    public void decreaseAccount(Order order){
        String result = restTemplate.postForObject("http://localhost:11000/account/decrease", order, String.class);
        if(!"success".equals(result)){
            throw new RuntimeException("调用远程账户服务失败");
        }
    }
    public void decreaseAccountTcc(Order order){
        String result = restTemplate.postForObject("http://localhost:11000/tcc/account/decrease", order, String.class);
        if(!"success".equals(result)){
            throw new RuntimeException("调用远程账户服务失败");
        }
    }

    public void decreaseAccountTccWithoutException(Order order){
        String result = restTemplate.postForObject("http://localhost:11000/tcc/account/decrease", order, String.class);
    }
}
