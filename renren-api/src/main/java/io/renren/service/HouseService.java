package io.renren.service;


import com.baomidou.mybatisplus.service.IService;
import io.renren.entity.TmMbodycardEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2017-03-23 15:22:06
 */
public interface HouseService extends IService<TmMbodycardEntity> {

    /**
     * 查询主体信息
     *
     * @param cdno
     * @param ic_no
     * @return
     */
    List<TmMbodycardEntity> queryObligee(String cdno, String ic_no);

    /**
     * 查询客体信息
     *
     * @param cdno
     * @param ic_no
     * @return
     */
    List<Map> getHouseByCdnoAndIcno(String cdno, String ic_no);

    /**
     * 获取房屋状态
     *
     * @param hid
     * @return
     */
    List<Map> queryState(Long hid);
}
