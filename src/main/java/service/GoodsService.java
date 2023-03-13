package service;

import dao.GoodsDao;
import entity.Goods;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: stone
 * @program: EasyBuy
 * @description:
 * @date: 2021-12-22 17:18:54
 */
@Service
public class GoodsService {
    private final GoodsDao goodsDao;

    public GoodsService(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    public Result addGoods(Goods goods){
        Result result=new Result();
        int affectedRowCount=goodsDao.addGoods(goods);
        System.out.println(affectedRowCount);
        if (affectedRowCount == 1){
            result.setStatus(0);
            result.setMsg("添加商品成功");
        } else {
            result.setMsg("添加商品失败");
        }
        return result;
    }

    public Result findGoods(Goods goods){
        Result result=new Result();
        List<Goods> goodsList=goodsDao.findGoods(goods);
        result.setData(goodsList);
        return result;
    }

    public Result updateGoodsInfo(Goods goods){
        Result result=new Result();
        int affectedRowCount=goodsDao.updateGoodsInfo(goods);
        if (affectedRowCount == 1){
            result.setStatus(0);
            result.setMsg("修改商品信息成功");
        } else {
            result.setMsg("修改商品信息失败");
        }
        return result;
    }

    public Result deleteGoods(Goods goods){
        Result result=new Result();
        goodsDao.deleteGoods(goods);
        result.setMsg("删除商品完成");
        return result;
    }
}
