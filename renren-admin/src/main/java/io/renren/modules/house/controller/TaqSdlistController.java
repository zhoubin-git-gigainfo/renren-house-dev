package io.renren.modules.house.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.house.entity.TaqSdlistEntity;
import io.renren.modules.house.service.TaqSdlistService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 业务客体-合同
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-08 14:56:01
 */
@RestController
@RequestMapping("sys/taqsdlist")
public class TaqSdlistController {
    @Autowired
    private TaqSdlistService taqSdlistService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:taqsdlist:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = taqSdlistService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:taqsdlist:info")
    public R info(@PathVariable("id") String id) {
        TaqSdlistEntity taqSdlist = taqSdlistService.selectById(id);

        return R.ok().put("taqSdlist", taqSdlist);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:taqsdlist:save")
    public R save(@RequestBody TaqSdlistEntity taqSdlist) {
        taqSdlistService.insert(taqSdlist);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:taqsdlist:update")
    public R update(@RequestBody TaqSdlistEntity taqSdlist) {
        taqSdlistService.updateById(taqSdlist);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:taqsdlist:delete")
    public R delete(@RequestBody String[] ids) {
        taqSdlistService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
