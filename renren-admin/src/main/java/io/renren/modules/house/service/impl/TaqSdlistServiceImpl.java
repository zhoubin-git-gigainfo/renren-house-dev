package io.renren.modules.house.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.house.dao.TaqSdlistDao;
import io.renren.modules.house.entity.TaqSdlistEntity;
import io.renren.modules.house.service.TaqSdlistService;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("taqSdlistService")
public class TaqSdlistServiceImpl extends ServiceImpl<TaqSdlistDao, TaqSdlistEntity> implements TaqSdlistService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TaqSdlistEntity> page = this.selectPage(
                new Query<TaqSdlistEntity>(params).getPage(),
                new EntityWrapper<TaqSdlistEntity>()
        );

        return new PageUtils(page);
    }

}
