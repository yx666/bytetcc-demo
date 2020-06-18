package com.cyx.goodsserver.controller;

import com.cyx.goodsserver.dto.Order;
import com.cyx.goodsserver.model.Goods;
import com.cyx.goodsserver.service.GoodsService;
import com.cyx.goodsserver.service.TCCIGoodsService;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tcc/goods")
@Compensable(
        interfaceClass = TCCIGoodsService.class
        , confirmableKey = "tCCGoodsServiceConfirm"
        , cancellableKey = "tCCGoodsServiceCancel"
)
public class TCCGoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/decrease")
    @Transactional
    public String decrease(@RequestBody Order order){

        Goods goods  = goodsService.findById(order.getGoodsId());
        if(goods == null){
            return "failed,goods is not exist;";
        }
        int usefulNumber = goods.getNumber() - goods.getFroze();
        if(order.getGoodsNumber() > usefulNumber){
            return "failed,goods number is not enough;";
        }
        //设置冻结数量
        goods.setFroze(order.getGoodsNumber());
        goodsService.save(goods);
        System.out.println("=====" + goods.toString());
        return "success";
    }
}
