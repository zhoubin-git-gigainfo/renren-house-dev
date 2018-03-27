package io.renren.modules.house.controller;

import io.renren.common.utils.R;
import io.renren.modules.house.service.TsCodeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 基础信息
 *
 * @author zhoubin
 * @email zhoubin@gigainfo.com.cn
 * @date 2018-03-22 10:10:47
 */
@RestController
@RequestMapping("/app/house/tscode")
@Api("APP登录接口")
public class TsCodeController {

    @Autowired
    private TsCodeService tsCodeService;

    /**
     * @param sseq *
     *             户型       77000
     *             用途       53008200
     *             权属       90003
     *             朝向       7900
     *             装修       100000528084
     * @return
     */
    @RequestMapping("list")
    public R list(String sseq) {
        Map map = new HashMap();
        //户型77000
        List<Map> maps = tsCodeService.queryListByParentId(sseq);
        return R.ok().put("page", maps);
    }

    /**
     * @param sseq *
     *             户型       77000
     *             用途       53008200
     *             权属       90003
     *             朝向       7900
     *             装修       100000528084
     * @return
     */
    @RequestMapping("tree")
    public R tree(String sseq) {
        Map map = new HashMap();
        //户型77000
        List<Map> maps = tsCodeService.queryTreeByParentId(sseq);
        return R.ok().put("page", maps);
    }

}
