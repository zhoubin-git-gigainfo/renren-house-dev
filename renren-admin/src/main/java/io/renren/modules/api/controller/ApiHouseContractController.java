package io.renren.modules.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.pdf.PdfReader;
import io.renren.common.utils.*;
import io.renren.modules.house.entity.*;
import io.renren.modules.house.entity.check.HouseCheckEntity;
import io.renren.modules.house.entity.check.HouseEntity;
import io.renren.modules.house.entity.check.HouseMiddleware;
import io.renren.modules.house.entity.check.HouseholdRegisterEntity;
import io.renren.modules.house.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

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

    @Autowired
    private ToStateService toStateService;


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
                return R.error();
            }
            //生成合同
            tatContractEntity.setCId(UUID.randomUUID().toString());
            tatContractEntity.setCreatedate(new Date());
            String seq = sequenceService.queryNextvalSeq("code_validate").toString();
            while (seq.length() < 9) {
                seq = "0" + seq;
            }
            tatContractEntity.setBusinessNo("B" + seq);
            tatContractService.insert(tatContractEntity);

            //主体
            houseCheckEntity.getData().getBodys().stream().forEach(tmMbodycardEntity -> {
                TmBodyEntity tmBodyEntity = new TmBodyEntity();
                tmBodyEntity.setMId(UUID.randomUUID().toString());
                tmBodyEntity.setCId(tatContractEntity.getCId());
                tmBodyEntity.setIcNo(tmMbodycardEntity.getIc_no());
                tmBodyEntity.setIcType(tmMbodycardEntity.getIc_type());
                tmBodyEntity.setMdAddr(tmMbodycardEntity.getMd_addr());
                tmBodyEntity.setMdAname(tmMbodycardEntity.getMd_name());
                tmBodyEntity.setMdTel(tmMbodycardEntity.getMd_tel());
                tmBodyEntity.setMdType(2);
//                tmBodyEntity.setIsConfirm();
//                tmBodyEntity.setIcOrg();
//                tmBodyEntity.setMaintypeid();
//                tmBodyEntity.setMdType();
//                tmBodyEntity.setPercant();
                tmBodyService.insert(tmBodyEntity);
            });
            //客体
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


            //写入状态表
            toStateService.insert(ContractStateEmun.CONTRACT_ON_LINE.generateOrder(tatContractEntity.getCId(), tatContractEntity.getBusinessNo(), null));
            return R.ok().put("contractId", tatContractEntity.getCId());
        } catch (IOException e) {
            e.printStackTrace();
            return R.error();
        }
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


    @RequestMapping("/checkBody")
    public R checkBody(String idCard, String username) {
        Map map = new HashMap();
        Map mapIDCard = new HashMap();
        if (!idCard.startsWith("43")) {
            mapIDCard.put("username", "031507");
        }
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
            return R.error(); //身份查询不到用户信息
        }
        if (!username.equals(entity.getData().get(0).getXm()) || !idCard.equals(entity.getData().get(0).getSfzh())) {
            return R.error(); //用户信息不匹配
        }
        return R.ok().put("lname", entity.getData().get(0).getXzzxz());
    }

    /**
     * 合同确定
     * 1、判断是否双方都确认过
     * 是：生成合同号、生成pdf、写入、修改（两个）状态表
     * 否：写入状态表
     */
    @RequestMapping("/contractDetermine")
    public R contractDetermine(String cid) {
        //TODO 双方
        //写入状态表
        try {
            TatContractEntity tatContractEntity = tatContractService.selectById(cid);
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
            //主体
            List<TmBodyEntity> tmBodyEntities = tmBodyService.selectListByCid(cid);
            tmBodyEntities.stream().forEach(tmBodyEntity -> {
                switch (tmBodyEntity.getMdType()) {
                    case 1:
                        formMap.put("buyerName", tmBodyEntity.getName());
                        formMap.put("buyerIc", tmBodyEntity.getIcNo());
                        break;
                    case 2:
                        formMap.put("sellerName", tmBodyEntity.getName());
                        formMap.put("sellerIc", tmBodyEntity.getIcNo());
                        break;
                    case 3:
                        formMap.put("buyerAgentName", tmBodyEntity.getName());
                        formMap.put("buyerAgentIc", tmBodyEntity.getIcNo());
                        break;
                    case 4:
                        formMap.put("sellerAgentName", tmBodyEntity.getName());
                        formMap.put("sellerAgentIc", tmBodyEntity.getIcNo());
                        break;
                }
            });
            //客体
            TaqSdlistEntity taqSdlistEntity = taqSdlistService.selectOneByCid(cid);
            formMap.put("lname", taqSdlistEntity.getLname());
            formMap.put("hdesc", taqSdlistEntity.getHdesc());
            formMap.put("barea", taqSdlistEntity.getBarea());
            formMap.put("huse", taqSdlistEntity.getHuse());

            //产权证号
            HouseVerificationCodeEntity codeEntity = houseVerificationCodeService.queryByCode(tatContractEntity.getCode());
            formMap.put("cdno", codeEntity.getCdno());

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
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
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


    /**
     * 插入主体信息
     *
     * @param tmBodyEntities
     * @return
     */
    @RequestMapping("/insertBody")
    public R insertBody(List<TmBodyEntity> tmBodyEntities) {
        tmBodyService.insertBatch(tmBodyEntities);
        return R.ok();
    }

    /**
     * 合同信息插入
     *
     * @param map
     * @return
     */
    @RequestMapping("/insertContract")
    public R insertContract(Map map) {
        //TODO 大写金额
        try {
            String state = map.get("state") + "";
            ObjectMapper objectMapper = new ObjectMapper();
            String contractId = map.get("cid") + "";
            map.remove("state");
            map.remove("cid");
            String price = map.get("price") + "";

            BigDecimal numberOfMoney = new BigDecimal(map.get("price") + "");
            map.put("priceC", NumberToCNUtils.number2CNMontrayUnit(numberOfMoney));

            TatContractEntity tatContractEntity = new TatContractEntity();
            tatContractEntity.setCId(contractId);
            tatContractEntity.setContent(objectMapper.writeValueAsString(map));
            tatContractService.updateById(tatContractEntity);

            /**
             * 合同信息录入完成
             *  修改状态表-写入新状态
             */
            tatContractEntity = tatContractService.selectById(contractId);
            if ("1".equals(state)) {
                ToStateEntity toStateEntity = new ToStateEntity();
                toStateEntity.setFDate(new Date());
                toStateEntity.setModality(0);
                toStateService.update(toStateEntity, new EntityWrapper<ToStateEntity>().eq("sid", contractId).eq("stype", ContractStateEmun.CONTRACT_ON_LINE.getCode()));

                toStateService.insert(ContractStateEmun.CONTRACT_GENERATE.generateOrder(contractId, tatContractEntity.getBusinessNo(), null));
            }
            return R.ok();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 合同下载
     *
     * @param cid      合同id
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/download")
    public Object offRechargeExportRecords(String cid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        TuReportEntity tuReportEntity = new TuReportEntity();
        tuReportEntity.setcId(cid);

        tuReportEntity = tuReportService.selectByEntity(tuReportEntity);
        response.setContentType("application/x-msdownload");
        String fileName = "文件下载";
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        OutputStream out = response.getOutputStream();
        InputStream in = new ByteArrayInputStream(tuReportEntity.getrFile());
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        in.close();
        return null;
    }

    /**
     * 图片上传
     *
     * @param request
     * @param cid     合同id
     * @return
     * @throws IOException
     */
    @RequestMapping("/uploadFile")
    public Map<String, Object> uploadFile(HttpServletRequest request, String cid) throws IOException {
        try {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    /**获取文件的后缀**/
                    String suffix = file.getOriginalFilename().substring
                            (file.getOriginalFilename().lastIndexOf("."));
                    //如果名称不为"",说明该文件存在，否则说明该文件不存在
                    if (!StringUtils.isEmpty(myFileName.trim())) {
                        // 上传文件流
                        InputStream inputStream = file.getInputStream();
                        TuReportEntity tuReportEntity = new TuReportEntity();
                        tuReportEntity.setPic(toByteArray(inputStream));
                        tuReportService.update(tuReportEntity, new EntityWrapper<TuReportEntity>().eq("cId", cid));
                    }
                }
            }
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 文件流--转byte数组
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    /**
     * 合同完成
     * 1、修改-写入状态表
     * 2、修改核验码状态
     *
     * @param cid
     * @return
     */
    @RequestMapping("/contractFinish")
    public R contractFinish(String cid) {
        try {
            /**
             * 失效核验码
             */
            TatContractEntity tatContractEntity = tatContractService.selectById(cid);
            HouseVerificationCodeEntity codeEntity = new HouseVerificationCodeEntity();
            codeEntity.setState(0);
            houseVerificationCodeService.update(codeEntity, new EntityWrapper<HouseVerificationCodeEntity>().eq("code", tatContractEntity.getCode()));

            /**
             * 修改状态表
             */
            ToStateEntity toStateEntity = new ToStateEntity();
            toStateEntity.setFDate(new Date());
            toStateEntity.setModality(0);
            toStateService.update(toStateEntity, new EntityWrapper<ToStateEntity>().eq("sid", cid).eq("stype", ContractStateEmun.CONTRACT_PRINT.getCode()));
            /**
             * 写入状态表
             */
            toStateService.insert(ContractStateEmun.CONTRACT_COMPLETE.generateOrder(cid, tatContractEntity.getBusinessNo(), null));
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

}
