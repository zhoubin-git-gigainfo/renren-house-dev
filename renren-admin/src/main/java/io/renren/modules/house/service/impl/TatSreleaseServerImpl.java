package io.renren.modules.house.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.datasources.DataSourceNames;
import io.renren.datasources.annotation.DataSource;
import io.renren.modules.house.dao.TatSreleaseDao;
import io.renren.modules.house.entity.TatSreleaseEntity;
import io.renren.modules.house.service.TatSreleaseServer;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("tatSreleaseServer")
public class TatSreleaseServerImpl extends ServiceImpl<TatSreleaseDao, TatSreleaseEntity> implements TatSreleaseServer {

    @DataSource(name = DataSourceNames.SECOND)
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TatSreleaseEntity> page = this.selectPage(new Query<TatSreleaseEntity>(params).getPage());

        return new PageUtils(page);
    }
}
