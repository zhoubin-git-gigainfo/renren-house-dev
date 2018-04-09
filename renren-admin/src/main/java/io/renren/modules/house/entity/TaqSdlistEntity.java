package io.renren.modules.house.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * 业务客体-合同
 * 
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-04-08 14:56:01
 */
@TableName("taq_sdlist")
public class TaqSdlistEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.INPUT)
	private String id;
	/**
	 * 客体id
	 */
	private String hid;
	/**
	 * 坐落
	 */
	private String lname;
	/**
	 * 房号
	 */
	private String hdesc;
	/**
	 * 面积
	 */
	private Integer barea;
	/**
	 * 房屋用途
	 */
	private String huse;
	/**
	 * 建筑结构
	 */
	private String bstru;
	/**
	 * 建成年代
	 */
	private String dfate;
	/**
	 * 合同主键
	 */
	private String cId;


	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public String getHid() {
		return hid;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getLname() {
		return lname;
	}

	public void setHdesc(String hdesc) {
		this.hdesc = hdesc;
	}

	public String getHdesc() {
		return hdesc;
	}

	public void setBarea(Integer barea) {
		this.barea = barea;
	}

	public Integer getBarea() {
		return barea;
	}

	public void setHuse(String huse) {
		this.huse = huse;
	}

	public String getHuse() {
		return huse;
	}

	public void setBstru(String bstru) {
		this.bstru = bstru;
	}

	public String getBstru() {
		return bstru;
	}

	public void setDfate(String dfate) {
		this.dfate = dfate;
	}

	public String getDfate() {
		return dfate;
	}

	public void setCId(String cId) {
		this.cId = cId;
	}

	public String getCId() {
		return cId;
	}
}
