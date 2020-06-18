package com.cyx.goodsserver.controller;

import com.cyx.goodsserver.dto.Order;
import com.cyx.goodsserver.model.Goods;
import com.cyx.goodsserver.repository.GoodsRepository;

import com.cyx.goodsserver.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsService goodsService;

    //============下列为基础方法

    @RequestMapping("/decrease")
    public String decrease(@RequestBody Order order){

        Goods goods  = goodsService.findById(order.getGoodsId());
        if(goods == null){
            return "failed,goods is not exist;";
        }
        int usefulNumber = goods.getNumber() - goods.getFroze();
        if(order.getGoodsNumber() > usefulNumber){
            return "failed,goods number is not enough;";
        }
        //直接设置为最终数量
        goods.setNumber(goods.getNumber() - order.getGoodsNumber());
        goodsService.save(goods);
        return "success";
    }

    /**
     * 初始化固定,测试用
     * @return
     */
    @GetMapping("/init")
    public String init(){
        Goods goods = new Goods();
        goods.setGoodsName("iphone11");
        goods.setNumber(100);
        goods.setFroze(0);
        goods.setStatus(Goods.STATUS_ACTIVE);
        goodsRepository.save(goods);
        System.out.println(goods.toString());
        return "success";
    }

    /**
     *
     * @param goodsName
     * @param number
     * @return
     */
    @GetMapping("/create")
    public String create(String goodsName,String number){
        Goods oldGoods = goodsRepository.findByGoodsName(goodsName);
        if(oldGoods != null){
            return "failed,goods is already exist;";
        }
        Goods goods = new Goods();
        goods.setGoodsName(goodsName);

        try {
            int i = Integer.parseInt(number);
            goods.setNumber(i);
        }catch (Exception e){
            return "failed,goods number wrong;";
        }
        goods.setFroze(0);
        goods.setStatus(Goods.STATUS_ACTIVE);
        goodsRepository.save(goods);
        System.out.println(goods.toString());
        return "success";
    }


    /**
     * 减库存
     * @param goodsName
     * @param number
     * @return
     */
    @GetMapping("/decrease1")
    public String decrease1(String goodsName,String number){
        Goods goods = goodsRepository.findByGoodsName(goodsName);
        if(goods == null){
            return "failed,goods is not exist;";
        }
        int usefulNumber = goods.getNumber() - goods.getFroze();
        try {
            int needNumber = Integer.parseInt(number);
            int finalNumber = usefulNumber - needNumber;
            if(finalNumber < 0){
                return "failed,goods number is not enough;";
            }
            goods.setNumber(goods.getNumber() - needNumber);
            return "suuccess";

        }catch (Exception e){
            return "failed,goods number is not enough;";
        }

    }

}
