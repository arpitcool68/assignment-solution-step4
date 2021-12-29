//package com.stackroute.newz.config;
//
//import java.util.Collection;
//import java.util.Collections;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//
//@Configuration
//public class MongoClientConfig extends AbstractMongoClientConfiguration{
//	
//	@Override
//    protected String getDatabaseName() {
//        return "news";
//    }
// 
//    @Override
//    public MongoClient mongoClient() {
//        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/news");
//        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//            .applyConnectionString(connectionString)
//            .build();
//        
//        return MongoClients.create(mongoClientSettings);
//    }
// 
//    @Override
//    public Collection getMappingBasePackages() {
//        return Collections.singleton("com.stackroute.newz.repository");
//    }
//}
