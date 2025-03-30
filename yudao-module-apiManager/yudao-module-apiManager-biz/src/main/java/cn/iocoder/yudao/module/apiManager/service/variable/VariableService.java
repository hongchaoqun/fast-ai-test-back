package cn.iocoder.yudao.module.apiManager.service.variable;

import java.util.*;

import cn.iocoder.yudao.module.apiManager.controller.variable.vo.VariablePageReqVO;
import cn.iocoder.yudao.module.apiManager.controller.variable.vo.VariableSaveReqVO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.variable.VariableDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 环境变量 Service 接口
 *
 * @author 狗蛋
 */
public interface VariableService {

    /**
     * 创建环境变量
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createVariable(@Valid VariableSaveReqVO createReqVO);

    /**
     * 更新环境变量
     *
     * @param updateReqVO 更新信息
     */
    void updateVariable(@Valid VariableSaveReqVO updateReqVO);

    /**
     * 删除环境变量
     *
     * @param id 编号
     */
    void deleteVariable(Long id);

    /**
     * 获得环境变量
     *
     * @param id 编号
     * @return 环境变量
     */
    VariableDO getVariable(Long id);

    /**
     * 获得环境变量分页
     *
     * @param pageReqVO 分页查询
     * @return 环境变量分页
     */
    PageResult<VariableDO> getVariablePage(VariablePageReqVO pageReqVO);

}