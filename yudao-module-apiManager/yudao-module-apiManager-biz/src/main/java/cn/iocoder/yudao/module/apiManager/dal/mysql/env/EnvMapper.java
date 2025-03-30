package cn.iocoder.yudao.module.apiManager.dal.mysql.env;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.apiManager.controller.env.vo.EnvPageReqVO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.env.EnvDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;


/**
 * 项目环境 Mapper
 *
 * @author 狗蛋
 */
@Mapper
public interface EnvMapper extends BaseMapperX<EnvDO> {

    default PageResult<EnvDO> selectPage(EnvPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EnvDO>()
                .likeIfPresent(EnvDO::getEnvName, reqVO.getEnvName())
                .eqIfPresent(EnvDO::getBaseUrl, reqVO.getBaseUrl())
                .eqIfPresent(EnvDO::getProjectId, reqVO.getProjectId())
                .betweenIfPresent(EnvDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EnvDO::getId));
    }

}