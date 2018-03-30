package io.renren.entity;


import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;


/**
 * 中介公司
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date -- ::
 */
@TableName("tm_mbodycard")

public class TmMbodycardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mdId;
    private String icType;
    private String icNo;
    private String mdName;
    private String icName;

    public String getMdId() {
        return mdId;
    }

    public void setMdId(String mdId) {
        this.mdId = mdId;
    }

    public String getIcType() {
        return icType;
    }

    public void setIcType(String icType) {
        this.icType = icType;
    }

    public String getIcNo() {
        return icNo;
    }

    public void setIcNo(String icNo) {
        this.icNo = icNo;
    }

    public String getMdName() {
        return mdName;
    }

    public void setMdName(String mdName) {
        this.mdName = mdName;
    }

    public String getIcName() {
        return icName;
    }

    public void setIcName(String icName) {
        this.icName = icName;
    }
}
