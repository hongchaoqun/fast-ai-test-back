package cn.iocoder.yudao.module.system.dal.dataobject.apidata;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 接口管理 DO
 *
 * @author 狗蛋
 */
@TableName("api_api_data")
@KeySequence("api_api_data_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiDataDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 接口名称
     */
    private String name;
    /**
     * 接口方法
     */
    private String method;
    /**
     * 接口路径
     */
    private String path;
    /**
     * 目录id
     */
    private Long dirId;

}