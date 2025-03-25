package cn.iocoder.yudao.module.system.service.directorydata;

import java.util.*;

import cn.iocoder.yudao.module.system.controller.admin.directorydata.vo.DirectoryDataPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.directorydata.vo.DirectoryDataSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.apidata.ApiDataDO;
import cn.iocoder.yudao.module.system.dal.dataobject.directorydata.DirectoryDataDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 目录管理 Service 接口
 *
 * @author 狗蛋
 */
public interface DirectoryDataService {

    /**
     * 创建目录管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDirectoryData(@Valid DirectoryDataSaveReqVO createReqVO);

    /**
     * 更新目录管理
     *
     * @param updateReqVO 更新信息
     */
    void updateDirectoryData(@Valid DirectoryDataSaveReqVO updateReqVO);

    /**
     * 删除目录管理
     *
     * @param id 编号
     */
    void deleteDirectoryData(Long id);

    /**
     * 获得目录管理
     *
     * @param id 编号
     * @return 目录管理
     */
    DirectoryDataDO getDirectoryData(Long id);

    /**
     * 获得目录管理分页
     *
     * @param pageReqVO 分页查询
     * @return 目录管理分页
     */
    PageResult<DirectoryDataDO> getDirectoryDataPage(DirectoryDataPageReqVO pageReqVO);

    // ==================== 子表（接口管理） ====================

    /**
     * 获得接口管理列表
     *
     * @param dirId 目录id
     * @return 接口管理列表
     */
    List<ApiDataDO> getDataListByDirId(Long dirId);

}