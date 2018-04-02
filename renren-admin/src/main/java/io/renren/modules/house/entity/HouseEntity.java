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
    private String BAREA;
    private List<Map> states;

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

    public String getBAREA() {
        return BAREA;
    }

    public void setBAREA(String BAREA) {
        this.BAREA = BAREA;
    }

    public List<Map> getStates() {
        return states;
    }

    public void setStates(List<Map> states) {
        this.states = states;
    }
}
