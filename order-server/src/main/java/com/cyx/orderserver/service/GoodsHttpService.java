package com.cyx.orderserver.service;

import com.cyx.orderserver.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoodsHttpService {

    @Autowired
    private RestTemplate restTemplate;

    public void decreaseGoods(Order order){
        String result = restTemplate.postForObject("http://localhost:12000/goods/decrease", order, String.class);
        if(!"success".equals(result)){
            throw new RuntimeException("调用远程商品服务失败");
        }
    }

    public void decreaseGoodsTcc(Order order){
        String result = restTemplate.postForObject("http://localhost:12000/tcc/goods/decrease", order, String.class);
        if(!"success".equals(result)){
            throw new RuntimeException("调用远程商品服务失败");
        }
    }

    public void decreaseGoodsTccWithoutException(Order order){
        String result = restTemplate.postForObject("http://localhost:12000/tcc/goods/decrease", order, String.class);
    }
}
