package io.renren.modules.house.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 序列表
 * 
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-03 15:22:37
 */
@TableName("tb_sequence")
public class SequenceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序列名称
	 */
	@TableId
	private String seqName;
	/**
	 * 当前值
	 */
	private Integer currentVal;
	/**
	 * 每次步进
	 */
	private Integer incrementVal;


	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public String getSeqName() {
		return seqName;
	}

	public void setCurrentVal(Integer currentVal) {
		this.currentVal = currentVal;
	}

	public Integer getCurrentVal() {
		return currentVal;
	}

	public void setIncrementVal(Integer incrementVal) {
		this.incrementVal = incrementVal;
	}

	public Integer getIncrementVal() {
		return incrementVal;
	}
}
