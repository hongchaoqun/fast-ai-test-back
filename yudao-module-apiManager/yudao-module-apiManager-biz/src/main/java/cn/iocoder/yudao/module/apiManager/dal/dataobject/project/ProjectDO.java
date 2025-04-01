package cn.iocoder.yudao.module.apiManager.dal.dataobject.project;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * api项目 DO
 *
 * @author 狗蛋
 */
@TableName("api_project")
@KeySequence("api_project_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目描述
     */
    private String description;
    /**
     * 接口数量
     */
    private Integer apiCount;
    /**
     * 用户id
     */
    private Long userId;

}