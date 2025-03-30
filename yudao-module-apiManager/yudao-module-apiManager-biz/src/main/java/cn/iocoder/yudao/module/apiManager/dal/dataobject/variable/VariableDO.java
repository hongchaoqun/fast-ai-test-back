package cn.iocoder.yudao.module.apiManager.dal.dataobject.variable;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 环境变量 DO
 *
 * @author 狗蛋
 */
@TableName("api_variable")
@KeySequence("api_variable_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariableDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 变量名
     */
    private String varKey;
    /**
     * 变量值
     */
    private String varValue;
    /**
     * 环境id
     */
    private Long envId;

}