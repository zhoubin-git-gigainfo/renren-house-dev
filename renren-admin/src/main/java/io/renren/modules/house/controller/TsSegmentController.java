package io.renren.modules.house.controller;

import io.renren.common.utils.R;
import io.renren.modules.house.entity.TsSegmentEntity;
import io.renren.modules.house.service.TsSegmentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分段信息
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-03-22 10:10:47
 */
@RestController
@RequestMapping("/api/house/tssgment")
@Api("分段信息")
public class TsSegmentController {

    @Autowired
    private TsSegmentService tsSegmentService;

    /**
     * 根据sgType查询对应的分段信息
     *
     * @param sgType *
     *               2248249          面积分段
     *               2248250          价格分段
     *               100000028101     层次分段
     *               100000028100     房龄分段
     *               100001573095     建成年代分段
     * @return
     */
    @RequestMapping("list")
    public R list(String sgType) {
        List<TsSegmentEntity> maps = tsSegmentService.queryBySgType(sgType);
        return R.ok().put("page", maps);
    }

}
