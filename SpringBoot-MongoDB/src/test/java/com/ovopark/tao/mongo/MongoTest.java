package com.ovopark.tao.mongo;
//import static org.springframework.data.mongodb.core.query.Criteria.where;
//
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
import com.ovopark.boot.dp.plugin.mongodb.MongoDbService;
import com.ovopark.boot.dp.plugin.mongodb.impl.MongoDbServiceImpl;
import com.ovopark.tao.mongo.entity.po.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;


import java.util.List;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Query;


@SpringBootTest(classes = MongoApplication.class)
public class MongoTest {


    private static final Log log = LogFactory.getLog(MongoTest.class);

    @Autowired
    public MongoTemplate mongoTemplate;
    //@Autowired
    //public MongoDbService mongoDbService ;

    public MongoDbServiceImpl mongoDbService = new MongoDbServiceImpl();



    @Test
    public void cc() {


        //mongoTemplate.insert(new Person("TN001","Joe", 34));

        //log.info(mongoTemplate.findOne(new Query(where("name").is("Joe")), Person.class));

        //mongoTemplate.dropCollection("person");
        mongoDbService.save(new Person("TN002","Joe", 34));

        Person tn002 = mongoTemplate.findById("TN002", Person.class);
        System.out.println(tn002.toString());

        Person byId = mongoDbService.findById("TN002", "person", Person.class);
        System.out.println(byId.toString());
    }
}

