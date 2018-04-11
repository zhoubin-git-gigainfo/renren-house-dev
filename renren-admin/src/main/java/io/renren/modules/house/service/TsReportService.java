package io.renren.modules.house.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.house.entity.TsReportEntity;

import java.util.Map;

/**
 * 文本字典
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-09 11:00:21
 */
public interface TsReportService extends IService<TsReportEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void insertFile(TsReportEntity tsReportEntity);

}

