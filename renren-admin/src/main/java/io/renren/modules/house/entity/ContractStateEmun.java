package io.renren.modules.house.entity;

import java.util.Date;
import java.util.UUID;

public enum ContractStateEmun {

    CONTRACT_ON_LINE("合同再签", 1),
    CONTRACT_GENERATE("已生成合同", 2),

    CONTRACT_CONFIRM("合同确认", 3),
    CONTRACT_PRINT("打印", 4),
    CONTRACT_COMPLETE("完成", 5);

    private String name;
    private Integer code;

    ContractStateEmun(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    //依据此枚举类 生成对应的指令Entity--
    /**
     * @param cid        合同id
     * @param businessNo 业务号
     * @param idCard     确认人身份证
     * @return
     */
    public ToStateEntity generateOrder(String cid, String businessNo,String idCard) {
        ToStateEntity toStateEntity = new ToStateEntity();
        toStateEntity.setId(UUID.randomUUID().toString());
        toStateEntity.setSid(cid);
        toStateEntity.setBid(businessNo);
        toStateEntity.setDroits(idCard);
        toStateEntity.setModality(1);
        toStateEntity.setStype(this.getCode() + "");
        toStateEntity.setVDate(new Date());
        return toStateEntity;
    }
}
