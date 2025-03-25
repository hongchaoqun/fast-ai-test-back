package cn.iocoder.yudao.module.system.controller.admin.project.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - api项目 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ProjectRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "29329")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "项目名称", example = "张三")
    @ExcelProperty("项目名称")
    private String name;

    @Schema(description = "项目描述", example = "你猜")
    @ExcelProperty("项目描述")
    private String description;

    @Schema(description = "接口数量", example = "13261")
    @ExcelProperty("接口数量")
    private Integer apiCount;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "用户id", example = "21036")
    @ExcelProperty("用户id")
    private Long userId;

}