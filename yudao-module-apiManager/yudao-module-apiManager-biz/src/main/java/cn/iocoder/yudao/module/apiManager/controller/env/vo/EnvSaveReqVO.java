package cn.iocoder.yudao.module.apiManager.controller.env.vo;

import cn.iocoder.yudao.module.apiManager.dal.dataobject.variable.VariableDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 项目环境新增/修改 Request VO")
@Data
public class EnvSaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "23686")
    private Long id;

    @Schema(description = "环境名称", example = "张三")
    private String envName;

    @Schema(description = "基础Url", example = "https://www.iocoder.cn")
    private String baseUrl;

    @Schema(description = "项目id", example = "26680")
    private Long projectId;

    @Schema(description = "环境变量列表")
    private List<VariableDO> variables;

}