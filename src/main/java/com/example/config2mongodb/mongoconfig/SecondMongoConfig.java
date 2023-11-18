package com.example.config2mongodb.mongoconfig;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.config2mongodb", mongoTemplateRef = "secondMongoTemplate")
public class SecondMongoConfig {
    @Value("${spring.data.mongodb.second.uri}")
    private String databaseConnection2;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Bean(name = "secondMongoTemplate")
    public MongoTemplate secondMongoTemplate() {
        return new MongoTemplate(secondMongoClient(),
                "dev_db_2");
    }

    @Bean
    public MongoClient secondMongoClient() {
        MongoCredential credential = MongoCredential.createCredential(username,
                "dev_db_2", password.toCharArray());
        MongoClientSettings settings = MongoClientSettings.builder().credential(credential).
                applyConnectionString(new ConnectionString(databaseConnection2)).build();
        return MongoClients.create(settings);
    }
}
