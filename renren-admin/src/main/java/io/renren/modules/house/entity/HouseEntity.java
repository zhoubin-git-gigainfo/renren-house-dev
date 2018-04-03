package io.renren.modules.house.entity;

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
    private List<Map> states;

    /**
     * 关联核验码--界面展示
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public List<Map> getStates() {
        return states;
    }

    public void setStates(List<Map> states) {
        this.states = states;
    }
}
