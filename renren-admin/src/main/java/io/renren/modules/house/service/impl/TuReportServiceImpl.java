package io.renren.modules.house.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.house.dao.TuReportDao;
import io.renren.modules.house.entity.TuReportEntity;
import io.renren.modules.house.service.TuReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("tuReportService")
public class TuReportServiceImpl extends ServiceImpl<TuReportDao, TuReportEntity> implements TuReportService {

    @Autowired
    private TuReportDao tuReportDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TuReportEntity> page = this.selectPage(
                new Query<TuReportEntity>(params).getPage(),
                new EntityWrapper<TuReportEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void insertFile(TuReportEntity tuReportEntity) {
        tuReportDao.insertFile(tuReportEntity);
    }

    @Override
    public TuReportEntity selectByEntity(TuReportEntity tuReportEntity ) {
        return baseMapper.selectOne(tuReportEntity);
    }

}
