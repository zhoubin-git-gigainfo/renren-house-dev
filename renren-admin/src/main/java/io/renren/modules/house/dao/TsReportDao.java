package io.renren.modules.house.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.house.entity.TsReportEntity;

/**
 * 文本字典
 * 
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-09 11:00:21
 */
public interface TsReportDao extends BaseMapper<TsReportEntity> {

    void insertFile(TsReportEntity tsReportEntity);
}
