package io.renren.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.entity.TmMbodycardEntity;

import java.util.List;
import java.util.Map;

public interface HouseDao extends BaseMapper<TmMbodycardEntity> {

    List<TmMbodycardEntity> queryMbodycardEntityByIcNo(Map map);

    List<TmMbodycardEntity> queryMbodycardEntityByCdno(String trim);

    List<Map> getHouseByCdnoAndIcno(Map map);

    List<Map> queryState(Long hid);

}
