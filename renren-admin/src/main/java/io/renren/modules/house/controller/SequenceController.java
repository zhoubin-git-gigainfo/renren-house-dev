package io.renren.modules.house.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.house.entity.SequenceEntity;
import io.renren.modules.house.service.SequenceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 序列表
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-03 15:22:37
 */
@RestController
@RequestMapping("sys/sequence")
public class SequenceController {
    @Autowired
    private SequenceService sequenceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:sequence:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sequenceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{seqName}")
    @RequiresPermissions("sys:sequence:info")
    public R info(@PathVariable("seqName") String seqName){
			SequenceEntity sequence = sequenceService.selectById(seqName);

        return R.ok().put("sequence", sequence);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:sequence:save")
    public R save(@RequestBody SequenceEntity sequence){
			sequenceService.insert(sequence);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:sequence:update")
    public R update(@RequestBody SequenceEntity sequence){
			sequenceService.updateById(sequence);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:sequence:delete")
    public R delete(@RequestBody String[] seqNames){
			sequenceService.deleteBatchIds(Arrays.asList(seqNames));

        return R.ok();
    }

}
