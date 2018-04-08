package io.renren.modules.house.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.house.entity.TatContractEntity;

import java.util.Map;

/**
 * 合体业务主表
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-08 14:56:01
 */
public interface TatContractService extends IService<TatContractEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

