package io.renren.modules.house.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.datasources.DataSourceNames;
import io.renren.datasources.annotation.DataSource;
import io.renren.modules.house.dao.TatSechlistingDao;
import io.renren.modules.house.entity.TatSechlistingEntity;
import io.renren.modules.house.service.TatSechlistingServer;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("tatSechlistingServer")
public class TatSechlistingServerImpl extends ServiceImpl<TatSechlistingDao, TatSechlistingEntity> implements TatSechlistingServer {

    @Override
    @DataSource(name = DataSourceNames.SECOND)
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TatSechlistingEntity> page = this.selectPage(new Query<TatSechlistingEntity>(params).getPage());
        return new PageUtils(page);
    }

}
