package cn.iocoder.yudao.module.apiManager.dal.mysql.project;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.apiManager.controller.project.vo.ProjectPageReqVO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.project.ProjectDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * api项目 Mapper
 *
 * @author 狗蛋
 */
@Mapper
public interface ProjectMapper extends BaseMapperX<ProjectDO> {

    default PageResult<ProjectDO> selectPage(ProjectPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProjectDO>()
                .likeIfPresent(ProjectDO::getName, reqVO.getName())
                .eqIfPresent(ProjectDO::getDescription, reqVO.getDescription())
                .eqIfPresent(ProjectDO::getApiCount, reqVO.getApiCount())
                .betweenIfPresent(ProjectDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ProjectDO::getUserId, reqVO.getUserId())
                .orderByDesc(ProjectDO::getId));
    }

}