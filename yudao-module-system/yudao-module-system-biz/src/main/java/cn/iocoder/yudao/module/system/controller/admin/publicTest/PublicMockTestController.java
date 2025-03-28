package cn.iocoder.yudao.module.system.controller.admin.publicTest;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.project.vo.ProjectPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.project.vo.ProjectRespVO;
import cn.iocoder.yudao.module.system.controller.admin.project.vo.ProjectSaveReqVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * @author hongcq
 * @since 2025/3/28
 */
@Tag(name = "管理后台 - 公开测试接口管理")
@RestController
@RequestMapping("/public/test")
@Validated
public class PublicMockTestController {

    @PostMapping("/create")
    @Operation(summary = "创建项目")
    @PermitAll
    public CommonResult<Long> createProject(@Valid @RequestBody ProjectSaveReqVO createReqVO) {
        return success(1l);
    }

    @GetMapping("/page")
    @Operation(summary = "获得项目分页")
    @PermitAll
    public CommonResult<PageResult<ProjectRespVO>> getProjectPage(@Valid ProjectPageReqVO pageReqVO) {
        PageResult<ProjectRespVO> pageResult = new PageResult<>();
        return success(pageResult);
    }
}
