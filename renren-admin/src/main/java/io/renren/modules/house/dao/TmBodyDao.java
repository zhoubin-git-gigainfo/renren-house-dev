package io.renren.modules.house.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.house.entity.TmBodyEntity;

import java.util.List;

/**
 * 主体表
 * 
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-08 14:56:01
 */
public interface TmBodyDao extends BaseMapper<TmBodyEntity> {

    List<TmBodyEntity> selectListByCid(String rId);

}
