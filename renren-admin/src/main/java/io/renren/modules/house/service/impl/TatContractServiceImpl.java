package io.renren.modules.house.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.house.dao.TatContractDao;
import io.renren.modules.house.entity.TatContractEntity;
import io.renren.modules.house.service.TatContractService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("tatContractService")
public class TatContractServiceImpl extends ServiceImpl<TatContractDao, TatContractEntity> implements TatContractService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TatContractEntity> page = this.selectPage(
                new Query<TatContractEntity>(params).getPage(),
                new EntityWrapper<TatContractEntity>()
        );

        return new PageUtils(page);
    }

}
