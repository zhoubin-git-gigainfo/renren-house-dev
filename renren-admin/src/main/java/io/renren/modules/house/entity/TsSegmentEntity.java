package io.renren.modules.house.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;


/**
 * ${comments}
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-10-18 17:10:47
 */
@TableName("ts_segment")
public class TsSegmentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sgType;
    private String sgName;
    private String sgMinval;
    private String sgMaxval;
    private String sgCode;
    private String mseq;
    private String xzId;

    public String getSgType() {
        return sgType;
    }

    public void setSgType(String sgType) {
        this.sgType = sgType;
    }

    public String getSgName() {
        return sgName;
    }

    public void setSgName(String sgName) {
        this.sgName = sgName;
    }

    public String getSgMinval() {
        return sgMinval;
    }

    public void setSgMinval(String sgMinval) {
        this.sgMinval = sgMinval;
    }

    public String getSgMaxval() {
        return sgMaxval;
    }

    public void setSgMaxval(String sgMaxval) {
        this.sgMaxval = sgMaxval;
    }

    public String getSgCode() {
        return sgCode;
    }

    public void setSgCode(String sgCode) {
        this.sgCode = sgCode;
    }

    public String getMseq() {
        return mseq;
    }

    public void setMseq(String mseq) {
        this.mseq = mseq;
    }

    public String getXzId() {
        return xzId;
    }

    public void setXzId(String xzId) {
        this.xzId = xzId;
    }
}
