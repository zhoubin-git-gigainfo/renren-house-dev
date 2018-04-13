package io.renren.modules.house.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.house.entity.TsReportEntity;
import io.renren.modules.house.service.TsReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 文本字典
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-09 11:00:21
 */
@RestController
@RequestMapping("house/tsreport")
public class TsReportController {
    @Autowired
    private TsReportService tsReportService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("house:tsreport:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tsReportService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("house:tsreport:info")
    public R info(@PathVariable("id") String id){
			TsReportEntity tsReport = tsReportService.selectById(id);

        return R.ok().put("tsReport", tsReport);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("house:tsreport:save")
    public R save(@RequestBody TsReportEntity tsReport){

        tsReportService.insert(tsReport);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("house:tsreport:update")
    public R update(@RequestBody TsReportEntity tsReport){
			tsReportService.updateById(tsReport);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("house:tsreport:delete")
    public R delete(@RequestBody String[] ids){
			tsReportService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
