package com.hendisantika.springelasticsearchsample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-elasticsearch-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/06/24
 * Time: 22.31
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.hendisantika.springelasticsearchsample.domain")
public class ElasticConfig {
    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.username}")
    private String username;

    @Value("${elasticsearch.password}")
    private String password;

//    @Bean
//    public RestHighLevelClient client() {
//        ClientConfiguration clientConfiguration
//                = ClientConfiguration.builder()
//                .connectedTo(host)
//                .withBasicAuth(username, password) // put your credentials
//                .build();
//        return RestClient.create(clientConfiguration).rest();
//        return RestClient.builder(clientConfiguration).rest();
//    }

//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() {
//        return new ElasticsearchRestTemplate(client());
//
//        final CredentialsProvider credentialsProvider =
//                new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY,
//                new UsernamePasswordCredentials("user", "test-user-password"));
//
//        RestClientBuilder builder = RestClient.builder(
//                        new HttpHost("localhost", 9200))
//                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//                    @Override
//                    public HttpAsyncClientBuilder customizeHttpClient(
//                            HttpAsyncClientBuilder httpClientBuilder) {
//                        return httpClientBuilder
//                                .setDefaultCredentialsProvider(credentialsProvider);
//                    }
//                });
//
//    }


}
