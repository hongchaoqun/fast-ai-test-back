package cn.iocoder.yudao.module.apiManager.controller.env.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 项目环境 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EnvRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "23686")
    @ExcelProperty("id")
    private Long id;

    @Schema(description = "环境名称", example = "张三")
    @ExcelProperty("环境名称")
    private String envName;

    @Schema(description = "基础Url", example = "https://www.iocoder.cn")
    @ExcelProperty("基础Url")
    private String baseUrl;

    @Schema(description = "项目id", example = "26680")
    @ExcelProperty("项目id")
    private Long projectId;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}