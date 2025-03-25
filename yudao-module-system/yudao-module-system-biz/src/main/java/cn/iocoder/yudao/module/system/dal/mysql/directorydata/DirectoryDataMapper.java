package cn.iocoder.yudao.module.system.dal.mysql.directorydata;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.controller.admin.directorydata.vo.DirectoryDataPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.directorydata.DirectoryDataDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 目录管理 Mapper
 *
 * @author 狗蛋
 */
@Mapper
public interface DirectoryDataMapper extends BaseMapperX<DirectoryDataDO> {

    default PageResult<DirectoryDataDO> selectPage(DirectoryDataPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DirectoryDataDO>()
                .likeIfPresent(DirectoryDataDO::getName, reqVO.getName())
                .eqIfPresent(DirectoryDataDO::getDescription, reqVO.getDescription())
                .betweenIfPresent(DirectoryDataDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(DirectoryDataDO::getProjectId, reqVO.getProjectId())
                .orderByDesc(DirectoryDataDO::getId));
    }

}