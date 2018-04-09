package io.renren.modules.house.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * 主体表
 * 
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-08 14:56:01
 */
@TableName("tm_body")
public class TmBodyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.INPUT)
	private String mId;
	/**
	 * 证件号
	 */
	private String icNo;
	/**
	 * 证件类型
	 */
	private String icType;
	/**
	 * 电话
	 */
	private String mdTel;
	/**
	 * 地址
	 */
	private String mdAddr;
	/**
	 * 发证机关
	 */
	private String icOrg;
	/**
	 * 类型
	 */
	private String mdType;
	/**
	 * 所属区域
	 */
	private String mdAname;
	/**
	 * 业务主体类型
	 */
	private String maintypeid;
	/**
	 * 占用份额
	 */
	private String percant;
	/**
	 * 业务主体id
	 */
	private String cId;


	public void setMId(String mId) {
		this.mId = mId;
	}

	public String getMId() {
		return mId;
	}

	public void setIcNo(String icNo) {
		this.icNo = icNo;
	}

	public String getIcNo() {
		return icNo;
	}

	public void setIcType(String icType) {
		this.icType = icType;
	}

	public String getIcType() {
		return icType;
	}

	public void setMdTel(String mdTel) {
		this.mdTel = mdTel;
	}

	public String getMdTel() {
		return mdTel;
	}

	public void setMdAddr(String mdAddr) {
		this.mdAddr = mdAddr;
	}

	public String getMdAddr() {
		return mdAddr;
	}

	public void setIcOrg(String icOrg) {
		this.icOrg = icOrg;
	}

	public String getIcOrg() {
		return icOrg;
	}

	public void setMdType(String mdType) {
		this.mdType = mdType;
	}

	public String getMdType() {
		return mdType;
	}

	public void setMdAname(String mdAname) {
		this.mdAname = mdAname;
	}

	public String getMdAname() {
		return mdAname;
	}

	public void setMaintypeid(String maintypeid) {
		this.maintypeid = maintypeid;
	}

	public String getMaintypeid() {
		return maintypeid;
	}

	public void setPercant(String percant) {
		this.percant = percant;
	}

	public String getPercant() {
		return percant;
	}

	public void setCId(String cId) {
		this.cId = cId;
	}

	public String getCId() {
		return cId;
	}
}
