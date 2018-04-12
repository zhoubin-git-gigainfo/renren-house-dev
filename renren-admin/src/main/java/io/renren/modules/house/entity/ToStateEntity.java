package io.renren.modules.house.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 状态表
 * 
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-11 16:06:28
 */
@TableName("to_state")
public class ToStateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.INPUT)
	private String id;
	/**
	 * 合体id
	 */
	private String sid;
	/**
	 * 业务号
	 */
	private String bid;
	/**
	 * 生效时间
	 */
	private Date vDate;
	/**
	 * 实现时间
	 */
	private Date fDate;
	/**
	 * 状态1有效 0失效
	 */
	private Integer modality;
	/**
	 * 权力类别
	 */
	private String stype;
	/**
	 * 权力人
	 */
	private String droits;


	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSid() {
		return sid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBid() {
		return bid;
	}

	public void setVDate(Date vDate) {
		this.vDate = vDate;
	}

	public Date getVDate() {
		return vDate;
	}

	public void setFDate(Date fDate) {
		this.fDate = fDate;
	}

	public Date getFDate() {
		return fDate;
	}

	public void setModality(Integer modality) {
		this.modality = modality;
	}

	public Integer getModality() {
		return modality;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getStype() {
		return stype;
	}

	public void setDroits(String droits) {
		this.droits = droits;
	}

	public String getDroits() {
		return droits;
	}
}
