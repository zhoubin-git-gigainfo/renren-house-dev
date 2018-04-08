package io.renren.modules.house.entity.check;

import java.io.Serializable;

/**
 * 房源核验实体封装类
 */
public class HouseCheckEntity implements Serializable {

    private Integer resultCode;
    private String resultInfo;
    private HouseMiddleware data;

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public HouseMiddleware getData() {
        return data;
    }

    public void setData(HouseMiddleware data) {
        this.data = data;
    }
}
