package com.ovopark.tao.java.tool.demo.aop;

import com.ovopark.tao.java.tool.ToolApplication;
import com.ovopark.tao.java.tool.aop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ToolApplication.class)
public class AopTest {

    @Autowired
    UserService userService;

    @Test
    public void testPkTypeAspect(){
        userService.addUser();
    }
}
