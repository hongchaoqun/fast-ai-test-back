package cn.iocoder.yudao.module.system.controller.admin.directorydata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 目录管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DirectoryDataRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6832")
    @ExcelProperty("id")
    private Long id;

    @Schema(description = "目录名", example = "李四")
    @ExcelProperty("目录名")
    private String name;

    @Schema(description = "简介", example = "你说的对")
    @ExcelProperty("简介")
    private String description;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "项目id", example = "8363")
    @ExcelProperty("项目id")
    private Long projectId;

}