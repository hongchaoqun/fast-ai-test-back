package cn.iocoder.yudao.module.system.controller.admin.apifile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 接口文件新增/修改 Request VO")
@Data
public class ApiFileSaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "29729")
    private Long id;

    @Schema(description = "文件名", example = "芋艿")
    private String fileName;

    @Schema(description = "文件url", example = "https://www.iocoder.cn")
    private String url;

    @Schema(description = "分组目录id", example = "20979")
    private Long dirId;

}