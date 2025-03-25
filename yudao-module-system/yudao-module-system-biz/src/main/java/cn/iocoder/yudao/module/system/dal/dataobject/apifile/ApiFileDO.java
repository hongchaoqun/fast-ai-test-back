package cn.iocoder.yudao.module.system.dal.dataobject.apifile;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 接口文件 DO
 *
 * @author 狗蛋
 */
@TableName("api_api_file")
@KeySequence("api_api_file_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiFileDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件url
     */
    private String url;
    /**
     * 分组目录id
     */
    private Long dirId;

}