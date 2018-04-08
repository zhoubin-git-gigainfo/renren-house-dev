package io.renren.modules.house.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.house.entity.TuReportEntity;

import java.util.Map;

/**
 * 合同文本
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-08 14:56:01
 */
public interface TuReportService extends IService<TuReportEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

