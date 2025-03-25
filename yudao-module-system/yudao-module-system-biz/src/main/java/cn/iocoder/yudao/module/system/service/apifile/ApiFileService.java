package cn.iocoder.yudao.module.system.service.apifile;

import java.util.*;

import cn.iocoder.yudao.module.system.controller.admin.apifile.vo.ApiFilePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.apifile.vo.ApiFileSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.apifile.ApiFileDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

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

}