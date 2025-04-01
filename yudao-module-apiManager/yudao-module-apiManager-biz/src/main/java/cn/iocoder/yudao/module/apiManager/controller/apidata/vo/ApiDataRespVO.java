package cn.iocoder.yudao.module.apiManager.controller.apidata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 接口管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ApiDataRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10081")
    @ExcelProperty("id")
    private Long id;

    @Schema(description = "接口名称", example = "赵六")
    @ExcelProperty("接口名称")
    private String name;

    @Schema(description = "接口方法")
    @ExcelProperty("接口方法")
    private String method;

    @Schema(description = "接口路径")
    @ExcelProperty("接口路径")
    private String path;

    @Schema(description = "目录id", example = "15920")
    @ExcelProperty("目录id")
    private Long dirId;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}