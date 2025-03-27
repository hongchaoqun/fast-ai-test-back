package cn.iocoder.yudao.module.system.controller.admin.apifile;

import cn.iocoder.yudao.module.system.controller.admin.apifile.vo.ApiFilePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.apifile.vo.ApiFileRespVO;
import cn.iocoder.yudao.module.system.controller.admin.apifile.vo.ApiFileSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.apifile.ApiFileDO;
import cn.iocoder.yudao.module.system.dal.dataobject.directorydata.DirectoryDataDO;
import cn.iocoder.yudao.module.system.service.apifile.ApiFileService;
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


@Tag(name = "管理后台 - 接口文件")
@RestController
@RequestMapping("/api/file")
@Validated
public class ApiFileController {

    @Resource
    private ApiFileService fileService;
    @Resource
    private DirectoryDataService directoryDataService;

    @PostMapping("/create")
    @Operation(summary = "创建接口文件")
    @PreAuthorize("@ss.hasPermission('api:file:create')")
    public CommonResult<Long> createFile(@Valid @RequestBody ApiFileSaveReqVO createReqVO) {
        return success(fileService.createFile(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新接口文件")
    @PreAuthorize("@ss.hasPermission('api:file:update')")
    public CommonResult<Boolean> updateFile(@Valid @RequestBody ApiFileSaveReqVO updateReqVO) {
        fileService.updateFile(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除接口文件")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('api:file:delete')")
    public CommonResult<Boolean> deleteFile(@RequestParam("id") Long id) {
        fileService.deleteFile(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得接口文件")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('api:file:query')")
    public CommonResult<ApiFileRespVO> getFile(@RequestParam("id") Long id) {
        ApiFileDO file = fileService.getFile(id);
        return success(BeanUtils.toBean(file, ApiFileRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得接口文件分页")
    @PreAuthorize("@ss.hasPermission('api:file:query')")
    public CommonResult<PageResult<ApiFileRespVO>> getFilePage(@Valid ApiFilePageReqVO pageReqVO) {
        PageResult<ApiFileDO> pageResult = fileService.getFilePage(pageReqVO);
        PageResult<ApiFileRespVO> pageResultVO = BeanUtils.toBean(pageResult, ApiFileRespVO.class);
        pageResultVO.getList().forEach(item -> {
            DirectoryDataDO directoryData = directoryDataService.getDirectoryData(item.getDirId());
            if(directoryData != null){
                item.setDirName(directoryData.getName());
            }
        });
        return success(pageResultVO);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出接口文件 Excel")
    @PreAuthorize("@ss.hasPermission('api:file:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFileExcel(@Valid ApiFilePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ApiFileDO> list = fileService.getFilePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "接口文件.xls", "数据", ApiFileRespVO.class,
                        BeanUtils.toBean(list, ApiFileRespVO.class));
    }

}