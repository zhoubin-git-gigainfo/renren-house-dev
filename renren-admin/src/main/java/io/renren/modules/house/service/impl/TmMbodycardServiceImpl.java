package io.renren.modules.house.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.modules.house.dao.TmMbodycardDao;
import io.renren.modules.house.entity.check.TmMbodycardEntity;
import io.renren.modules.house.service.TmMbodycardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("tmMbodycardService")
public class TmMbodycardServiceImpl extends ServiceImpl<TmMbodycardDao, TmMbodycardEntity> implements TmMbodycardService {

    @Autowired
    private TmMbodycardDao tmMbodycardDao;

    @Override
    public PageUtils queryEntity(Map<String, Object> params) {
        Page<TmMbodycardEntity> page = tmMbodycardDao.queryEntity(params);
        return new PageUtils(page);
    }

}
