package cn.iocoder.yudao.module.apiManager.controller.apifile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 接口文件 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ApiFileRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "29729")
    @ExcelProperty("id")
    private Long id;

    @Schema(description = "文件名", example = "芋艿")
    @ExcelProperty("文件名")
    private String fileName;

    @Schema(description = "文件url", example = "https://www.iocoder.cn")
    @ExcelProperty("文件url")
    private String url;

    @Schema(description = "分组目录id", example = "20979")
    @ExcelProperty("分组目录id")
    private Long dirId;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 数据量
     */
    @Schema(description = "数据量", example = "20979")
    private Integer number;

    /**
     * 训练模式
     */
    @Schema(description = "训练模式", example = "直接训练")
    private String model;

    /**
     * 是否启用 0=否 ，1=是
     */
    @Schema(description = "是否启用 0=否 ，1=是", example = "1")
    private Integer enabled;

    @Schema(description = "分组目录名", example = "芋艿")
    private String dirName;

}