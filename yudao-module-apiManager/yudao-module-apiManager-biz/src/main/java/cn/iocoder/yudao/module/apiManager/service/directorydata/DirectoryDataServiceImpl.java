package cn.iocoder.yudao.module.apiManager.service.directorydata;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.iocoder.yudao.module.apiManager.controller.apifile.vo.ApiFileSaveReqVO;
import cn.iocoder.yudao.module.apiManager.controller.directorydata.vo.DirectoryDataPageReqVO;
import cn.iocoder.yudao.module.apiManager.controller.directorydata.vo.DirectoryDataSaveReqVO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.apidata.ApiDataDO;
import cn.iocoder.yudao.module.apiManager.dal.dataobject.directorydata.DirectoryDataDO;
import cn.iocoder.yudao.module.apiManager.dal.mysql.apidata.ApiDataMapper;
import cn.iocoder.yudao.module.apiManager.dal.mysql.directorydata.DirectoryDataMapper;
import cn.iocoder.yudao.module.apiManager.service.apifile.ApiFileService;
import cn.iocoder.yudao.module.infra.api.file.FileApi;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.DIRECTORY_DATA_NOT_EXISTS;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    @Resource
    private ApiFileService apiFileService;
    @Resource
    private FileApi fileApi;
    @Resource
    private ApiDataMapper apiDataMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDirectoryData(DirectoryDataSaveReqVO createReqVO) {
        // 插入
        DirectoryDataDO directoryData = BeanUtils.toBean(createReqVO, DirectoryDataDO.class);
        directoryDataMapper.insert(directoryData);

        // 插入子表
        createDataList(directoryData.getId(), createReqVO);
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

    /**
     * 解析文件和新增文件表
     * @param dirId
     * @param createReqVO
     */
    private void createDataList(Long dirId, DirectoryDataSaveReqVO  createReqVO) {
        if(createReqVO.getFileUrl() == null){
            return;
        }
        // 获取请求的路径
        String path = StrUtil.subAfter(createReqVO.getFileUrl(), "/get/", false);
        if (StrUtil.isEmpty(path)) {
            throw new IllegalArgumentException("结尾的 path 路径必须传递");
        }
        // 解码，解决中文路径的问题 https://gitee.com/zhijiantianya/ruoyi-vue-pro/pulls/807/
        path = URLUtil.decode(path);

        ApiFileSaveReqVO data = new ApiFileSaveReqVO();
        data.setDirId(dirId);
        data.setFileName(path);
        data.setUrl(createReqVO.getFileUrl());
        apiFileService.createFile(data);

        // 读取内容
        makeFile(path, dirId);
    }


    /**
     * 解析文件添加API记录
     */
    private void makeFile(String path, Long dirId){
        try {
            // 读取内容
            byte[] content = fileApi.getFileContent(4l,path);
            InputStream inputStream = new ByteArrayInputStream(content);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(inputStream);
            // 处理JsonNode数据...
            List<ApiDataDO> apiDataList = parseApiData(rootNode);

            // 打印验证
            apiDataList.forEach(api -> {
                System.out.printf(
                        "接口名称: %-20s 方法: %-4s 路径: %s%n",
                        api.getName(), api.getMethod(), api.getPath()
                );
                api.setDirId(dirId);
            });
            apiDataMapper.insertBatch(apiDataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析文件到对象
     * @param rootNode
     * @return
     */
    public static List<ApiDataDO> parseApiData(JsonNode rootNode) {
        List<ApiDataDO> result = new ArrayList<>();
        JsonNode pathsNode = rootNode.get("paths");

        if (pathsNode == null || !pathsNode.isObject()) return result;

        pathsNode.fields().forEachRemaining(pathEntry -> {
            String path = pathEntry.getKey(); // 例如 "/admin-api/.../update-avatar"
            JsonNode methodsNode = pathEntry.getValue();

            methodsNode.fields().forEachRemaining(methodEntry -> {
                String httpMethod = methodEntry.getKey().toUpperCase(); // 如 "PUT", "POST"
                JsonNode methodDetails = methodEntry.getValue();

                // 构建对象
                ApiDataDO apiData = new ApiDataDO();
                apiData.setPath(path);
                apiData.setMethod(httpMethod);

                // 优先取 summary，没有则用 operationId
                if (methodDetails.has("summary")) {
                    apiData.setName(methodDetails.get("summary").asText());
                } else if (methodDetails.has("operationId")) {
                    apiData.setName(methodDetails.get("operationId").asText());
                } else {
                    apiData.setName("未命名接口");
                }

                result.add(apiData);
            });
        });

        return result;
    }

    private void updateDataList(Long dirId, List<ApiDataDO> list) {
        deleteDataByDirId(dirId);
		list.forEach(o -> o.setId(null).setUpdater(null).setUpdateTime(null)); // 解决更新情况下：1）id 冲突；2）updateTime 不更新
        //createDataList(dirId, list);
    }

    private void deleteDataByDirId(Long dirId) {
        dataMapper.deleteByDirId(dirId);
    }

}