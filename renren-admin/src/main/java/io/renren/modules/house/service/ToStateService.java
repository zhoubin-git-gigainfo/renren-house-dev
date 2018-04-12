package io.renren.modules.house.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.house.entity.ToStateEntity;

import java.util.Map;

/**
 * 状态表
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-11 16:06:28
 */
public interface ToStateService extends IService<ToStateEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

