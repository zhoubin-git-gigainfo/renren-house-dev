package io.renren.modules.house.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysql.jdbc.Blob;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同文本
 * 
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-08 14:56:01
 */
@TableName("tu_report")
public class TuReportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer rId;
	/**
	 * 文件名称
	 */
	private String rName;
	/**
	 * 合同文件
	 */
	private Blob rFile;
	/**
	 * 创建时间
	 */
	private Date createdate;
	/**
	 * 合同id
	 */
	private Integer cId;


	public void setRId(Integer rId) {
		this.rId = rId;
	}

	public Integer getRId() {
		return rId;
	}

	public void setRName(String rName) {
		this.rName = rName;
	}

	public String getRName() {
		return rName;
	}

	public void setRFile(Blob rFile) {
		this.rFile = rFile;
	}

	public Blob getRFile() {
		return rFile;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCId(Integer cId) {
		this.cId = cId;
	}

	public Integer getCId() {
		return cId;
	}
}
