package cn.iocoder.yudao.module.apiManager.service.env;

import java.util.*;

import cn.iocoder.yudao.module.apiManager.controller.env.vo.EnvPageReqVO;
import cn.iocoder.yudao.module.apiManager.controller.env.vo.EnvSaveReqVO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.env.EnvDO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.variable.VariableDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 项目环境 Service 接口
 *
 * @author 狗蛋
 */
public interface EnvService {

    /**
     * 创建项目环境
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEnv(@Valid EnvSaveReqVO createReqVO);

    /**
     * 更新项目环境
     *
     * @param updateReqVO 更新信息
     */
    void updateEnv(@Valid EnvSaveReqVO updateReqVO);

    /**
     * 删除项目环境
     *
     * @param id 编号
     */
    void deleteEnv(Long id);

    /**
     * 获得项目环境
     *
     * @param id 编号
     * @return 项目环境
     */
    EnvDO getEnv(Long id);

    /**
     * 获得项目环境分页
     *
     * @param pageReqVO 分页查询
     * @return 项目环境分页
     */
    PageResult<EnvDO> getEnvPage(EnvPageReqVO pageReqVO);

    // ==================== 子表（环境变量） ====================

    /**
     * 获得环境变量列表
     *
     * @param envId 环境id
     * @return 环境变量列表
     */
    List<VariableDO> getVariableListByEnvId(Long envId);

}