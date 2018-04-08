package io.renren.modules.house.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.renren.common.utils.HttpRequest;
import io.renren.common.utils.R;
import io.renren.common.utils.RequestUrlConfig;
import io.renren.modules.api.annotation.LoginUser;
import io.renren.modules.house.entity.check.HouseCheckEntity;
import io.renren.modules.house.entity.HouseVerificationCodeEntity;
import io.renren.modules.house.entity.UserEntity;
import io.renren.modules.house.service.HouseVerificationCodeService;
import io.renren.modules.house.service.SequenceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public R list(@ApiIgnore @LoginUser UserEntity user, String cdno) {
        HouseCheckEntity houseCheckEntity = new HouseCheckEntity();
        try {
            //TODO 接口已修改，需重新解析数据
            String json = HttpRequest.get(RequestUrlConfig.HOUSE_VERIFICATION_URL + "&cd_no=" + cdno + "&ic_no=" + user.getIdCard());
            ObjectMapper objectMapper = new ObjectMapper();
            houseCheckEntity = objectMapper.readValue(json, HouseCheckEntity.class);
            if (null == houseCheckEntity.getData().getBodys() || null == houseCheckEntity.getData().getHouses()) {
                return R.error();
            }
            houseCheckEntity.getData().getHouses().stream().forEach(entity -> {
                //TODO 根据hid查询表是否存在有效状态的核验码
                if (entity.getPass_tag() == 1) {
                    HouseVerificationCodeEntity codeEntity = houseVerificationCodeService.queryByHid(entity.getHid());
                    if (null != codeEntity) {
                        entity.setCode(codeEntity.getCode());
                    }
                    if (null == codeEntity) {

                        /**
                         * 生成核验码   180408（年月日）+4位数序列（天为单位）+imei算法
                         */
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
                        String code = dateFormat.format(new Date());
                        String seq = sequenceService.queryNextvalSeq("code_validate").toString();
                        while (seq.toString().length() < 4) {
                            seq = "0" + seq;
                        }

                        codeEntity = new HouseVerificationCodeEntity();
                        codeEntity.setId(UUID.randomUUID().toString());
                        codeEntity.setHouseId(entity.getHid());
                        codeEntity.setState(1);
                        codeEntity.setCdno(cdno);
                        codeEntity.setIcno(user.getIdCard());
                        codeEntity.setVDate(new Date());
                        codeEntity.setCode(IMEICheck(code + seq));
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
