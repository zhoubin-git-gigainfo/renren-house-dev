package io.renren.modules.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.pdf.PdfReader;
import io.renren.common.utils.HttpRequest;
import io.renren.common.utils.ITextUtil;
import io.renren.common.utils.R;
import io.renren.common.utils.RequestUrlConfig;
import io.renren.modules.house.entity.*;
import io.renren.modules.house.entity.check.HouseCheckEntity;
import io.renren.modules.house.entity.check.HouseEntity;
import io.renren.modules.house.entity.check.HouseMiddleware;
import io.renren.modules.house.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 前端合同controller
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("api/tatcontract")
public class ApiHouseContractController {

    @Autowired
    private TatContractService tatContractService;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private TsReportService tsReportService;

    @Autowired
    private TuReportService tuReportService;

    @Autowired
    private HouseVerificationCodeService houseVerificationCodeService;

    @Autowired
    private TmBodyService tmBodyService;

    @Autowired
    private TaqSdlistService taqSdlistService;

    /**
     * 保存
     * 1、生成合同
     * 2、主体
     * 3、客体
     * 4、写入状态表
     */
    @RequestMapping("/save")
    public R save(String code) {
        TatContractEntity tatContractEntity = new TatContractEntity();
        try {
            HouseVerificationCodeEntity codeEntity = houseVerificationCodeService.queryByCode(code);
            HouseCheckEntity houseCheckEntity = houseCheck(codeEntity.getHouseId());

            if (null == houseCheckEntity) {
                //TODO 房屋验证不通过
                return R.error();
            }
            tatContractEntity.setCId(UUID.randomUUID().toString());
            tatContractEntity.setCreatedate(new Date());
            String seq = sequenceService.queryNextvalSeq("code_validate").toString();
            while (seq.length() < 9) {
                seq = "0" + seq;
            }
            tatContractEntity.setBusinessNo("B" + seq);
            tatContractService.insert(tatContractEntity);

            houseCheckEntity.getData().getBodys().stream().forEach(tmMbodycardEntity -> {
                TmBodyEntity tmBodyEntity = new TmBodyEntity();
                tmBodyEntity.setMId(UUID.randomUUID().toString());
                tmBodyEntity.setCId(tatContractEntity.getCId());
                tmBodyEntity.setIcNo(tmMbodycardEntity.getIc_no());
                tmBodyEntity.setIcType(tmMbodycardEntity.getIc_type());
                tmBodyEntity.setMdAddr(tmMbodycardEntity.getMd_addr());
                tmBodyEntity.setMdAname(tmMbodycardEntity.getMd_name());
                tmBodyEntity.setMdTel(tmMbodycardEntity.getMd_tel());
                tmBodyEntity.setType("2");
//                tmBodyEntity.setIsConfirm();
//                tmBodyEntity.setIcOrg();
//                tmBodyEntity.setMaintypeid();
//                tmBodyEntity.setMdType();
//                tmBodyEntity.setPercant();
                tmBodyService.insert(tmBodyEntity);
            });
            HouseEntity houseEntity = houseCheckEntity.getData().getHouses().get(0);
            TaqSdlistEntity taqSdlistEntity = new TaqSdlistEntity();
            taqSdlistEntity.setId(UUID.randomUUID().toString());
            taqSdlistEntity.setCId(tatContractEntity.getCId());
            taqSdlistEntity.setBarea(houseEntity.getBarea());
            taqSdlistEntity.setBstru(houseEntity.getBstru());
            taqSdlistEntity.setDfate(houseEntity.getBfete());
            taqSdlistEntity.setHdesc(houseEntity.getHdesc());
            taqSdlistEntity.setHid(houseEntity.getHid());
            taqSdlistEntity.setLname(houseEntity.getLname());
            taqSdlistEntity.setHuse(houseEntity.getHuse());
            taqSdlistService.insert(taqSdlistEntity);
            //TODO 写入状态表
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok().put("contractId", tatContractEntity.getCId());
    }

    private HouseCheckEntity houseCheck(String hid) throws IOException {
        String json = HttpRequest.get(RequestUrlConfig.HOUSE_VERIFICATION_URL + "byhid?b_type=2&hid=" + hid);
        ObjectMapper objectMapper = new ObjectMapper();
        HouseCheckEntity houseCheckEntity = objectMapper.readValue(json, HouseCheckEntity.class);
        HouseMiddleware date = houseCheckEntity.getData();
        if (null != date.getBodys() && null != date.getHouses()
                && date.getHouses().size() != 1 && date.getHouses().get(0).getPass_tag() == 1) {
            return houseCheckEntity;
        }
        return null;
    }

    /**
     * 合同确定
     * 1、生成合同号
     * 2、生成pdf
     */
    @RequestMapping("/contractDetermine")
    public R contractDetermine(String rId) {
        //TODO 双方

        try {
            //TODO  业务号
            TatContractEntity tatContractEntity = tatContractService.selectById(rId);
            String seq = sequenceService.queryNextvalSeq("code_validate").toString();
            while (seq.length() < 9) {
                seq = "0" + seq;
            }
            TatContractEntity entity = new TatContractEntity();
            entity.setCId(tatContractEntity.getCId());
            entity.setContractNo("C" + seq);
            tatContractService.updateById(entity);

            /**
             * 生成合同
             */
            ObjectMapper objectMapper = new ObjectMapper();
            //表单数据
            Map formMap = objectMapper.readValue(tatContractEntity.getContent(), HashMap.class);
            Map barCodeMap = new HashMap();
            //条形码数据
            barCodeMap.put("businessNo", tatContractEntity.getBusinessNo());
            barCodeMap.put("contractNo", tatContractEntity.getContractNo());

//        map.put("sellerName", "甲方姓名");
//        map.put("sellerIc", "甲方身份证");
//        map.put("sellerAgentName", "甲方代理人");
//        map.put("sellerAgentIc", "代理人身份证");
//        map.put("buyerName", "乙方姓名");
//        map.put("buyerIc", "乙方身份证");
//        map.put("buyerAgentName", "代理人姓名");
//        map.put("buyerAgentIc", "代理人Ic");
//        map.put("lname", "坐落");
//        map.put("hdesc", "房号");
//        map.put("cdno", "产权证号");
//        map.put("barea", "面积");
//        map.put("huse", "用途");
//        map.put("price", "价格");
//        map.put("priceC", "价格大写");
//        map.put("payMethod", "付款方式");
//        map.put("payCustom", "其他付款方式");
//        map.put("taxation", "税费方式");
//        map.put("taxationCustom", "其他方式");
//        map.put("term", "期限");
//        map.put("responsibility", "违约责任");
//        map.put("dispute", "争议解决方式");
//        map.put("matter", "其他约定");
            convertTransData(formMap, barCodeMap, tatContractEntity);
            System.out.println("执行完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok();
    }

    /**
     * 将数据转换为输入字节流
     *
     * @param form              表单数据
     * @param barCode           条形数据
     * @param tatContractEntity 合同主体信息
     * @return
     * @throws Exception
     */
    private InputStream convertTransData(Map form, Map barCode, TatContractEntity tatContractEntity) throws Exception {
        if (form == null || form.isEmpty())
            return null;
        try {
            //TODO  更改查询方式
            TsReportEntity t = tsReportService.selectById("45708f9f-0a10-4d4d-8123-4f12ce37976f");
            InputStream in = new ByteArrayInputStream(t.getFile());

            ByteArrayOutputStream out = (ByteArrayOutputStream) ITextUtil.generate(new PdfReader(in), form, barCode);
            ByteArrayInputStream ret = new ByteArrayInputStream(out.toByteArray());

            TuReportEntity tuReportEntity = new TuReportEntity();
            tuReportEntity.setrId(UUID.randomUUID().toString());
            tuReportEntity.setCreatedate(new Date());
            tuReportEntity.setrFile(out.toByteArray());
            //TODO  买卖方姓名--合同号--株洲市--
            tuReportEntity.setrName("株洲市" + tatContractEntity.getContractNo() + tatContractEntity.getTitle());
            tuReportEntity.setcId(tatContractEntity.getCId());
            tuReportService.insertFile(tuReportEntity);

            out.close();
            return ret;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @RequestMapping("/checkBody")
    public R checkBody(String idCard, String username) {
        Map map = new HashMap();
        Map mapIDCard = new HashMap();
        mapIDCard.put("sfzh", idCard);
        map.put("data", mapIDCard);
        map.put("business", "querySsSyrkInfoBySfzh");
        map.put("sjly", "1");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = HttpRequest.get(RequestUrlConfig.HOUSE_HOLD_REGISTER_URL_HN + "?requestStr=" + objectMapper.writeValueAsString(map));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HouseholdRegisterEntity entity = null;
        try {
            entity = objectMapper.readValue(json, HouseholdRegisterEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == entity.getData() || entity.getData().size() == 0) {
            return R.error().put("msg", "身份证号码不存在"); //身份查询不到用户信息
        }
        if (!username.equals(entity.getData().get(0).getXm()) || !idCard.equals(entity.getData().get(0).getSfzh())) {
            return R.error().put("msg", "姓名与身份证号码不匹配"); //用户信息不匹配
        }
        return R.ok().put("lname", entity.getData().get(0).getXzzxz());
    }
}
