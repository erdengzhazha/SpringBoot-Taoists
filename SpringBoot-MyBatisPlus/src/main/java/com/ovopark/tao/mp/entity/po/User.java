package com.ovopark.tao.mp.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("my_user")
public class User {
  /** 序号*/
  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;
  /** 姓名*/
  private String name;
}
