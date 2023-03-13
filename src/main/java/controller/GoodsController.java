package controller;

import entity.Goods;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.GoodsService;
import service.Result;

/**
 * @author: stone
 * @program: EasyBuy
 * @description:
 * @date: 2021-12-22 17:19:16
 */
@Controller
@RequestMapping("goods")
public class GoodsController {
    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    //新增商品
    @PostMapping("/add")
    @ResponseBody
    public Result addGoods(Goods goods) {
        System.out.println("获取待新增商品信息：" + goods);
        return goodsService.addGoods(goods);
    }

    @GetMapping("/find")
    @ResponseBody
    public Result findGoods(Goods goods) {
        System.out.println("获取待查询商品信息：" + goods);
        return goodsService.findGoods(goods);
    }

    @PutMapping("/update")
    @ResponseBody
    public Result updateGoodsInfo(Goods goods) {
        System.out.println("获取待更新商品信息：" + goods);
        return goodsService.updateGoodsInfo(goods);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Result deleteGoods(Goods goods) {
        System.out.println("获取待删除商品信息：" + goods);
        return goodsService.deleteGoods(goods);
    }
}
