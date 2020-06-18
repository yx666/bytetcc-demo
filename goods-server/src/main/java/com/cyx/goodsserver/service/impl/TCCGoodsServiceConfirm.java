package com.cyx.goodsserver.service.impl;

import com.cyx.goodsserver.dto.Order;
import com.cyx.goodsserver.model.Goods;
import com.cyx.goodsserver.service.GoodsService;
import com.cyx.goodsserver.service.TCCIGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tCCGoodsServiceConfirm")
public class TCCGoodsServiceConfirm implements TCCIGoodsService {

    @Autowired
    private GoodsService goodsService;

    @Override
    @Transactional
    public String decrease(Order order) {
        Goods goods  = goodsService.findById(order.getGoodsId());
        if(goods == null){
            return "failed,goods is not exist;";
        }
        int usefulNumber = goods.getNumber() - goods.getFroze();
        if(order.getGoodsNumber() > usefulNumber){
            return "failed,goods number is not enough;";
        }
        //扣除冻结数量,实现幂等性
        goods.setNumber(goods.getNumber() - goods.getFroze());
        goods.setFroze(0);
        goodsService.save(goods);
        System.out.println("=====================goods confrim  ok");
        return "success";
    }
}
