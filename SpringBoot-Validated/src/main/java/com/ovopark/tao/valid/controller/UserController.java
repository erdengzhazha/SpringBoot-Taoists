package com.ovopark.tao.valid.controller;
import com.ovopark.tao.valid.entity.User;
import com.ovopark.tao.valid.validate.Insert;
import com.ovopark.tao.valid.validate.Update;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@Slf4j
@Validated //此注释是在入参校验时才要加的
public class UserController {

  /**
   * json 请求
   * @param user
   */
  @PostMapping("/user")
  public void add(@RequestBody @Validated(Update.class) User user){
    log.info("user ======{}",user);
  }

  /**
   * 注意：入参校验时，Controller上面要加: @Validated
   * TODO (@RequestParam方式绑定参数)
   * @param id
   * @param name
   */
  @GetMapping("/get1")
  public void get1(@RequestParam("id") @NotNull(message = "id不能为空") Integer id,
                   @RequestParam("name") @NotBlank(message = "name不能为空!" ) @Length(max = 10,message = "用户名不能超过10个") String name)
  {
    log.info("id:{},name:{}",id,name);
  }

  /**
   * TODO (@PathVariable方式绑定参数)
   * @param id
   */
  @GetMapping("/get2/{id}")
  public void get2(@PathVariable("id") @Min(value = 0,message = "id最小为0!") Integer id){
    log.info("id:{}",id);
  }

}
