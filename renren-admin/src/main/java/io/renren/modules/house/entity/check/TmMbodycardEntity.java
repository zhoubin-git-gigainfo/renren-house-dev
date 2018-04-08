package io.renren.modules.house.entity.check;


import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * 主体
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date -- ::
 */
@TableName("tm_mbodycard")

public class TmMbodycardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String md_id;
    private String ic_type;
    private String ic_name;
    private String ic_no;
    private String md_name;
    private String md_tel;
    private String md_addr;

    public String getMd_id() {
        return md_id;
    }

    public void setMd_id(String md_id) {
        this.md_id = md_id;
    }

    public String getIc_type() {
        return ic_type;
    }

    public void setIc_type(String ic_type) {
        this.ic_type = ic_type;
    }

    public String getIc_name() {
        return ic_name;
    }

    public void setIc_name(String ic_name) {
        this.ic_name = ic_name;
    }

    public String getIc_no() {
        return ic_no;
    }

    public void setIc_no(String ic_no) {
        this.ic_no = ic_no;
    }

    public String getMd_name() {
        return md_name;
    }

    public void setMd_name(String md_name) {
        this.md_name = md_name;
    }

    public String getMd_tel() {
        return md_tel;
    }

    public void setMd_tel(String md_tel) {
        this.md_tel = md_tel;
    }

    public String getMd_addr() {
        return md_addr;
    }

    public void setMd_addr(String md_addr) {
        this.md_addr = md_addr;
    }
}
