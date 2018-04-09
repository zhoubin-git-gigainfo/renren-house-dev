package io.renren.modules.house.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
    @TableId(type = IdType.INPUT)
    private String rId;
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
    private String cId;


    public void setRId(String rId) {
        this.rId = rId;
    }

    public String getRId() {
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

    public void setCId(String cId) {
        this.cId = cId;
    }

    public String getCId() {
        return cId;
    }
}
