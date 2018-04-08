package io.renren.modules.house.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.house.entity.TmBodyEntity;
import io.renren.modules.house.service.TmBodyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 主体表
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-08 14:56:01
 */
@RestController
@RequestMapping("sys/tmbody")
public class TmBodyController {
    @Autowired
    private TmBodyService tmBodyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:tmbody:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tmBodyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{mId}")
    @RequiresPermissions("sys:tmbody:info")
    public R info(@PathVariable("mId") String mId) {
        TmBodyEntity tmBody = tmBodyService.selectById(mId);

        return R.ok().put("tmBody", tmBody);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:tmbody:save")
    public R save(@RequestBody TmBodyEntity tmBody) {
        tmBodyService.insert(tmBody);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:tmbody:update")
    public R update(@RequestBody TmBodyEntity tmBody) {
        tmBodyService.updateById(tmBody);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:tmbody:delete")
    public R delete(@RequestBody String[] mIds) {
        tmBodyService.deleteBatchIds(Arrays.asList(mIds));

        return R.ok();
    }

}
