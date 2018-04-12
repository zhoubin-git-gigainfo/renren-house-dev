package io.renren.modules.house.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.house.entity.ToLogEntity;

import java.util.Map;

/**
 * 接口日志表
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-12 15:26:34
 */
public interface ToLogService extends IService<ToLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

