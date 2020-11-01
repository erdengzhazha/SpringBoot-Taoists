package com.ovopark.tao.java.tool.aop.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void addUser(){
        System.out.printf("加了一个User");
    }
}
