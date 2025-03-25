package cn.iocoder.yudao.module.system.service.apifile;

import cn.iocoder.yudao.module.system.controller.admin.apifile.vo.ApiFilePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.apifile.vo.ApiFileSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.apifile.ApiFileDO;
import cn.iocoder.yudao.module.system.dal.mysql.apifile.ApiFileMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.infra.enums.ErrorCodeConstants.FILE_NOT_EXISTS;

/**
 * 接口文件 Service 实现类
 *
 * @author 狗蛋
 */
@Service
@Validated
public class ApiFileServiceImpl implements ApiFileService {

    @Resource
    private ApiFileMapper fileMapper;

    @Override
    public Long createFile(ApiFileSaveReqVO createReqVO) {
        // 插入
        ApiFileDO file = BeanUtils.toBean(createReqVO, ApiFileDO.class);
        fileMapper.insert(file);
        // 返回
        return file.getId();
    }

    @Override
    public void updateFile(ApiFileSaveReqVO updateReqVO) {
        // 校验存在
        validateFileExists(updateReqVO.getId());
        // 更新
        ApiFileDO updateObj = BeanUtils.toBean(updateReqVO, ApiFileDO.class);
        fileMapper.updateById(updateObj);
    }

    @Override
    public void deleteFile(Long id) {
        // 校验存在
        validateFileExists(id);
        // 删除
        fileMapper.deleteById(id);
    }

    private void validateFileExists(Long id) {
        if (fileMapper.selectById(id) == null) {
            throw exception(FILE_NOT_EXISTS);
        }
    }

    @Override
    public ApiFileDO getFile(Long id) {
        return fileMapper.selectById(id);
    }

    @Override
    public PageResult<ApiFileDO> getFilePage(ApiFilePageReqVO pageReqVO) {
        return fileMapper.selectPage(pageReqVO);
    }

}