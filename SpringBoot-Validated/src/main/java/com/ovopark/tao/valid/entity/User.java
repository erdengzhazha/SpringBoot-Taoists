package com.ovopark.tao.valid.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  /**
   * 用户id
   */
  @NotNull(message = "用户id不能为空!")
  private Long userId;

  /**
   * 用户名
   */
  @NotBlank(message = "用户名不能为空!")
  @Length(max = 20, min = 3, message = "用户名必须在 3～20 字符之间!")
  private String username;

  /**
   * 密码
   */
  @NotBlank(message = "密码不能为空！")
  private String password;

  /**
   * 手机号
   */
  @NotBlank(message = "手机号不能为空!")
  @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式错误!")
  private String mobile;
  /**
   * 性别
   */
  @NotNull(message = "{sex.NotNull}")
  private Integer sex;

  /**
   * 年龄
   */
  @NotNull(message = "年龄不能为空！")
  @Min(value = 1, message = "年龄最小为1岁！")
  @Max(value = 140, message = "年龄最大为140岁!")
  private Integer age;

  /**
   * 邮箱
   */
  @NotBlank(message = "邮箱不能为空！")
  @Email(message = "邮箱格式错误！")
  private String email;

}
