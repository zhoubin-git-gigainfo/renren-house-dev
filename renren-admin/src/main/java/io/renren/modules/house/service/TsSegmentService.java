package io.renren.modules.house.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.house.entity.TsSegmentEntity;

import java.util.List;

public interface TsSegmentService extends IService<TsSegmentEntity> {

    List<TsSegmentEntity> queryBySgType(String sgType);

}
