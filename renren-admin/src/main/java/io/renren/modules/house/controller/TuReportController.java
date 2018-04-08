package io.renren.modules.house.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.house.entity.TuReportEntity;
import io.renren.modules.house.service.TuReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 合同文本
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-08 14:56:01
 */
@RestController
@RequestMapping("sys/tureport")
public class TuReportController {
    @Autowired
    private TuReportService tuReportService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:tureport:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tuReportService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{rId}")
    @RequiresPermissions("sys:tureport:info")
    public R info(@PathVariable("rId") Integer rId) {
        TuReportEntity tuReport = tuReportService.selectById(rId);

        return R.ok().put("tuReport", tuReport);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:tureport:save")
    public R save(@RequestBody TuReportEntity tuReport) {
        tuReportService.insert(tuReport);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:tureport:update")
    public R update(@RequestBody TuReportEntity tuReport) {
        tuReportService.updateById(tuReport);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:tureport:delete")
    public R delete(@RequestBody Integer[] rIds) {
        tuReportService.deleteBatchIds(Arrays.asList(rIds));

        return R.ok();
    }

}
