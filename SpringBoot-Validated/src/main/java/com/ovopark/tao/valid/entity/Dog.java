package com.ovopark.tao.valid.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Dog 类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dog implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotBlank(message = "{dogName.NotBlank}")
  private String name;


}
