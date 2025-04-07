package cn.iocoder.yudao.module.ai.controller.admin.knowledge;

import cn.iocoder.yudao.framework.ai.core.factory.AiModelFactory;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.ai.controller.admin.chat.vo.message.AiChatMessageSendReqVO;
import cn.iocoder.yudao.module.ai.service.model.AiModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.ai.core.enums.AiPlatformEnum.TONG_YI;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * @author hongcq
 * @since 2025/4/3
 */
@Tag(name = "管理后台 - AI 知识库段落")
@RestController
@RequestMapping("/ai/knowledge/test")
@Validated
public class AiKnowledgeTestController {

    @Resource
    private AiModelFactory modelFactory;
    @Resource
    private TokenTextSplitter tokenTextSplitter;

    @Operation(summary = "发送消息（测试）", description = "一次性返回，响应较慢")
    @GetMapping("/")
    @PermitAll
    public CommonResult<Integer> sendMessageTest() {

        // 创建或获取 EmbeddingModel 对象
        EmbeddingModel embeddingModel = modelFactory.getOrCreateEmbeddingModel(
                TONG_YI, "8Ob6i9rHpxkosKXs3A2LkqDAhmuBJXd2DCB043ABD84711ED8FD24A6A8A097D92", "https://dashscope.aliyuncs.com/compatible-mode/v1", "text-embedding-v1");
        VectorStore vectorStore = modelFactory.getOrCreateVectorStore(SimpleVectorStore.class, embeddingModel, null);
        TikaDocumentReader reader = new TikaDocumentReader("./data/test.md");
        List<Document> documents = reader.get();
        List<Document> documentSplitterList = tokenTextSplitter.apply(documents);

        documents.forEach(doc -> doc.getMetadata().put("knowledge", "知识库名称"));

        documentSplitterList.forEach(doc -> doc.getMetadata().put("knowledge", "知识库名称"));

        vectorStore.accept(documentSplitterList);

        return success(1);
    }
}
