package cn.iocoder.yudao.module.system.controller.admin.project;

import cn.iocoder.yudao.module.system.controller.admin.project.vo.ProjectSaveReqVO;
import cn.iocoder.yudao.module.system.controller.admin.project.vo.ProjectPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.project.vo.ProjectRespVO;
import cn.iocoder.yudao.module.system.dal.dataobject.project.ProjectDO;
import cn.iocoder.yudao.module.system.service.project.ProjectService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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


@Tag(name = "管理后台 - api项目")
@RestController
@RequestMapping("/api/project")
@Validated
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @PostMapping("/create")
    @Operation(summary = "创建api项目")
    @PreAuthorize("@ss.hasPermission('api:project:create')")
    public CommonResult<Long> createProject(@Valid @RequestBody ProjectSaveReqVO createReqVO) {
        return success(projectService.createProject(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新api项目")
    @PreAuthorize("@ss.hasPermission('api:project:update')")
    public CommonResult<Boolean> updateProject(@Valid @RequestBody ProjectSaveReqVO updateReqVO) {
        projectService.updateProject(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除api项目")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('api:project:delete')")
    public CommonResult<Boolean> deleteProject(@RequestParam("id") Long id) {
        projectService.deleteProject(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得api项目")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('api:project:query')")
    public CommonResult<ProjectRespVO> getProject(@RequestParam("id") Long id) {
        ProjectDO project = projectService.getProject(id);
        return success(BeanUtils.toBean(project, ProjectRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得api项目分页")
    @PreAuthorize("@ss.hasPermission('api:project:query')")
    public CommonResult<PageResult<ProjectRespVO>> getProjectPage(@Valid ProjectPageReqVO pageReqVO) {
        PageResult<ProjectDO> pageResult = projectService.getProjectPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ProjectRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出api项目 Excel")
    @PreAuthorize("@ss.hasPermission('api:project:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportProjectExcel(@Valid ProjectPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ProjectDO> list = projectService.getProjectPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "api项目.xls", "数据", ProjectRespVO.class,
                        BeanUtils.toBean(list, ProjectRespVO.class));
    }

}