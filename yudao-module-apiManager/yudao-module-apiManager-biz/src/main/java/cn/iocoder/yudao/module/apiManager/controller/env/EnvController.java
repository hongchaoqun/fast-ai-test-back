package cn.iocoder.yudao.module.apiManager.controller.env;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.apiManager.controller.env.vo.EnvPageReqVO;
import cn.iocoder.yudao.module.apiManager.controller.env.vo.EnvRespVO;
import cn.iocoder.yudao.module.apiManager.controller.env.vo.EnvSaveReqVO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.env.EnvDO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.variable.VariableDO;
import cn.iocoder.yudao.module.apiManager.service.env.EnvService;
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


@Tag(name = "管理后台 - 项目环境")
@RestController
@RequestMapping("/api/env")
@Validated
public class EnvController {

    @Resource
    private EnvService envService;

    @PostMapping("/create")
    @Operation(summary = "创建项目环境")
    @PreAuthorize("@ss.hasPermission('api:env:create')")
    public CommonResult<Long> createEnv(@Valid @RequestBody EnvSaveReqVO createReqVO) {
        return success(envService.createEnv(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新项目环境")
    @PreAuthorize("@ss.hasPermission('api:env:update')")
    public CommonResult<Boolean> updateEnv(@Valid @RequestBody EnvSaveReqVO updateReqVO) {
        envService.updateEnv(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除项目环境")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('api:env:delete')")
    public CommonResult<Boolean> deleteEnv(@RequestParam("id") Long id) {
        envService.deleteEnv(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得项目环境")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('api:env:query')")
    public CommonResult<EnvRespVO> getEnv(@RequestParam("id") Long id) {
        EnvDO env = envService.getEnv(id);
        return success(BeanUtils.toBean(env, EnvRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得项目环境分页")
    @PreAuthorize("@ss.hasPermission('api:env:query')")
    public CommonResult<PageResult<EnvRespVO>> getEnvPage(@Valid EnvPageReqVO pageReqVO) {
        PageResult<EnvDO> pageResult = envService.getEnvPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EnvRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出项目环境 Excel")
    @PreAuthorize("@ss.hasPermission('api:env:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEnvExcel(@Valid EnvPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EnvDO> list = envService.getEnvPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "项目环境.xls", "数据", EnvRespVO.class,
                        BeanUtils.toBean(list, EnvRespVO.class));
    }

    // ==================== 子表（环境变量） ====================

    @GetMapping("/variable/list-by-env-id")
    @Operation(summary = "获得环境变量列表")
    @Parameter(name = "envId", description = "环境id")
    @PreAuthorize("@ss.hasPermission('api:env:query')")
    public CommonResult<List<VariableDO>> getVariableListByEnvId(@RequestParam("envId") Long envId) {
        return success(envService.getVariableListByEnvId(envId));
    }

}