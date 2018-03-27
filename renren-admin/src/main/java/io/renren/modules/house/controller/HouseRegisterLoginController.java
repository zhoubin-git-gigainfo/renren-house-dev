package io.renren.modules.house.controller;

import io.renren.common.utils.R;
import io.renren.common.utils.SendMsgUtil;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.house.entity.UserEntity;
import io.renren.modules.house.form.HouseLoginForm;
import io.renren.modules.house.form.HouseRegisterForm;
import io.renren.modules.house.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * APP登录授权
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/app/house")
@Api(tags = "登录接口")
public class HouseRegisterLoginController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    @ApiOperation("登录")
    public R login(@RequestBody HouseLoginForm form) {
        //表单校验
        ValidatorUtils.validateEntity(form);

        //用户登录
        Map<String, Object> map = userService.login(form);

        return R.ok(map);
    }


    @PostMapping("register")
    @ApiOperation("注册")
    public R register(HouseRegisterForm form) {
        //表单校验
        ValidatorUtils.validateEntity(form);

//        验证手机验证码
        UserEntity user = new UserEntity();
        if (null != userService.queryByMobile(form.getMobile())) {
            return R.error().put("message", "手机号已注册");
        }

//        与内网验证

        user.setIdCard(form.getIdCard());
        user.setMobile(form.getMobile());
        user.setUsername(form.getUsername());
        user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        user.setCreateTime(new Date());
        userService.insert(user);

        return R.ok();
    }

    @PostMapping("message")
    @ApiOperation("验证码")
    public R message(String mobile) {
        String code = SendMsgUtil.createRandomVcode();
//        SendMsgUtil.sendMsg(phone, "【签名】尊敬的用户，您的验证码为" + code + "，请在10分钟内输入。请勿告诉其他人!");
        return R.ok().put("code", code);
    }


}
