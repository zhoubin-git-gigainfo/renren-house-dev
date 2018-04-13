package io.renren.modules.house.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.house.dao.ToLogDao;
import io.renren.modules.house.entity.ToLogEntity;
import io.renren.modules.house.service.ToLogService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("toLogService")
public class ToLogServiceImpl extends ServiceImpl<ToLogDao, ToLogEntity> implements ToLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ToLogEntity> page = this.selectPage(
                new Query<ToLogEntity>(params).getPage(),
                new EntityWrapper<ToLogEntity>()
        );

        return new PageUtils(page);
    }

}
