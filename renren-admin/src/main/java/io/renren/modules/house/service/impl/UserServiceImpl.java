package io.renren.modules.house.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.validator.Assert;
import io.renren.modules.house.dao.UserDao;
import io.renren.modules.house.entity.TokenEntity;
import io.renren.modules.house.entity.UserEntity;
import io.renren.modules.house.form.HouseLoginForm;
import io.renren.modules.house.service.TokenService;
import io.renren.modules.house.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private TokenService tokenService;

    @Override
    public UserEntity queryByMobile(String mobile) {
        UserEntity userEntity = new UserEntity();
        userEntity.setMobile(mobile);
        return baseMapper.selectOne(userEntity);
    }

    @Override
    public Map<String, Object> login(HouseLoginForm form) {
        Map<String, Object> map = new HashMap<>(2);
        UserEntity user = queryByMobile(form.getMobile());
        Assert.isNull(user, "手机号或密码错误");
        if(user==null){
            map.put("code",200);
            map.put("msg","该手机号还没注册");
            return map;
        }else {
            //密码错误
            if (!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))) {
                throw new RRException("手机号或密码错误");
            }

            //获取登录token
            TokenEntity tokenEntity = tokenService.createToken(user.getUserId());

            map.put("token", tokenEntity.getToken());
            map.put("expire", tokenEntity.getExpireTime().getTime() - System.currentTimeMillis());
            map.put("username",user.getUsername());

            return map;
        }
    }

    @Override
    public UserEntity queryByIdCard(String idCard) {
        UserEntity userEntity = new UserEntity();
        userEntity.setIdCard(idCard);
        return baseMapper.selectOne(userEntity);
    }
}
