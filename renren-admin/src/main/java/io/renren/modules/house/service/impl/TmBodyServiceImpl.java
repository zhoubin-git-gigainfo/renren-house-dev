package io.renren.modules.house.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.house.dao.TmBodyDao;
import io.renren.modules.house.entity.TmBodyEntity;
import io.renren.modules.house.service.TmBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("tmBodyService")
public class TmBodyServiceImpl extends ServiceImpl<TmBodyDao, TmBodyEntity> implements TmBodyService {

    @Autowired
    private TmBodyDao tmBodyDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TmBodyEntity> page = this.selectPage(
                new Query<TmBodyEntity>(params).getPage(),
                new EntityWrapper<TmBodyEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<TmBodyEntity> selectListByCid(String rId) {
        return tmBodyDao.selectListByCid(rId);
    }

}
