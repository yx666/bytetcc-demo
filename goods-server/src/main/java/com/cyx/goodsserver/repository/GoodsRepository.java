package com.cyx.goodsserver.repository;

import com.cyx.goodsserver.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods,Integer> {

    Goods findByGoodsName(String goodsName);
}
