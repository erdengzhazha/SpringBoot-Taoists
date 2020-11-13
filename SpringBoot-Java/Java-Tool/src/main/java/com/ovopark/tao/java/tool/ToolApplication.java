package com.ovopark.tao.java.tool;

import com.ovopark.tao.java.tool.jvm.FieldDemo;
import com.ovopark.tao.java.tool.jvm.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component("com.ovopark.tao.java.tool")
public class ToolApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ToolApplication.class, args);
//        FieldDemo user =(FieldDemo) run.getBean("fieldDemo");
//        System.out.println(user.getUser());
//        user.ate();
        FieldDemo user =(FieldDemo) run.getBean("fieldDemo");
        user.ate();

    }
}
