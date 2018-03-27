package io.renren.modules.house.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.house.service.TmMbodycardService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 中介公司
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-03-22 10:10:47
 */
@RestController
@RequestMapping("/app/house/tmmbodycard")
@Api("APP登录接口")
public class TmMbodycardController {

    @Autowired
    private TmMbodycardService tmMbodycardService;

    /**
     * 中介公司
     *
     * @param params
     * @return
     */
    @RequestMapping("/page")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tmMbodycardService.queryEntity(params);
        return R.ok().put("page", page);
    }

}
