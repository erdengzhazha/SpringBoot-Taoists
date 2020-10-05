# SpringBoot集成validated
### 目的:在controller层便携的参数校验
本项目SpringBoot v 2.2.10  
注意: 从SpringBoot v 2.3 之前都是自带validated jar 包  
本项目使用了LomBok插件，安装步骤1.idea下载插件 ，2.倒入LomBok的jar包
# 校验核心步骤
1.创建POJO对象  
2.给POJO属性加上校验注解  
```java
@NotBlank(message = "用户名不能为空!" ,groups = {Insert.class,Update.class})
@Length(max = 20, min = 3, message = "用户名必须在 3～20 字符之间!")
private String username;
```
3.创建校验组接口（Insert,Update,Get,Delete) 
```java
public interface Insert extends Default {
}
```
4.在Controller参数中使用@Validate(Insert.class，***)  
```java
@PostMapping("/user")
  public void add(@RequestBody @Validated(Update.class) User user){
  
  }
```
5.创建全局异常处理类，接收请求参数异常
```java
/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * post方式提交json数据,参数校验失败后,会抛出一个MethodArgumentNotValidException
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    // 获取所有的错误
    // List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
    // 获取错误提示
    // System.out.println(fieldErrors.get(0).getDefaultMessage());
    // 获取错误字段
    // System.out.println(fieldErrors.get(0).getField());

    // 将所有的错误提示使用";"拼接起来并返回
    StringJoiner sj = new StringJoiner(";");
    e.getBindingResult().getFieldErrors().forEach(x -> sj.add(x.getDefaultMessage()));

    // 此处通常定义一个全局相应对象返回
    Map<String, Object> map = new HashMap<String, Object>();
    // 此处状态码可以通过枚举或者常量定义
    map.put("code", 1001);
    map.put("msg", sj.toString());
    return map;
  }

  /**
   * get方式提交参数,参数校验失败后,会抛出一个ConstraintViolationException
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public Map<String, Object> handleConstraintViolationException(ConstraintViolationException e) {
    StringJoiner sj = new StringJoiner(";");
    e.getConstraintViolations().forEach(x -> sj.add(x.getMessage()));

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("code", 1001);
    map.put("msg", sj.toString());
    return map;
  }
}
```

