package com.cyx.goodsserver.service;

import com.cyx.goodsserver.model.Goods;
import com.cyx.goodsserver.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    public Goods findById(int id){
        return goodsRepository.findById(id).get();
    }

    public Goods save(Goods goods){
        return goodsRepository.save(goods);
    }
}
