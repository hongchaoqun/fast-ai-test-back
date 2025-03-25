package cn.iocoder.yudao.module.system.controller.admin.apidata;

import cn.iocoder.yudao.module.system.controller.admin.apidata.vo.ApiDataPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.apidata.vo.ApiDataRespVO;
import cn.iocoder.yudao.module.system.controller.admin.apidata.vo.ApiDataSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.apidata.ApiDataDO;
import cn.iocoder.yudao.module.system.service.apidata.ApiDataService;
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

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 接口管理")
@RestController
@RequestMapping("/api/data")
@Validated
public class ApiDataController {

    @Resource
    private ApiDataService dataService;

    @PostMapping("/create")
    @Operation(summary = "创建接口管理")
    @PreAuthorize("@ss.hasPermission('api:data:create')")
    public CommonResult<Long> createData(@Valid @RequestBody ApiDataSaveReqVO createReqVO) {
        return success(dataService.createData(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新接口管理")
    @PreAuthorize("@ss.hasPermission('api:data:update')")
    public CommonResult<Boolean> updateData(@Valid @RequestBody ApiDataSaveReqVO updateReqVO) {
        dataService.updateData(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除接口管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('api:data:delete')")
    public CommonResult<Boolean> deleteData(@RequestParam("id") Long id) {
        dataService.deleteData(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得接口管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('api:data:query')")
    public CommonResult<ApiDataRespVO> getData(@RequestParam("id") Long id) {
        ApiDataDO data = dataService.getData(id);
        return success(BeanUtils.toBean(data, ApiDataRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得接口管理分页")
    @PreAuthorize("@ss.hasPermission('api:data:query')")
    public CommonResult<PageResult<ApiDataRespVO>> getDataPage(@Valid ApiDataPageReqVO pageReqVO) {
        PageResult<ApiDataDO> pageResult = dataService.getDataPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ApiDataRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出接口管理 Excel")
    @PreAuthorize("@ss.hasPermission('api:data:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDataExcel(@Valid ApiDataPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ApiDataDO> list = dataService.getDataPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "接口管理.xls", "数据", ApiDataRespVO.class,
                        BeanUtils.toBean(list, ApiDataRespVO.class));
    }

}