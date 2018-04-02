package io.renren.modules.house.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 户籍验证接口实体封装
 */
public class HouseholdRegisterEntity implements Serializable {
    private List<HouseEntity> data;
    private String resultCode;
    private String resultInfo;

    public List<HouseEntity> getData() {
        return data;
    }

    public void setData(List<HouseEntity> data) {
        this.data = data;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }
}
