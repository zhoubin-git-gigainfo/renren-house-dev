package io.renren.modules.house.controller;

import io.renren.common.utils.HttpRequest;
import io.renren.common.utils.R;
import io.renren.common.utils.RequestUrlConfig;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/app/house")
@Api(tags = "房源核验")
public class HouseVerificationController {

    @PostMapping("/list")
    public R list(String icno,String cdno) {
        String json = HttpRequest.get(RequestUrlConfig.HOUSE_VERIFICATION_URL + "obligee?ic_no"+icno+"&cdno="+cdno);

        return R.ok();
    }
}
