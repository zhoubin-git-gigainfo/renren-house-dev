package io.renren.modules.house.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.house.entity.TatSechlistingEntity;

import java.util.Map;

public interface TatSechlistingServer extends IService<TatSechlistingEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
