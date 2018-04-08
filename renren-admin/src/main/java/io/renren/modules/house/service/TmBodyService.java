package io.renren.modules.house.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.house.entity.TmBodyEntity;

import java.util.Map;

/**
 * 主体表
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-08 14:56:01
 */
public interface TmBodyService extends IService<TmBodyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

