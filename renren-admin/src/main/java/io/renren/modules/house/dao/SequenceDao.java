package io.renren.modules.house.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.house.entity.SequenceEntity;

/**
 * 序列表
 * 
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-03 15:22:37
 */
public interface SequenceDao extends BaseMapper<SequenceEntity> {

    Integer queryNextvalSeq(String value);
}
