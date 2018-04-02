package io.renren.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.dao.HouseDao;
import io.renren.entity.TmMbodycardEntity;
import io.renren.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("houseService")
public class HouseServiceImpl extends ServiceImpl<HouseDao, TmMbodycardEntity> implements HouseService {
    @Autowired
    private HouseDao houseDao;

    @Override
    public List<TmMbodycardEntity> queryObligee(String cdno, String ic_no) {
        List<TmMbodycardEntity> list = new ArrayList<TmMbodycardEntity>();
        // 产权证号查询
        Map map = new HashMap();
        map.put("cdno", cdno);
        map.put("icno", ic_no);
        list = houseDao.queryMbodycardEntityByIcNo(map);

        // 合同编号查询
        if (list.size() == 0) {
            list = houseDao.queryMbodycardEntityByCdno(cdno.trim());
        }
        return list;
    }

    @Override
    public List<Map> getHouseByCdnoAndIcno(String cdno, String icno) {
        Map map = new HashMap();
        map.put("cdno",cdno.trim());
        map.put("icno",icno.trim());
        List<Map> list = houseDao.getHouseByCdnoAndIcno(map);

        return list;
    }

    @Override
    public List<Map> queryState(Long hid) {
        return houseDao.queryState(hid);
    }

}
