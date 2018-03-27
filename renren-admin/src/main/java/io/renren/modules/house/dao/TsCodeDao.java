package io.renren.modules.house.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.house.entity.TsCodeEntity;
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
public interface TsCodeDao extends BaseMapper<TsCodeEntity> {
    /**
     * 查询树
     *
     * @param value
     * @return
     */
    List<TsCodeEntity> queryTreeByParentId(String value);

    /**
     * 根据parentId查询
     *
     * @param value
     * @return
     */
    List<TsCodeEntity> queryListByParentId(String value);

}
