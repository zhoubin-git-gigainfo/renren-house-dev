package io.renren.modules.house.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.house.dao.SequenceDao;
import io.renren.modules.house.entity.SequenceEntity;
import io.renren.modules.house.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sequenceService")
public class SequenceServiceImpl extends ServiceImpl<SequenceDao, SequenceEntity> implements SequenceService {

    @Autowired
    private SequenceDao sequenceDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SequenceEntity> page = this.selectPage(
                new Query<SequenceEntity>(params).getPage(),
                new EntityWrapper<SequenceEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Integer queryNextvalSeq(String value) {
        return sequenceDao.queryNextvalSeq(value);
    }
}
