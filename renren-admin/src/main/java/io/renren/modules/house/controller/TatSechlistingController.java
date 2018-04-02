package io.renren.modules.house.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.house.service.TatSechlistingServer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
/**
 * 挂牌房源
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-03-22 10:10:47
 */
@RestController
@RequestMapping("/app/house/sechlisting")
@Api("APP登录接口")
public class TatSechlistingController {

    @Autowired
    private TatSechlistingServer tatSechlistingServer;

    @PostMapping("/page")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tatSechlistingServer.queryPage(params);
        return R.ok().put("page", page);
    }

}
