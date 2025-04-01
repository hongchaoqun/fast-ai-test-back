package cn.iocoder.yudao.module.apiManager.service.apifile;

import cn.iocoder.yudao.module.apiManager.controller.apifile.vo.ApiFilePageReqVO;
import cn.iocoder.yudao.module.apiManager.controller.apifile.vo.ApiFileSaveReqVO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.apifile.ApiFileDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 接口文件 Service 接口
 *
 * @author 狗蛋
 */
public interface ApiFileService {

    /**
     * 创建接口文件
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFile(@Valid ApiFileSaveReqVO createReqVO);

    /**
     * 更新接口文件
     *
     * @param updateReqVO 更新信息
     */
    void updateFile(@Valid ApiFileSaveReqVO updateReqVO);

    /**
     * 删除接口文件
     *
     * @param id 编号
     */
    void deleteFile(Long id);

    /**
     * 获得接口文件
     *
     * @param id 编号
     * @return 接口文件
     */
    ApiFileDO getFile(Long id);

    /**
     * 获得接口文件分页
     *
     * @param pageReqVO 分页查询
     * @return 接口文件分页
     */
    PageResult<ApiFileDO> getFilePage(ApiFilePageReqVO pageReqVO);

    void enable(Long id);
}