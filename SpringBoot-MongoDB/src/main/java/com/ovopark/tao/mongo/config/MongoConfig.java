//package com.ovopark.tao.mongo.config;
//
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import com.mongodb.client.MongoClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
//
//import static java.util.Collections.singletonList;
//
///**
// * <p>MongoDB的配置类</p>
// * @author Xiu_Er 13813641925@163.com
// * @Date 2020年10月18号 下午 14:46
// */
//@Configuration
//public class MongoConfig extends AbstractMongoConfiguration {
//
//
//
//    @Override
//    protected String getDatabaseName() {
//        return "cw_doc";
//    }
//
//
//    @Override
//    @Bean
//    public MongoClient mongoClient() {
//        return new MongoClient(singletonList(new ServerAddress("192.168.48.129", 27017)),
//                singletonList(MongoCredential.createCredential("admin", "cw_doc", "123456".toCharArray())));
//    }
//}
