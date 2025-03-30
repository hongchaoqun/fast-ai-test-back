package cn.iocoder.yudao.module.apiManager.controller.variable.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 环境变量 Response VO")
@Data
@ExcelIgnoreUnannotated
public class VariableRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "17103")
    @ExcelProperty("id")
    private Long id;

    @Schema(description = "变量名")
    @ExcelProperty("变量名")
    private String varKey;

    @Schema(description = "变量值")
    @ExcelProperty("变量值")
    private String varValue;

    @Schema(description = "环境id", example = "10365")
    @ExcelProperty("环境id")
    private Long envId;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}