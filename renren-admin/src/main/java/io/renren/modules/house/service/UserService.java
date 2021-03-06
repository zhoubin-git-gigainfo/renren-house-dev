package io.renren.modules.house.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.house.entity.UserEntity;
import io.renren.modules.house.form.HouseLoginForm;

import java.util.Map;

/**
 * 用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:06
 */
public interface UserService extends IService<UserEntity> {

	UserEntity queryByMobile(String mobile);

	/**
	 * 用户登录
	 * @param form    登录表单
	 * @return        返回登录信息
	 */
	Map<String, Object> login(HouseLoginForm form);

	/**
	 *
	 * @param idCard
	 * @return
	 */
    Object queryByIdCard(String idCard);
}
