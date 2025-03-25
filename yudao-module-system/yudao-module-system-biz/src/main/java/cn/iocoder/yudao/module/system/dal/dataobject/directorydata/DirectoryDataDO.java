package cn.iocoder.yudao.module.system.dal.dataobject.directorydata;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 目录管理 DO
 *
 * @author 狗蛋
 */
@TableName("api_directory_data")
@KeySequence("api_directory_data_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectoryDataDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 目录名
     */
    private String name;
    /**
     * 简介
     */
    private String description;
    /**
     * 项目id
     */
    private Long projectId;

}