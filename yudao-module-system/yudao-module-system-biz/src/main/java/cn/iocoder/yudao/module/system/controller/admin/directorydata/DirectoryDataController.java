package cn.iocoder.yudao.module.system.controller.admin.directorydata;

import cn.iocoder.yudao.module.system.controller.admin.directorydata.vo.DirectoryDataPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.directorydata.vo.DirectoryDataRespVO;
import cn.iocoder.yudao.module.system.controller.admin.directorydata.vo.DirectoryDataSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.apidata.ApiDataDO;
import cn.iocoder.yudao.module.system.dal.dataobject.directorydata.DirectoryDataDO;
import cn.iocoder.yudao.module.system.service.directorydata.DirectoryDataService;
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


@Tag(name = "管理后台 - 目录管理")
@RestController
@RequestMapping("/api/directory-data")
@Validated
public class DirectoryDataController {

    @Resource
    private DirectoryDataService directoryDataService;

    @PostMapping("/create")
    @Operation(summary = "创建目录管理")
    @PreAuthorize("@ss.hasPermission('api:directory-data:create')")
    public CommonResult<Long> createDirectoryData(@Valid @RequestBody DirectoryDataSaveReqVO createReqVO) {
        return success(directoryDataService.createDirectoryData(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新目录管理")
    @PreAuthorize("@ss.hasPermission('api:directory-data:update')")
    public CommonResult<Boolean> updateDirectoryData(@Valid @RequestBody DirectoryDataSaveReqVO updateReqVO) {
        directoryDataService.updateDirectoryData(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除目录管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('api:directory-data:delete')")
    public CommonResult<Boolean> deleteDirectoryData(@RequestParam("id") Long id) {
        directoryDataService.deleteDirectoryData(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得目录管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('api:directory-data:query')")
    public CommonResult<DirectoryDataRespVO> getDirectoryData(@RequestParam("id") Long id) {
        DirectoryDataDO directoryData = directoryDataService.getDirectoryData(id);
        return success(BeanUtils.toBean(directoryData, DirectoryDataRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得目录管理分页")
    @PreAuthorize("@ss.hasPermission('api:directory-data:query')")
    public CommonResult<PageResult<DirectoryDataRespVO>> getDirectoryDataPage(@Valid DirectoryDataPageReqVO pageReqVO) {
        PageResult<DirectoryDataDO> pageResult = directoryDataService.getDirectoryDataPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DirectoryDataRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出目录管理 Excel")
    @PreAuthorize("@ss.hasPermission('api:directory-data:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDirectoryDataExcel(@Valid DirectoryDataPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DirectoryDataDO> list = directoryDataService.getDirectoryDataPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "目录管理.xls", "数据", DirectoryDataRespVO.class,
                        BeanUtils.toBean(list, DirectoryDataRespVO.class));
    }

    // ==================== 子表（接口管理） ====================

    @GetMapping("/data/list-by-dir-id")
    @Operation(summary = "获得接口管理列表")
    @Parameter(name = "dirId", description = "目录id")
    @PreAuthorize("@ss.hasPermission('api:directory-data:query')")
    public CommonResult<List<ApiDataDO>> getDataListByDirId(@RequestParam("dirId") Long dirId) {
        return success(directoryDataService.getDataListByDirId(dirId));
    }

}