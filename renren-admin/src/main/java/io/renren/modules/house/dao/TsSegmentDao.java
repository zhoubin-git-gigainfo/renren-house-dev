package io.renren.modules.house.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.house.entity.TsSegmentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ${comments}
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-10-18 17:10:47
 */
@Mapper
public interface TsSegmentDao extends BaseMapper<TsSegmentEntity> {

    List<TsSegmentEntity> queryBySgType(String value);

}
