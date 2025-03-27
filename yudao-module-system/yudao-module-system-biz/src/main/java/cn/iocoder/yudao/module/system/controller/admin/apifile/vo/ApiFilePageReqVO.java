package cn.iocoder.yudao.module.system.controller.admin.apifile.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 接口文件分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ApiFilePageReqVO extends PageParam {

    @Schema(description = "文件名", example = "芋艿")
    private String fileName;

    @Schema(description = "文件url", example = "https://www.iocoder.cn")
    private String url;

    @Schema(description = "分组目录id", example = "20979")
    private Long dirId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

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

}