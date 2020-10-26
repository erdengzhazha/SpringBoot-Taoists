package com.ovopark.tao.cloud.rabbit.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Deprecated
@Data
public class ManagementConst {

  public static class QueueArgsBuild {
    private static Map<String,String> map = new HashMap<>();

    public void addDead(){

    }
  }

}
