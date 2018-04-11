package io.renren.modules.house.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.house.dao.HouseVerificationCodeDao;
import io.renren.modules.house.entity.HouseVerificationCodeEntity;
import io.renren.modules.house.service.HouseVerificationCodeService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("houseVerificationCodeService")
public class HouseVerificationCodeServiceImpl extends ServiceImpl<HouseVerificationCodeDao, HouseVerificationCodeEntity> implements HouseVerificationCodeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<HouseVerificationCodeEntity> page = this.selectPage(
                new Query<HouseVerificationCodeEntity>(params).getPage(),
                new EntityWrapper<HouseVerificationCodeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public HouseVerificationCodeEntity queryByHid(String hid) {
        HouseVerificationCodeEntity entity = new HouseVerificationCodeEntity();
        entity.setHouseId(hid);
        entity.setState(1);
        return baseMapper.selectOne(entity);
    }

    @Override
    public HouseVerificationCodeEntity queryByCode(String code) {
        HouseVerificationCodeEntity entity = new HouseVerificationCodeEntity();
        entity.setCode(code);
        entity.setState(1);
        return baseMapper.selectOne(entity);
    }

}
