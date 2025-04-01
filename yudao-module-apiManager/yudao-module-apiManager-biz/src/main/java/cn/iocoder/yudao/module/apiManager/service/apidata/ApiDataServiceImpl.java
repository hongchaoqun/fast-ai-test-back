package cn.iocoder.yudao.module.apiManager.service.apidata;

import cn.iocoder.yudao.module.apiManager.controller.apidata.vo.ApiDataPageReqVO;
import cn.iocoder.yudao.module.apiManager.controller.apidata.vo.ApiDataSaveReqVO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.apidata.ApiDataDO;
import cn.iocoder.yudao.module.apiManager.dal.mysql.apidata.ApiDataMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.DATA_NOT_EXISTS;

/**
 * 接口管理 Service 实现类
 *
 * @author 狗蛋
 */
@Service
@Validated
public class ApiDataServiceImpl implements ApiDataService {

    @Resource
    private ApiDataMapper dataMapper;

    @Override
    public Long createData(ApiDataSaveReqVO createReqVO) {
        // 插入
        ApiDataDO data = BeanUtils.toBean(createReqVO, ApiDataDO.class);
        dataMapper.insert(data);
        // 返回
        return data.getId();
    }

    @Override
    public void updateData(ApiDataSaveReqVO updateReqVO) {
        // 校验存在
        validateDataExists(updateReqVO.getId());
        // 更新
        ApiDataDO updateObj = BeanUtils.toBean(updateReqVO, ApiDataDO.class);
        dataMapper.updateById(updateObj);
    }

    @Override
    public void deleteData(Long id) {
        // 校验存在
        validateDataExists(id);
        // 删除
        dataMapper.deleteById(id);
    }

    private void validateDataExists(Long id) {
        if (dataMapper.selectById(id) == null) {
            throw exception(DATA_NOT_EXISTS);
        }
    }

    @Override
    public ApiDataDO getData(Long id) {
        return dataMapper.selectById(id);
    }

    @Override
    public PageResult<ApiDataDO> getDataPage(ApiDataPageReqVO pageReqVO) {
        return dataMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ApiDataDO> getDataListByDirId(Long id) {
        return dataMapper.selectListByDirId(id);
    }
}