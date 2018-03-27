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
@TableName("ts_code")
public class TsCodeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String ckey;
    private String name;
    private String parentid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCkey() {
        return ckey;
    }

    public void setCkey(String ckey) {
        this.ckey = ckey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
}
