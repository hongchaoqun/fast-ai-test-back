package cn.iocoder.yudao.module.apiManager.controller.variable;

import cn.iocoder.yudao.module.apiManager.controller.variable.vo.VariablePageReqVO;
import cn.iocoder.yudao.module.apiManager.controller.variable.vo.VariableRespVO;
import cn.iocoder.yudao.module.apiManager.controller.variable.vo.VariableSaveReqVO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.variable.VariableDO;
import cn.iocoder.yudao.module.apiManager.service.variable.VariableService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 环境变量")
@RestController
@RequestMapping("/api/variable")
@Validated
public class VariableController {

    @Resource
    private VariableService variableService;

    @PostMapping("/create")
    @Operation(summary = "创建环境变量")
    @PreAuthorize("@ss.hasPermission('api:variable:create')")
    public CommonResult<Long> createVariable(@Valid @RequestBody VariableSaveReqVO createReqVO) {
        return success(variableService.createVariable(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新环境变量")
    @PreAuthorize("@ss.hasPermission('api:variable:update')")
    public CommonResult<Boolean> updateVariable(@Valid @RequestBody VariableSaveReqVO updateReqVO) {
        variableService.updateVariable(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除环境变量")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('api:variable:delete')")
    public CommonResult<Boolean> deleteVariable(@RequestParam("id") Long id) {
        variableService.deleteVariable(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得环境变量")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('api:variable:query')")
    public CommonResult<VariableRespVO> getVariable(@RequestParam("id") Long id) {
        VariableDO variable = variableService.getVariable(id);
        return success(BeanUtils.toBean(variable, VariableRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得环境变量分页")
    @PreAuthorize("@ss.hasPermission('api:variable:query')")
    public CommonResult<PageResult<VariableRespVO>> getVariablePage(@Valid VariablePageReqVO pageReqVO) {
        PageResult<VariableDO> pageResult = variableService.getVariablePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, VariableRespVO.class));
    }


}