package cn.iocoder.yudao.module.apiManager.service.project;

import cn.iocoder.yudao.module.apiManager.controller.project.vo.ProjectPageReqVO;
import cn.iocoder.yudao.module.apiManager.controller.project.vo.ProjectSaveReqVO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.project.ProjectDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * api项目 Service 接口
 *
 * @author 狗蛋
 */
public interface ProjectService {

    /**
     * 创建api项目
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProject(@Valid ProjectSaveReqVO createReqVO);

    /**
     * 更新api项目
     *
     * @param updateReqVO 更新信息
     */
    void updateProject(@Valid ProjectSaveReqVO updateReqVO);

    /**
     * 删除api项目
     *
     * @param id 编号
     */
    void deleteProject(Long id);

    /**
     * 获得api项目
     *
     * @param id 编号
     * @return api项目
     */
    ProjectDO getProject(Long id);

    /**
     * 获得api项目分页
     *
     * @param pageReqVO 分页查询
     * @return api项目分页
     */
    PageResult<ProjectDO> getProjectPage(ProjectPageReqVO pageReqVO);

}