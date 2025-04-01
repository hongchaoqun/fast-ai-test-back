package cn.iocoder.yudao.module.apiManager.controller.apidata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 接口管理新增/修改 Request VO")
@Data
public class ApiDataSaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10081")
    private Long id;

    @Schema(description = "接口名称", example = "赵六")
    private String name;

    @Schema(description = "接口方法")
    private String method;

    @Schema(description = "接口路径")
    private String path;

    @Schema(description = "目录id", example = "15920")
    private Long dirId;

}