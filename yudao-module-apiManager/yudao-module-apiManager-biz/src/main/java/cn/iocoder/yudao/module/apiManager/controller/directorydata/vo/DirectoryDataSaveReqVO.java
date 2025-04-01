package cn.iocoder.yudao.module.apiManager.controller.directorydata.vo;

import cn.iocoder.yudao.module.apiManager.dal.dataobject.apidata.ApiDataDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 目录管理新增/修改 Request VO")
@Data
public class DirectoryDataSaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6832")
    private Long id;

    @NotNull(message = "目录名不能为空")
    @Schema(description = "目录名", example = "李四")
    private String name;

    @Schema(description = "简介", example = "你说的对")
    private String description;

    @NotNull(message = "项目id不能为空")
    @Schema(description = "项目id", example = "8363")
    private Long projectId;

    @NotNull(message = "文件地址不能为空")
    @Schema(description = "文件地址", example = "http://sssss.jpg")
    private String fileUrl;

    @Schema(description = "接口管理列表")
    private List<ApiDataDO> datas;

}