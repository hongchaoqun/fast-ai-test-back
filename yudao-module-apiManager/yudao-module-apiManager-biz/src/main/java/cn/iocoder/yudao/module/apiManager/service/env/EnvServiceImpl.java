package cn.iocoder.yudao.module.apiManager.service.env;

import cn.iocoder.yudao.module.apiManager.controller.env.vo.EnvPageReqVO;
import cn.iocoder.yudao.module.apiManager.controller.env.vo.EnvSaveReqVO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.env.EnvDO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.variable.VariableDO;
import cn.iocoder.yudao.module.apiManager.dal.mysql.env.EnvMapper;
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
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.ENV_NOT_EXISTS;

/**
 * 项目环境 Service 实现类
 *
 * @author 狗蛋
 */
@Service
@Validated
public class EnvServiceImpl implements EnvService {

    @Resource
    private EnvMapper envMapper;
    @Resource
    private VariableMapper variableMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createEnv(EnvSaveReqVO createReqVO) {
        // 插入
        EnvDO env = BeanUtils.toBean(createReqVO, EnvDO.class);
        envMapper.insert(env);

        // 插入子表
        createVariableList(env.getId(), createReqVO.getVariables());
        // 返回
        return env.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEnv(EnvSaveReqVO updateReqVO) {
        // 校验存在
        validateEnvExists(updateReqVO.getId());
        // 更新
        EnvDO updateObj = BeanUtils.toBean(updateReqVO, EnvDO.class);
        envMapper.updateById(updateObj);

        // 更新子表
        updateVariableList(updateReqVO.getId(), updateReqVO.getVariables());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEnv(Long id) {
        // 校验存在
        validateEnvExists(id);
        // 删除
        envMapper.deleteById(id);

        // 删除子表
        deleteVariableByEnvId(id);
    }

    private void validateEnvExists(Long id) {
        if (envMapper.selectById(id) == null) {
            throw exception(ENV_NOT_EXISTS);
        }
    }

    @Override
    public EnvDO getEnv(Long id) {
        return envMapper.selectById(id);
    }

    @Override
    public PageResult<EnvDO> getEnvPage(EnvPageReqVO pageReqVO) {
        return envMapper.selectPage(pageReqVO);
    }

    // ==================== 子表（环境变量） ====================

    @Override
    public List<VariableDO> getVariableListByEnvId(Long envId) {
        return variableMapper.selectListByEnvId(envId);
    }

    private void createVariableList(Long envId, List<VariableDO> list) {
        list.forEach(o -> o.setEnvId(envId));
        variableMapper.insertBatch(list);
    }

    private void updateVariableList(Long envId, List<VariableDO> list) {
        deleteVariableByEnvId(envId);
		list.forEach(o -> o.setId(null).setUpdater(null).setUpdateTime(null)); // 解决更新情况下：1）id 冲突；2）updateTime 不更新
        createVariableList(envId, list);
    }

    private void deleteVariableByEnvId(Long envId) {
        variableMapper.deleteByEnvId(envId);
    }

}