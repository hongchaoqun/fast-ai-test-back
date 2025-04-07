//package cn.iocoder.yudao.module.apiManager.config;
//
//import org.springframework.ai.ollama.api.OllamaApi;
//import org.springframework.ai.transformer.splitter.TokenTextSplitter;
//import org.springframework.ai.vectorstore.SimpleVectorStore;
//import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
///**
// * @author hongcq
// * @since 2025/4/3
// */
//@Configuration
//public class VectorStoreConfig {
//
//    @Bean(name = "slaveDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.slave")
//    public DataSource slaveDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "slaveJdbcTemplate")
//    public JdbcTemplate slaveJdbcTemplate(@Qualifier("slaveDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    @Bean
//    public TokenTextSplitter tokenTextSplitter() {
//        return new TokenTextSplitter();
//    }
//
//    @Bean
//    public OllamaApi ollamaApi(@Value("${spring.ai.ollama.base-url}") String baseUrl) {
//        return new OllamaApi(baseUrl);
//    }
//
//    @Bean
//    public SimpleVectorStore vectorStore(OllamaApi ollamaApi) {
//        OllamaEmbeddingClient embeddingClient = new OllamaEmbeddingClient(ollamaApi);
//        embeddingClient.withDefaultOptions(OllamaOptions.create().withModel("nomic-embed-text"));
//        return new SimpleVectorStore(embeddingClient);
//    }
//
//    @Bean
//    public PgVectorStore pgVectorStore(OllamaApi ollamaApi, JdbcTemplate slaveJdbcTemplate) {
//        OllamaEmbeddingClient embeddingClient = new OllamaEmbeddingClient(ollamaApi);
//        embeddingClient.withDefaultOptions(OllamaOptions.create().withModel("nomic-embed-text"));
//        return new PgVectorStore(slaveJdbcTemplate, embeddingClient);
//    }
//
//}
