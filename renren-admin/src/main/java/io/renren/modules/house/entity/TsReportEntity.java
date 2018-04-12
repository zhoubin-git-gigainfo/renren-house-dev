package io.renren.modules.house.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 文本字典
 * 
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-09 11:00:21
 */
@TableName("ts_report")
public class TsReportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private byte[]	 file;
	/**
	 * 
	 */
	private Date createdate;


	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public byte[]	 getFile() {
		return file;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getCreatedate() {
		return createdate;
	}
}
