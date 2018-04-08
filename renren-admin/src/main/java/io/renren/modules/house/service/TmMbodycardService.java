package io.renren.modules.house.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.house.entity.check.TmMbodycardEntity;

import java.util.Map;

public interface TmMbodycardService extends IService<TmMbodycardEntity> {

    PageUtils queryEntity(Map<String, Object> params);

}
