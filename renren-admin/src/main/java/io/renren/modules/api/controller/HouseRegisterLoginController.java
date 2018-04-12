package io.renren.modules.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.renren.common.utils.HttpRequest;
import io.renren.common.utils.R;
import io.renren.common.utils.RequestUrlConfig;
import io.renren.common.utils.SendMsgUtil;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.house.entity.check.HouseholdRegisterEntity;
import io.renren.modules.house.entity.UserEntity;
import io.renren.modules.house.form.HouseLoginForm;
import io.renren.modules.house.form.HouseRegisterForm;
import io.renren.modules.house.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * APP登录授权
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/api/house")
@Api(tags = "登录接口")
public class HouseRegisterLoginController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    @ApiOperation("登录")
    public R login( HouseLoginForm form) {
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
            return R.error().put("msg", "手机号已注册");
        }

        if (null != userService.queryByIdCard(form.getIdCard())) {
            return R.error().put("msg", "身份证已注册");
        }
//        与户籍接口验证
        Map map = new HashMap();
        Map mapIDCard = new HashMap();
        if (!form.getIdCard().startsWith("43")) {
            mapIDCard.put("username", "031507");
        }
        mapIDCard.put("sfzh", form.getIdCard());
        map.put("business", "querySsSyrkInfoBySfzh");
        map.put("sjly", "1");
        map.put("data", mapIDCard);
        ObjectMapper objectMapper = new ObjectMapper();

        String json = null;
        try {
            json = HttpRequest.get(RequestUrlConfig.HOUSE_HOLD_REGISTER_URL_HN + "?requestStr=" + objectMapper.writeValueAsString(map));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HouseholdRegisterEntity entity = null;
        try {
            entity = objectMapper.readValue(json, HouseholdRegisterEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == entity.getData() || entity.getData().size() == 0) {
            return R.error().put("msg", "身份证号码不存在"); //身份查询不到用户信息
        }
        if (!form.getUsername().equals(entity.getData().get(0).getXm()) || !form.getIdCard().equals(entity.getData().get(0).getSfzh())) {
            return R.error().put("msg", "姓名与身份证号码不匹配"); //用户信息不匹配
        }

        user.setIdCard(form.getIdCard());
        user.setMobile(form.getMobile());
        user.setUsername(form.getUsername());
        user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        user.setCreateTime(new Date());
        userService.insert(user);

        return R.ok();
    }

    @PostMapping("updatePassword")
    @ApiOperation("修改密码")
    public R updatePassword(String mobile,String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setMobile(mobile);
        userEntity.setPassword(DigestUtils.sha256Hex(password));
        userService.update(userEntity, new EntityWrapper<UserEntity>().eq("mobile", mobile));
        return R.ok().put("code", 0).put("user", userService.queryByMobile(mobile));
    }


    @PostMapping("message")
    @ApiOperation("验证码")
    public R message(String mobile) {
        String code = SendMsgUtil.createRandomVcode();
//        SendMsgUtil.sendMsg(phone, "【签名】尊敬的用户，您的验证码为" + code + "，请在10分钟内输入。请勿告诉其他人!");
        return R.ok().put("code", code);
    }


}
