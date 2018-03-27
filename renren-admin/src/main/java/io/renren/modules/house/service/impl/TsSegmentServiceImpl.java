package io.renren.modules.house.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.datasources.DataSourceNames;
import io.renren.datasources.annotation.DataSource;
import io.renren.modules.house.dao.TsSegmentDao;
import io.renren.modules.house.entity.TsSegmentEntity;
import io.renren.modules.house.service.TsSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("tsSegmentService")
public class TsSegmentServiceImpl extends ServiceImpl<TsSegmentDao, TsSegmentEntity> implements TsSegmentService {

    @Autowired
    private TsSegmentDao tsSegmentDao;

    @Override
    @DataSource(name = DataSourceNames.SECOND)
    public List<TsSegmentEntity> queryBySgType(String sgType) {
        List<TsSegmentEntity> tsCodeList = tsSegmentDao.queryBySgType(sgType);
        return tsCodeList;
    }

}
