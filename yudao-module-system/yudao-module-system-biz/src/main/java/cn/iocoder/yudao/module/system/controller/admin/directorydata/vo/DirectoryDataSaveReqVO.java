package cn.iocoder.yudao.module.system.controller.admin.directorydata.vo;

import cn.iocoder.yudao.module.system.dal.dataobject.apidata.ApiDataDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 目录管理新增/修改 Request VO")
@Data
public class DirectoryDataSaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6832")
    private Long id;

    @Schema(description = "目录名", example = "李四")
    private String name;

    @Schema(description = "简介", example = "你说的对")
    private String description;

    @Schema(description = "项目id", example = "8363")
    private Long projectId;

    @Schema(description = "接口管理列表")
    private List<ApiDataDO> datas;

}