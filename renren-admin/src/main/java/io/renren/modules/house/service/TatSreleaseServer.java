package io.renren.modules.house.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.house.entity.TatSreleaseEntity;

import java.util.Map;

public interface TatSreleaseServer extends IService<TatSreleaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
