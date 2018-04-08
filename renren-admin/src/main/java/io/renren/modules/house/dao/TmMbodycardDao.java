package io.renren.modules.house.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.house.entity.check.TmMbodycardEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface TmMbodycardDao extends BaseMapper<TmMbodycardEntity> {

    Page<TmMbodycardEntity> queryEntity(Map<String, Object> params);

}
