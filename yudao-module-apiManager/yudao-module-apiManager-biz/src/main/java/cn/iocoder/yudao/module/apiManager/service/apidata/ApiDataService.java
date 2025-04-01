package cn.iocoder.yudao.module.apiManager.service.apidata;

import java.util.*;

import cn.iocoder.yudao.module.apiManager.controller.apidata.vo.ApiDataPageReqVO;
import cn.iocoder.yudao.module.apiManager.controller.apidata.vo.ApiDataSaveReqVO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.apidata.ApiDataDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 接口管理 Service 接口
 *
 * @author 狗蛋
 */
public interface ApiDataService {

    /**
     * 创建接口管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createData(@Valid ApiDataSaveReqVO createReqVO);

    /**
     * 更新接口管理
     *
     * @param updateReqVO 更新信息
     */
    void updateData(@Valid ApiDataSaveReqVO updateReqVO);

    /**
     * 删除接口管理
     *
     * @param id 编号
     */
    void deleteData(Long id);

    /**
     * 获得接口管理
     *
     * @param id 编号
     * @return 接口管理
     */
    ApiDataDO getData(Long id);

    /**
     * 获得接口管理分页
     *
     * @param pageReqVO 分页查询
     * @return 接口管理分页
     */
    PageResult<ApiDataDO> getDataPage(ApiDataPageReqVO pageReqVO);

    /**
     * 获得接口管理列表
     * @param id
     * @return
     */
    List<ApiDataDO> getDataListByDirId(Long id);
}