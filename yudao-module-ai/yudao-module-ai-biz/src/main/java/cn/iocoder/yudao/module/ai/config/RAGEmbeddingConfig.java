package cn.iocoder.yudao.module.ai.config;

import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hongcq
 * @since 2025/4/3
 */
@Configuration
public class RAGEmbeddingConfig {

    @Bean
    public TokenTextSplitter tokenTextSplitter() {
        return new TokenTextSplitter();
    }
}
