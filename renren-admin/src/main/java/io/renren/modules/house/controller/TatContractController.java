package io.renren.modules.house.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.house.entity.TatContractEntity;
import io.renren.modules.house.service.TatContractService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 合体业务主表
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-08 14:56:01
 */
@RestController
@RequestMapping("sys/tatcontract")
public class TatContractController {
    @Autowired
    private TatContractService tatContractService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:tatcontract:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tatContractService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{cId}")
    @RequiresPermissions("sys:tatcontract:info")
    public R info(@PathVariable("cId") String cId) {
        TatContractEntity tatContract = tatContractService.selectById(cId);

        return R.ok().put("tatContract", tatContract);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:tatcontract:save")
    public R save(@RequestBody TatContractEntity tatContract) {
        tatContractService.insert(tatContract);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:tatcontract:update")
    public R update(@RequestBody TatContractEntity tatContract) {
        tatContractService.updateById(tatContract);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:tatcontract:delete")
    public R delete(@RequestBody String[] cIds) {
        tatContractService.deleteBatchIds(Arrays.asList(cIds));

        return R.ok();
    }

}
