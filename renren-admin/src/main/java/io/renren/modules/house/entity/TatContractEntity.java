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
	 * 业务状态
	 */
	private Integer state;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 价格
	 */
	private Integer price;
	/**
	 * 付款方式
	 */
	private Integer payment;
	/**
	 * 付款方式信息
	 */
	private String paymentMessage;
	/**
	 * 税费等承担原则
	 */
	private Integer taxationEar;
	/**
	 * 税费等承担原则信息
	 */
	private String taxationEarMessage;
	/**
	 * 交付期限
	 */
	private Integer limitPay;
	/**
	 * 违约责任
	 */
	private String breachInfo;
	/**
	 * 争议解决方式
	 */
	private Integer disputeResolution;
	/**
	 * 其他约定事项
	 */
	private String otherAssumpsit;
	/**
	 * 创建时间
	 */
	private Date createDate;


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

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getState() {
		return state;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPaymentMessage(String paymentMessage) {
		this.paymentMessage = paymentMessage;
	}

	public String getPaymentMessage() {
		return paymentMessage;
	}

	public void setTaxationEar(Integer taxationEar) {
		this.taxationEar = taxationEar;
	}

	public Integer getTaxationEar() {
		return taxationEar;
	}

	public void setTaxationEarMessage(String taxationEarMessage) {
		this.taxationEarMessage = taxationEarMessage;
	}

	public String getTaxationEarMessage() {
		return taxationEarMessage;
	}

	public void setLimitPay(Integer limitPay) {
		this.limitPay = limitPay;
	}

	public Integer getLimitPay() {
		return limitPay;
	}

	public void setBreachInfo(String breachInfo) {
		this.breachInfo = breachInfo;
	}

	public String getBreachInfo() {
		return breachInfo;
	}

	public void setDisputeResolution(Integer disputeResolution) {
		this.disputeResolution = disputeResolution;
	}

	public Integer getDisputeResolution() {
		return disputeResolution;
	}

	public void setOtherAssumpsit(String otherAssumpsit) {
		this.otherAssumpsit = otherAssumpsit;
	}

	public String getOtherAssumpsit() {
		return otherAssumpsit;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return createDate;
	}
}
