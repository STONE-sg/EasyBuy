package dao;

import entity.Goods;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GoodsDao {
    int addGoods(Goods goods);
    List<Goods> findGoods(Goods goods);
    int updateGoodsInfo(Goods goods);
    int deleteGoods(Goods goods);
}