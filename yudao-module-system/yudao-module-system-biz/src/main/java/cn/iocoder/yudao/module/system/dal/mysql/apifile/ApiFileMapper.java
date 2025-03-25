package cn.iocoder.yudao.module.system.dal.mysql.apifile;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.controller.admin.apifile.vo.ApiFilePageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.apifile.ApiFileDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 接口文件 Mapper
 *
 * @author 狗蛋
 */
@Mapper
public interface ApiFileMapper extends BaseMapperX<ApiFileDO> {

    default PageResult<ApiFileDO> selectPage(ApiFilePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ApiFileDO>()
                .likeIfPresent(ApiFileDO::getFileName, reqVO.getFileName())
                .eqIfPresent(ApiFileDO::getUrl, reqVO.getUrl())
                .eqIfPresent(ApiFileDO::getDirId, reqVO.getDirId())
                .betweenIfPresent(ApiFileDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ApiFileDO::getId));
    }

}