package cn.iocoder.yudao.module.apiManager.controller.variable.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 环境变量新增/修改 Request VO")
@Data
public class VariableSaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "17103")
    private Long id;

    @Schema(description = "变量名")
    private String varKey;

    @Schema(description = "变量值")
    private String varValue;

    @Schema(description = "环境id", example = "10365")
    private Long envId;

}