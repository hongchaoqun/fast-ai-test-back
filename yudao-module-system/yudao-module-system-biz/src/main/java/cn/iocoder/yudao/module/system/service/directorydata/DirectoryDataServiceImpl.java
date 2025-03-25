package cn.iocoder.yudao.module.system.service.directorydata;

import cn.iocoder.yudao.module.system.controller.admin.directorydata.vo.DirectoryDataPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.directorydata.vo.DirectoryDataSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.apidata.ApiDataDO;
import cn.iocoder.yudao.module.system.dal.dataobject.directorydata.DirectoryDataDO;
import cn.iocoder.yudao.module.system.dal.mysql.apidata.ApiDataMapper;
import cn.iocoder.yudao.module.system.dal.mysql.directorydata.DirectoryDataMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.DIRECTORY_DATA_NOT_EXISTS;

/**
 * 目录管理 Service 实现类
 *
 * @author 狗蛋
 */
@Service
@Validated
public class DirectoryDataServiceImpl implements DirectoryDataService {

    @Resource
    private DirectoryDataMapper directoryDataMapper;
    @Resource
    private ApiDataMapper dataMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDirectoryData(DirectoryDataSaveReqVO createReqVO) {
        // 插入
        DirectoryDataDO directoryData = BeanUtils.toBean(createReqVO, DirectoryDataDO.class);
        directoryDataMapper.insert(directoryData);

        // 插入子表
        createDataList(directoryData.getId(), createReqVO.getDatas());
        // 返回
        return directoryData.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDirectoryData(DirectoryDataSaveReqVO updateReqVO) {
        // 校验存在
        validateDirectoryDataExists(updateReqVO.getId());
        // 更新
        DirectoryDataDO updateObj = BeanUtils.toBean(updateReqVO, DirectoryDataDO.class);
        directoryDataMapper.updateById(updateObj);

        // 更新子表
        updateDataList(updateReqVO.getId(), updateReqVO.getDatas());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDirectoryData(Long id) {
        // 校验存在
        validateDirectoryDataExists(id);
        // 删除
        directoryDataMapper.deleteById(id);

        // 删除子表
        deleteDataByDirId(id);
    }

    private void validateDirectoryDataExists(Long id) {
        if (directoryDataMapper.selectById(id) == null) {
            throw exception(DIRECTORY_DATA_NOT_EXISTS);
        }
    }

    @Override
    public DirectoryDataDO getDirectoryData(Long id) {
        return directoryDataMapper.selectById(id);
    }

    @Override
    public PageResult<DirectoryDataDO> getDirectoryDataPage(DirectoryDataPageReqVO pageReqVO) {
        return directoryDataMapper.selectPage(pageReqVO);
    }

    // ==================== 子表（接口管理） ====================

    @Override
    public List<ApiDataDO> getDataListByDirId(Long dirId) {
        return dataMapper.selectListByDirId(dirId);
    }

    private void createDataList(Long dirId, List<ApiDataDO> list) {
        list.forEach(o -> o.setDirId(dirId));
        dataMapper.insertBatch(list);
    }

    private void updateDataList(Long dirId, List<ApiDataDO> list) {
        deleteDataByDirId(dirId);
		list.forEach(o -> o.setId(null).setUpdater(null).setUpdateTime(null)); // 解决更新情况下：1）id 冲突；2）updateTime 不更新
        createDataList(dirId, list);
    }

    private void deleteDataByDirId(Long dirId) {
        dataMapper.deleteByDirId(dirId);
    }

}