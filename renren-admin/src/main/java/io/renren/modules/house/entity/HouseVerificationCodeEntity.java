package io.renren.modules.house.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 房屋核验码
 * 
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-03 11:03:59
 */
@TableName("tb_house_verification_code")
public class HouseVerificationCodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.INPUT)
	private String id;
	/**
	 * 核验码
	 */
	private String code;
	/**
	 * 房屋id
	 */
	private String houseId;
	/**
	 * 身份证号
	 */
	private String icno;
	/**
	 * 产权证号
	 */
	private String cdno;
	/**
	 * 创建时间
	 */
	private Date vDate;
	/**
	 * 失效时间
	 */
	private Date fDate;
	/**
	 * 状态（1有效，失效）
	 */
	private Integer state;


	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setIcno(String icno) {
		this.icno = icno;
	}

	public String getIcno() {
		return icno;
	}

	public void setCdno(String cdno) {
		this.cdno = cdno;
	}

	public String getCdno() {
		return cdno;
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

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getState() {
		return state;
	}
}
