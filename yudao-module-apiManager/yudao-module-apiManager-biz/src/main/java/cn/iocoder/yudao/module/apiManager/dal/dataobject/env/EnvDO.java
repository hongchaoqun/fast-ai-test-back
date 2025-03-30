package cn.iocoder.yudao.module.apiManager.dal.dataobject.env;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 项目环境 DO
 *
 * @author 狗蛋
 */
@TableName("api_env")
@KeySequence("api_env_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnvDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 环境名称
     */
    private String envName;
    /**
     * 基础Url
     */
    private String baseUrl;
    /**
     * 项目id
     */
    private Long projectId;

}