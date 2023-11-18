package com.example.config2mongodb.mongoconfig;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.config2mongodb", mongoTemplateRef = "firstMongoTemplate")
public class FirstMongoConfig {
    @Value("${spring.data.mongodb.first.uri}")
    private String databaseConnection;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;


    @Bean(name = "firstMongoTemplate")
    @Primary
    public MongoTemplate firstMongoTemplate(MongoClient client) {
        return new MongoTemplate(firstMongoClient(), "dev_db");
    }

    @Bean
    @Primary
    public MongoClient firstMongoClient() {
        MongoCredential credential = MongoCredential.createCredential(username,
                "dev_db", password.toCharArray());
        MongoClientSettings settings = MongoClientSettings.builder().credential(credential).
                applyConnectionString(new ConnectionString(databaseConnection)).build();
        return MongoClients.create(settings);
    }
}

