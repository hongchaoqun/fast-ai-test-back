package cn.iocoder.yudao.module.apiManager.dal.mysql.variable;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.apiManager.controller.variable.vo.VariablePageReqVO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.variable.VariableDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 环境变量 Mapper
 *
 * @author 狗蛋
 */
@Mapper
public interface VariableMapper extends BaseMapperX<VariableDO> {

    default PageResult<VariableDO> selectPage(VariablePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VariableDO>()
                .eqIfPresent(VariableDO::getVarKey, reqVO.getVarKey())
                .eqIfPresent(VariableDO::getVarValue, reqVO.getVarValue())
                .eqIfPresent(VariableDO::getEnvId, reqVO.getEnvId())
                .betweenIfPresent(VariableDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(VariableDO::getId));
    }

    default List<VariableDO> selectListByEnvId(Long envId){
        return selectList(new LambdaQueryWrapperX<VariableDO>()
                .eq(VariableDO::getEnvId, envId));
    };

    default void deleteByEnvId(Long envId){
         delete(new LambdaQueryWrapperX<VariableDO>().eq(VariableDO::getEnvId, envId));
    };
}