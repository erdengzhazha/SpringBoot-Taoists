package com.ovopark.tao.java.tool.jvm;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 属性测试
 */
@Data
@Component
public class FieldDemo {


   Class c  ;

   public FieldDemo(@Autowired User user){
     c = user.getClass();
   }



    public void ate(){
      System.out.println(c.getName());
    }
}
