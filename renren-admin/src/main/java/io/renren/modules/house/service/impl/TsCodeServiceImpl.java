package io.renren.modules.house.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.house.dao.TsCodeDao;
import io.renren.modules.house.entity.TsCodeEntity;
import io.renren.modules.house.service.TsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("tsCodeService")
public class TsCodeServiceImpl extends ServiceImpl<TsCodeDao, TsCodeEntity> implements TsCodeService {

    @Autowired
    private TsCodeDao tsCodeDao;

    @Override
    public List<Map> queryTreeByParentId(String value) {
        List<Map> list = new ArrayList<>();
        List<TsCodeEntity> tsCodeList = tsCodeDao.queryTreeByParentId(value);
        tsCodeList.stream().forEach(dto -> {
            Map map = new HashMap();
            map.put("id", dto.getId());
            map.put("name", dto.getName());
            list.add(map);
        });
        return list;
    }

    @Override
    public List<Map> queryListByParentId(String value) {
        List<Map> list = new ArrayList<>();
        List<TsCodeEntity> tsCodeList = tsCodeDao.queryListByParentId(value);
        tsCodeList.stream().forEach(dto -> {
            Map map = new HashMap();
            map.put("id", dto.getId());
            map.put("name", dto.getName());
            list.add(map);
        });
        return list;
    }

}
