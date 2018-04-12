package io.renren.modules.house.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.house.entity.ToLogEntity;
import io.renren.modules.house.service.ToLogService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 接口日志表
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-12 15:26:34
 */
@RestController
@RequestMapping("house/tolog")
public class ToLogController {
    @Autowired
    private ToLogService toLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("house:tolog:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = toLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("house:tolog:info")
    public R info(@PathVariable("id") String id) {
        ToLogEntity toLog = toLogService.selectById(id);

        return R.ok().put("toLog", toLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("house:tolog:save")
    public R save(@RequestBody ToLogEntity toLog) {
        toLogService.insert(toLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("house:tolog:update")
    public R update(@RequestBody ToLogEntity toLog) {
        toLogService.updateById(toLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("house:tolog:delete")
    public R delete(@RequestBody String[] ids) {
        toLogService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
