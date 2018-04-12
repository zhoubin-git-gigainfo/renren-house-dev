package io.renren.modules.house.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 接口日志表
 * 
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-12 15:26:34
 */
@TableName("to_log")
public class ToLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 操作人
	 */
	private String username;
	/**
	 * 用户操作
	 */
	private String operation;
	/**
	 * 方法名
	 */
	private String method;
	/**
	 * 参数
	 */
	private String params;
	/**
	 * IP地址
	 */
	private String ip;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 返回结果
	 */
	private String returnResult;


	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMethod() {
		return method;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getParams() {
		return params;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setReturnResult(String returnResult) {
		this.returnResult = returnResult;
	}

	public String getReturnResult() {
		return returnResult;
	}
}
