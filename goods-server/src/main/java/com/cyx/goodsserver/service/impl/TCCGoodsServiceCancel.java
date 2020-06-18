package com.cyx.goodsserver.service.impl;

import com.cyx.goodsserver.dto.Order;
import com.cyx.goodsserver.model.Goods;
import com.cyx.goodsserver.service.GoodsService;
import com.cyx.goodsserver.service.TCCIGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service("tCCGoodsServiceCancel")
public class TCCGoodsServiceCancel implements TCCIGoodsService {

    @Autowired
    private GoodsService goodsService;

    @Override
    @Transactional
    public String decrease(Order order) {
        Goods goods  = goodsService.findById(order.getGoodsId());
        //将冻结设置为0   一般不用判断产品是否有效,能进入cancel/confirm说明try成功了
        goods.setFroze(0);
        goodsService.save(goods);
        System.out.println("=====================goods cancel  ok");
        return "success";
    }
}
