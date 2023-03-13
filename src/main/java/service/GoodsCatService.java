package service;

import dao.GoodsCatDao;
import entity.GoodsCat;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: stone
 * @program: EasyBuy
 * @description:
 * @date: 2021-12-08 10:26:58
 */
@Service
public class GoodsCatService {
    private final GoodsCatDao goodsCatDao;

    public GoodsCatService(GoodsCatDao goodsCatDao) {
        this.goodsCatDao = goodsCatDao;
    }

    public Result addGoodsCat(GoodsCat goodsCat){
        Result result=new Result();
        int affectedRowCount=goodsCatDao.addGoodsCat(goodsCat);
        System.out.println(affectedRowCount);
        if (affectedRowCount == 1){
            result.setStatus(0);
            result.setMsg("添加分类成功");
        } else {
            result.setMsg("添加分类失败");
        }
        return result;
    }

    public Result findGoodsCat(GoodsCat goodsCat){
        Result result=new Result();
        List<GoodsCat> goodsCats=goodsCatDao.findGoodsCat(goodsCat);
        result.setData(goodsCats);
        return result;
    }

    public Result updateGoodsCatInfo(GoodsCat goodsCat){
        Result result=new Result();
        int affectedRowCount=goodsCatDao.updateGoodsCatInfo(goodsCat);
        if (affectedRowCount == 1){
            result.setStatus(0);
            result.setMsg("修改分类信息成功");
        } else {
            result.setMsg("修改分类信息失败");
        }
        return result;
    }

    public Result deleteGoodsCat(GoodsCat goodsCat){
        Result result=new Result();
        goodsCatDao.deleteGoodsCat(goodsCat);
        result.setMsg("删除分类完成");
        return result;
    }
}




/*    //新增分类
    public Result addCat(String name, int parentId, int typeId) {
        Result result = new Result();
        //判断是否存在此分类
        GoodsCat gc = goodsCatDao.findByName(name);
        if (gc != null){
            result.setStatus(1);
            result.setMsg("此分类已存在");
            return result;
        }
        if (parentId != 0){
            GoodsCat parentGc = goodsCatDao.findById(parentId);
            //判断父分类是否存在
            if (parentGc == null){
                result.setStatus(1);
                result.setMsg("父分类不存在");
                return result;
            }
            //判断父分类是否可用
            if (parentGc.getList_show() != 0){
                result.setStatus(1);
                result.setMsg("父分类不可用");
                return result;
            }
        }
        //没有父分类，parentId=0
        String catPath;
        if (parentId == 0){
            List<GoodsCat> gcs1 = goodsCatDao.findByCatPathDESC(3);
            String path1 = gcs1.get(0).getCat_path();
            String[] pathes1 = path1.split(",");
            int pathLength1 = Integer.parseInt(pathes1[1]);
            pathLength1++;
            catPath = "0,"+pathLength1;
        } else {
            GoodsCat checkGc = goodsCatDao.findById(parentId);
            if (checkGc.getParent_id() != 0){
                result.setStatus(1);
                result.setMsg("此分类不是顶级分类，不能在其下添加分类");
                return result;
            }
            List<GoodsCat> gcs2 = goodsCatDao.findByParentIdDESC(parentId);
            if (gcs2.isEmpty()){
                catPath = checkGc.getCat_path()+",1";
            } else {
                String[] pathes2 = gcs2.get(0).getCat_path().split(",");
                int pathLength2 = Integer.parseInt(pathes2[2]);
                pathLength2++;
                catPath = checkGc.getCat_path()+","+pathLength2;
            }
        }

        GoodsCat goodsCat = new GoodsCat();
        goodsCat.setName(name);
        goodsCat.setParent_id(parentId);
        goodsCat.setCat_path(catPath);
        goodsCat.setGoods_count(0);
        goodsCat.setSort(0);//默认没有排序
        goodsCat.setType_id(typeId);
        goodsCat.setList_show(0);//默认没有可以显示
        goodsCat.setModifytime(System.currentTimeMillis());
        goodsCatDao.addCat(goodsCat);
        result.setStatus(0);
        result.setMsg("新增分类成功");
        result.setData(goodsCat);
        return result;
    }*/
