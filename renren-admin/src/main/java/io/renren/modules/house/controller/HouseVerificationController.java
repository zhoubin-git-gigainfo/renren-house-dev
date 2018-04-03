package io.renren.modules.house.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.renren.common.utils.HttpRequest;
import io.renren.common.utils.R;
import io.renren.common.utils.RequestUrlConfig;
import io.renren.modules.house.entity.HouseCheckEntity;
import io.renren.modules.house.entity.HouseEntity;
import io.renren.modules.house.entity.HouseVerificationCodeEntity;
import io.renren.modules.house.service.HouseVerificationCodeService;
import io.renren.modules.house.service.SequenceService;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/house")
@Api(tags = "房源核验")
public class HouseVerificationController {

    @Autowired
    private HouseVerificationCodeService houseVerificationCodeService;

    @Autowired
    private SequenceService sequenceService;

    @PostMapping("/list")
    public R list(String icno, String cdno) {
        HouseCheckEntity houseCheckEntity = new HouseCheckEntity();
        try {
            //TODO 接口已修改，需重新解析数据
            String json = HttpRequest.get(RequestUrlConfig.HOUSE_VERIFICATION_URL + "obligee?ic_no" + icno + "&cdno=" + cdno);
            ObjectMapper objectMapper = new ObjectMapper();
            houseCheckEntity = objectMapper.readValue(json, HouseCheckEntity.class);
            if (null == houseCheckEntity.getMbodycard() || null == houseCheckEntity.getHouse()) {
                return R.error();
            }
            houseCheckEntity.getHouse().stream().forEach(entity -> {
                //TODO 根据hid查询表是否存在有效状态的客体
                if (entity.getStates().size() == 0) {
                    HouseVerificationCodeEntity codeEntity = houseVerificationCodeService.queryByHid(entity.getHid());
                    if (null != codeEntity) {
                        entity.setCode(codeEntity.getCode());
                    }
                    if (null == codeEntity) {
                        String code = code();
                        codeEntity = new HouseVerificationCodeEntity();
                        codeEntity.setId(UUID.randomUUID().toString());
                        codeEntity.setHouseId(entity.getHid());
                        codeEntity.setState(1);
                        codeEntity.setCdno(cdno);
                        codeEntity.setIcno(icno);
                        codeEntity.setVDate(new Date());
                        codeEntity.setCode(code);
                        houseVerificationCodeService.insert(codeEntity);
                        entity.setCode(code);
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok().put("data", houseCheckEntity);
    }

    private String code() {
        String code = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        code = dateFormat.format(new Date());
        String seq = sequenceService.queryNextvalSeq("code_validate").toString();
        while (seq.toString().length() < 4) {
            seq = "0" + seq;
        }
        code = IMEICheck(code);
        //存在
        //TODO 查询核验码表--有效状态
        if (code == "存在") {
            code();
        }
        return code;
    }

    /**
     * imei
     * (1).将偶数位数字分别乘以2，分别计算个位数和十位数之和
     * (2).将奇数位数字相加，再加上上一步算得的值
     * (3).如果得出的数个位是0则校验位为0，否则为10减去个位数
     *
     * @param imeiString
     * @return
     */
    private static String IMEICheck(String imeiString) {
        char[] imeiChar = imeiString.toCharArray();
        int resultInt = 0;
        for (int i = 0; i < imeiChar.length; i++) {
            int a = Integer.parseInt(String.valueOf(imeiChar[i]));
            i++;
            final int temp = Integer.parseInt(String.valueOf(imeiChar[i])) * 2;
            final int b = temp < 10 ? temp : temp - 9;
            resultInt += a + b;
        }
        resultInt %= 10;
        resultInt = resultInt == 0 ? 0 : 10 - resultInt;
        return imeiString + resultInt;
    }
}
