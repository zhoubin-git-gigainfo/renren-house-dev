package io.renren.modules.house.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.house.entity.ToStateEntity;
import io.renren.modules.house.service.ToStateService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 状态表
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-11 16:06:28
 */
@RestController
@RequestMapping("house/tostate")
public class ToStateController {
    @Autowired
    private ToStateService toStateService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("house:tostate:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = toStateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("house:tostate:info")
    public R info(@PathVariable("id") String id){
			ToStateEntity toState = toStateService.selectById(id);

        return R.ok().put("toState", toState);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("house:tostate:save")
    public R save(@RequestBody ToStateEntity toState){
			toStateService.insert(toState);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("house:tostate:update")
    public R update(@RequestBody ToStateEntity toState){
			toStateService.updateById(toState);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("house:tostate:delete")
    public R delete(@RequestBody String[] ids){
			toStateService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
