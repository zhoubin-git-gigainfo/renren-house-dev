package io.renren.modules.house.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.house.entity.SequenceEntity;

import java.util.Map;

/**
 * 序列表
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-03 15:22:37
 */
public interface SequenceService extends IService<SequenceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询下一个序列
     * @param value 序列名
     * @return
     */
    Integer queryNextvalSeq(String value);
}

