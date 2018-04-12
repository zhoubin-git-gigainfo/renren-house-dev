package io.renren.modules.house.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 合体业务主表
 * 
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-08 14:56:01
 */
@TableName("tat_contract")
public class TatContractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.INPUT)
	private String cId;
	/**
	 * 合同号
	 */
	private String contractNo;
	/**
	 * 签约日期
	 */
	private Date xydate;
	/**
	 * 创建日期
	 */
	private Date createdate;
	/**
	 * 合同模板
	 */
	private String reporid;
	/**
	 * 核验码
	 */
	private String code;
	/**
	 * 业务号
	 */
	private String businessNo;
	/**
	 * 标题
	 */
	private String title;

	/**
	 * 合同信息
	 */
	private String content;

	/**
	 * 创建人
	 */
	private String createNsername;
	private String createIdCard;

	public void setCId(String cId) {
		this.cId = cId;
	}

	public String getCId() {
		return cId;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setXydate(Date xydate) {
		this.xydate = xydate;
	}

	public Date getXydate() {
		return xydate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setReporid(String reporid) {
		this.reporid = reporid;
	}

	public String getReporid() {
		return reporid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreateNsername() {
		return createNsername;
	}

	public void setCreateNsername(String createNsername) {
		this.createNsername = createNsername;
	}

	public String getCreateIdCard() {
		return createIdCard;
	}

	public void setCreateIdCard(String createIdCard) {
		this.createIdCard = createIdCard;
	}
}
