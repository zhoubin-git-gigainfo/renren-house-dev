package io.renren.modules.house.entity.check;

import java.io.Serializable;

/**
 * 客体信息
 */
public class HouseEntity implements Serializable {

    /**
     * 房屋id
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
    private String barea;
    /**
     * 房屋用途
     */
    private String huse;
    /**
     *
     */
    private String limit_state;
    private String warn_state;
    /**
     * 0未通过 1通过
     */
    private Integer pass_tag;

    /**
     * 未通过信息
     */
    private String pass_info;
    /**
     * 建成年代
     */
    private String bstru;
    /**
     * 建成年代
     */
    private String bfete;

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

    public String getHuse() {
        return huse;
    }

    public void setHuse(String huse) {
        this.huse = huse;
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

    public String getPass_info() {
        return pass_info;
    }

    public void setPass_info(String pass_info) {
        this.pass_info = pass_info;
    }

    public String getBstru() {
        return bstru;
    }

    public void setBstru(String bstru) {
        this.bstru = bstru;
    }

    public String getBfete() {
        return bfete;
    }

    public void setBfete(String bfete) {
        this.bfete = bfete;
    }
}
