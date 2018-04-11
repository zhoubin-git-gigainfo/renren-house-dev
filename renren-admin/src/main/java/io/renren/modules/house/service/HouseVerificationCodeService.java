package io.renren.modules.house.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.house.entity.HouseVerificationCodeEntity;

import java.util.Map;

/**
 * 房屋核验码
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-03 11:03:59
 */
public interface HouseVerificationCodeService extends IService<HouseVerificationCodeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    HouseVerificationCodeEntity queryByHid(String hid);

    HouseVerificationCodeEntity queryByCode(String code);
}

