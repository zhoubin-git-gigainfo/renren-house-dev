package io.renren.modules.house.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.house.entity.TatSreleaseEntity;
import io.renren.modules.house.service.TatSreleaseServer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 挂牌房源详情
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-03-22 10:10:47
 */
@RestController
@RequestMapping("/app/house/srelease")
@Api("APP登录接口")
public class TatSreleaseController {

    @Autowired
    private TatSreleaseServer tatSreleaseServer;

    @RequestMapping("/page")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tatSreleaseServer.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("/info/{sseq}")
    public R info(@PathVariable("sseq") String sseq) {
        TatSreleaseEntity entity = tatSreleaseServer.selectById(sseq);
        return R.ok().put("entity", entity);
    }

}
