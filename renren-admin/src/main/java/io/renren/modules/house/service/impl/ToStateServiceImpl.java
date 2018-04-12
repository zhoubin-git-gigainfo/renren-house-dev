package io.renren.modules.house.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.house.dao.ToStateDao;
import io.renren.modules.house.entity.ToStateEntity;
import io.renren.modules.house.service.ToStateService;


@Service("toStateService")
public class ToStateServiceImpl extends ServiceImpl<ToStateDao, ToStateEntity> implements ToStateService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ToStateEntity> page = this.selectPage(
                new Query<ToStateEntity>(params).getPage(),
                new EntityWrapper<ToStateEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public ToStateEntity selectByEntity(ToStateEntity toStateEntity) {
        return baseMapper.selectOne(toStateEntity);
    }

}
