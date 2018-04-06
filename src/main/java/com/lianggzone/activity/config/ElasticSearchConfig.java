package com.lianggzone.activity.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.PostConstruct;

/**
 * <h3>概要:</h3><p>ElasticSearchConfig</p>
 * <h3>功能:</h3><p>ElasticSearchConfig</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Configuration
@EnableElasticsearchRepositories("com.lianggzone.activity")
public class ElasticSearchConfig {

    @PostConstruct
    private void init() {
        System.out.println("es.set.netty.runtime.available.processors set!");
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
}
