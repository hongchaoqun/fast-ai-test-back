package cn.iocoder.yudao.module.apiManager.service.variable;

import cn.iocoder.yudao.module.apiManager.controller.variable.vo.VariablePageReqVO;
import cn.iocoder.yudao.module.apiManager.controller.variable.vo.VariableSaveReqVO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.variable.VariableDO;
import cn.iocoder.yudao.module.apiManager.dal.mysql.variable.VariableMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.VARIABLE_NOT_EXISTS;

/**
 * 环境变量 Service 实现类
 *
 * @author 狗蛋
 */
@Service
@Validated
public class VariableServiceImpl implements VariableService {

    @Resource
    private VariableMapper variableMapper;

    @Override
    public Long createVariable(VariableSaveReqVO createReqVO) {
        // 插入
        VariableDO variable = BeanUtils.toBean(createReqVO, VariableDO.class);
        variableMapper.insert(variable);
        // 返回
        return variable.getId();
    }

    @Override
    public void updateVariable(VariableSaveReqVO updateReqVO) {
        // 校验存在
        validateVariableExists(updateReqVO.getId());
        // 更新
        VariableDO updateObj = BeanUtils.toBean(updateReqVO, VariableDO.class);
        variableMapper.updateById(updateObj);
    }

    @Override
    public void deleteVariable(Long id) {
        // 校验存在
        validateVariableExists(id);
        // 删除
        variableMapper.deleteById(id);
    }

    private void validateVariableExists(Long id) {
        if (variableMapper.selectById(id) == null) {
            throw exception(VARIABLE_NOT_EXISTS);
        }
    }

    @Override
    public VariableDO getVariable(Long id) {
        return variableMapper.selectById(id);
    }

    @Override
    public PageResult<VariableDO> getVariablePage(VariablePageReqVO pageReqVO) {
        return variableMapper.selectPage(pageReqVO);
    }

}