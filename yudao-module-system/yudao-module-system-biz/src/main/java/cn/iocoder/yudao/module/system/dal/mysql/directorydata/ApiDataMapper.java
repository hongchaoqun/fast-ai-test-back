package cn.iocoder.yudao.module.system.dal.mysql.directorydata;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.apidata.ApiDataDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 接口管理 Mapper
 *
 * @author 狗蛋
 */
@Mapper
public interface ApiDataMapper extends BaseMapperX<ApiDataDO> {

    default List<ApiDataDO> selectListByDirId(Long dirId) {
        return selectList(ApiDataDO::getDirId, dirId);
    }

    default int deleteByDirId(Long dirId) {
        return delete(ApiDataDO::getDirId, dirId);
    }

}