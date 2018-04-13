package io.renren.modules.house.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.house.dao.TsReportDao;
import io.renren.modules.house.entity.TsReportEntity;
import io.renren.modules.house.service.TsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("tsReportService")
public class TsReportServiceImpl extends ServiceImpl<TsReportDao, TsReportEntity> implements TsReportService {

    @Autowired
    private TsReportDao tsReportDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TsReportEntity> page = this.selectPage(
                new Query<TsReportEntity>(params).getPage(),
                new EntityWrapper<TsReportEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void insertFile(TsReportEntity tsReportEntity) {
        tsReportDao.insertFile(tsReportEntity);
    }

}
