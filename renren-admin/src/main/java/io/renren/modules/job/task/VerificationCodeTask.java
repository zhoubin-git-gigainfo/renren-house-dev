package io.renren.modules.job.task;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.modules.house.entity.SequenceEntity;
import io.renren.modules.house.service.SequenceService;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 修改序列表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.2.0 2016-11-28
 */
@Component("verificationCodeTask")
public class VerificationCodeTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SequenceService sequenceService;

    public void updateSeq() {
        SequenceEntity sequenceEntity = new SequenceEntity();
        sequenceEntity.setCurrentVal(0);
        sequenceService.update(sequenceEntity,new EntityWrapper<SequenceEntity>().eq("sqlName","code_validate"));
    }

}
