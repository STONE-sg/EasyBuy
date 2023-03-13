package dao;

import entity.GoodsCat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GoodsCatDao {
//    public GoodsCat findById(int cat_id);
//
//    public GoodsCat findByName(String name);
//    public List<GoodsCat> findByCatPathDESC(int length);
//    public List<GoodsCat> findByParentIdDESC(int parent_id);
//    public int addCat(GoodsCat goodsCat);

    int addGoodsCat(GoodsCat goodsCat);
    List<GoodsCat> findGoodsCat(GoodsCat goodsCat);
    int updateGoodsCatInfo(GoodsCat goodsCat);
    int deleteGoodsCat(GoodsCat goodsCat);
}
