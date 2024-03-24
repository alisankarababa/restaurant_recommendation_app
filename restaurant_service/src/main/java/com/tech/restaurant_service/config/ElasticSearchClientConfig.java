package com.tech.restaurant_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.tech.restaurant_service.repository")
@ComponentScan(basePackages = "com.tech.restaurant_service")
public class ElasticSearchClientConfig extends ElasticsearchConfiguration {

    @Value( "${elasticsearch.hostAndPort}" )
    private String elasticSearchHostAndPort;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(elasticSearchHostAndPort)
                .build();
    }
}
