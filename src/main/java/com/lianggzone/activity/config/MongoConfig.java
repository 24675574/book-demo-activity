package com.lianggzone.activity.config;

import com.google.common.collect.ImmutableList;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.annotations.Immutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;

/**
 * <h3>概要:</h3><p>MongoConfig</p>
 * <h3>功能:</h3><p>MongoConfig</p>
 * <h3>履历:</h3>
 * <li>2018年3月31日</li>
 * @author 粱桂钊
 * @since 0.1
 */
@Configuration
@EnableMongoRepositories
@PropertySource(value = {"classpath:mongodb.properties"})
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment env;

    private String dbName = "book-demo-activity";
 
    private static final String MONGO_BASE_PACKAGE = "com.lianggzone.core.entity";

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    public MongoClient mongoClient() {
        MongoClient mongoClient = new MongoClient(
                env.getProperty("source.mongoHost").trim(),
                Integer.valueOf(env.getProperty("source.mongoPort").trim()));
        return mongoClient;
    }
 
    @Override
    protected Collection<String> getMappingBasePackages() {
        return ImmutableList.of(MONGO_BASE_PACKAGE);
    }

    @Override
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }
}
