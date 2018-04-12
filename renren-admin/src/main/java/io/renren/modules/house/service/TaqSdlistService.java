package io.renren.modules.house.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.house.entity.TaqSdlistEntity;

import java.util.Map;

/**
 * 业务客体-合同
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-08 14:56:01
 */
public interface TaqSdlistService extends IService<TaqSdlistEntity> {

    PageUtils queryPage(Map<String, Object> params);

    TaqSdlistEntity selectOneByCid(String rId);
}

