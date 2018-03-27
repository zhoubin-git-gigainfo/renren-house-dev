package io.renren.modules.house.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.house.entity.TsCodeEntity;

import java.util.List;
import java.util.Map;

public interface TsCodeService extends IService<TsCodeEntity> {

    /**
     * 查询树
     *
     * @param value
     * @return
     */
    List<Map> queryTreeByParentId(String value);

    /**
     * 根据parentId查询
     *
     * @param value
     * @return
     */
    List<Map> queryListByParentId(String value);
}
