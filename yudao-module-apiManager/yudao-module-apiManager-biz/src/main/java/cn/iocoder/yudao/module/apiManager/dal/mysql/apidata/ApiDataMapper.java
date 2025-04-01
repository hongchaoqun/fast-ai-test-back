package cn.iocoder.yudao.module.apiManager.dal.mysql.apidata;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.apiManager.controller.apidata.vo.ApiDataPageReqVO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.apidata.ApiDataDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 接口管理 Mapper
 *
 * @author 狗蛋
 */
@Mapper
public interface ApiDataMapper extends BaseMapperX<ApiDataDO> {

    default PageResult<ApiDataDO> selectPage(ApiDataPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ApiDataDO>()
                .likeIfPresent(ApiDataDO::getName, reqVO.getName())
                .eqIfPresent(ApiDataDO::getMethod, reqVO.getMethod())
                .eqIfPresent(ApiDataDO::getPath, reqVO.getPath())
                .eqIfPresent(ApiDataDO::getDirId, reqVO.getDirId())
                .betweenIfPresent(ApiDataDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ApiDataDO::getId));
    }

    default List<ApiDataDO> selectListByDirId(Long dirId){
        return selectList(ApiDataDO::getDirId, dirId);
    };

    default void deleteByDirId(Long dirId){
        delete(ApiDataDO::getDirId, dirId);
    };
}