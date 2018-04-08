package io.renren.modules.house.entity.check;

import io.swagger.models.auth.In;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 客体信息
 */
public class HouseEntity implements Serializable {

    private String hid;
    private String lname;
    private String hdesc;
    private String barea;
    private String field;
    private String limit_state;
    private String warn_state;
    /**
     * 0未通过 1通过
     */
    private Integer pass_tag;

    /**
     * 关联核验码--界面展示
     */
    private String code;

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getHdesc() {
        return hdesc;
    }

    public void setHdesc(String hdesc) {
        this.hdesc = hdesc;
    }

    public String getBarea() {
        return barea;
    }

    public void setBarea(String barea) {
        this.barea = barea;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getLimit_state() {
        return limit_state;
    }

    public void setLimit_state(String limit_state) {
        this.limit_state = limit_state;
    }

    public String getWarn_state() {
        return warn_state;
    }

    public void setWarn_state(String warn_state) {
        this.warn_state = warn_state;
    }

    public Integer getPass_tag() {
        return pass_tag;
    }

    public void setPass_tag(Integer pass_tag) {
        this.pass_tag = pass_tag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
