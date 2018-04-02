package io.renren.modules.house.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 房源核验实体封装类
 */
public class HouseCheckEntity implements Serializable {

    private List<TmMbodycardEntity> mbodycard;
    private List<HouseEntity> house;
}
