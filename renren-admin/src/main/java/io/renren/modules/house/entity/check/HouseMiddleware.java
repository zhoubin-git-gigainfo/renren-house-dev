package io.renren.modules.house.entity.check;

import java.util.List;

public class HouseMiddleware {

    private List<TmMbodycardEntity> bodys;
    private List<HouseEntity> houses;

    public List<TmMbodycardEntity> getBodys() {
        return bodys;
    }

    public void setBodys(List<TmMbodycardEntity> bodys) {
        this.bodys = bodys;
    }

    public List<HouseEntity> getHouses() {
        return houses;
    }

    public void setHouses(List<HouseEntity> houses) {
        this.houses = houses;
    }
}
