package cn.iocoder.yudao.module.apiManager.controller.project.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.checkerframework.checker.units.qual.N;

@Schema(description = "管理后台 - api项目新增/修改 Request VO")
@Data
public class ProjectSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "29329")
    private Long id;

    @Schema(description = "项目名称", example = "张三")
    @NotNull(message = "项目名称不能为空")
    private String name;

    @Schema(description = "项目描述", example = "你猜")
    @NotNull(message = "项目描述不能为空")
    private String description;

    @Schema(description = "接口数量", example = "13261")
    private Integer apiCount;

    @Schema(description = "用户id", example = "21036")
    private Long userId;

}