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

import io.renren.modules.house.entity.HouseVerificationCodeEntity;
import io.renren.modules.house.service.HouseVerificationCodeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 房屋核验码
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-03 11:03:59
 */
@RestController
@RequestMapping("sys/houseverificationcode")
public class HouseVerificationCodeController {
    @Autowired
    private HouseVerificationCodeService houseVerificationCodeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:houseverificationcode:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = houseVerificationCodeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:houseverificationcode:info")
    public R info(@PathVariable("id") String id){
			HouseVerificationCodeEntity houseVerificationCode = houseVerificationCodeService.selectById(id);

        return R.ok().put("houseVerificationCode", houseVerificationCode);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:houseverificationcode:save")
    public R save(@RequestBody HouseVerificationCodeEntity houseVerificationCode){
			houseVerificationCodeService.insert(houseVerificationCode);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:houseverificationcode:update")
    public R update(@RequestBody HouseVerificationCodeEntity houseVerificationCode){
			houseVerificationCodeService.updateById(houseVerificationCode);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:houseverificationcode:delete")
    public R delete(@RequestBody String[] ids){
			houseVerificationCodeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
