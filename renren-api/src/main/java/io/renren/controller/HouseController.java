package io.renren.controller;

import io.renren.entity.TmMbodycardEntity;
import io.renren.service.HouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存量房-房屋核验
 */
@RestController
@RequestMapping("/api/house")
@Api(tags = "存量房-房屋核验")
public class HouseController {

    @Autowired
    private HouseService houseService;

    /**
     * @param cdno  1000045228
     * @param ic_no 143020000181
     * @return
     */
    @PostMapping("obligee")
    @ApiOperation("  ")
    public Map getTmMBodyCardListByCdnoOrContractno(String cdno, String ic_no) {
        if (cdno == null || (cdno != null && cdno.equals("")))
            return null;
        Map map = new HashMap();
        //主体
        List<TmMbodycardEntity> list = houseService.queryObligee(cdno, ic_no);
        //客体
        List<Map> maps = houseService.getHouseByCdnoAndIcno(cdno, ic_no);
        maps.stream().forEach(house -> {
            //客体状态
            List<Map> m = houseService.queryState(Long.parseLong(house.get("HID") + ""));
            house.put("states", m);
        });
        map.put("mbodycard", list);
        map.put("house", maps);
        return map;
    }

}
