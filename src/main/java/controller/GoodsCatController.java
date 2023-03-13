package controller;

import entity.GoodsCat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.GoodsCatService;
import service.Result;

/**
 * @author: stone
 * @program: EasyBuy
 * @description:
 * @date: 2021-12-08 11:08:35
 */
@Controller
@RequestMapping("cat")
public class GoodsCatController {
    private final GoodsCatService goodsCatService;

    public GoodsCatController(GoodsCatService goodsCatService) {
        this.goodsCatService = goodsCatService;
    }

    //新增分类
    @PostMapping("/add")
    @ResponseBody
    public Result addCat(GoodsCat goodsCat) {
        System.out.println("获取待新增分类信息：" + goodsCat);
        return goodsCatService.addGoodsCat(goodsCat);
    }

    @GetMapping("/find")
    @ResponseBody
    public Result findGoodsCat(GoodsCat goodsCat) {
        System.out.println("获取待查询分类信息：" + goodsCat);
        return goodsCatService.findGoodsCat(goodsCat);
    }

    @PutMapping("/update")
    @ResponseBody
    public Result updateGoodsCatInfo(GoodsCat goodsCat) {
        System.out.println("获取待更新分类信息：" + goodsCat);
        return goodsCatService.updateGoodsCatInfo(goodsCat);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Result deleteGoodsCat(GoodsCat goodsCat) {
        System.out.println("获取待删除分类信息：" + goodsCat);
        return goodsCatService.deleteGoodsCat(goodsCat);
    }
}
