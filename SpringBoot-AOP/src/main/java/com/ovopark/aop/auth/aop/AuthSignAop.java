package com.ovopark.aop.auth.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect //切面注解
@Component
public class AuthSignAop {

  public AuthSignAop() {
  }

  /**
   * 通过@Pointcut注解声明频繁使用的切点表达式
   */
  @Pointcut("@annotation(com.ovopark.aop.auth.annotation.AuthSign)")
  public void checkSign(){

  }

  /**
   * 每个切点前要做的事情
   * @param joinPoint
   */
  @Before("checkSign()")
  public void doBefore(JoinPoint joinPoint) {
    System.out.println("doBefore ing  ....");
  }

  @Around("checkSign()")
  public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    //获得方法的参数
    Object[] args = proceedingJoinPoint.getArgs();
    System.out.println("-------- 打印参数 start --------");
    Arrays.stream(args).forEach(a -> System.out.println(a));
    System.out.println("-------- 打印参数 end --------");
    //得到其方法签名
    MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
    //获取方法参数类型数组
    Class[] parameterTypes = methodSignature.getParameterTypes();
    String[] parameterNames = methodSignature.getParameterNames();
    //------------------------------------------------------------------------
    System.out.println("-------- 打印方法参数类型 start --------");
    for(Class c :parameterTypes){
      System.out.println(c.getName());
    }
    if(String.class.isAssignableFrom(parameterTypes[0])){
      args[0] = "重新赋值了";
    }
    System.out.println("-------- 打印方法参数类型 end --------");
    //------------------------------------------------------------------------
    System.out.println("-------- 打印方法参数名字 start --------");
    Arrays.stream(parameterNames).forEach(b -> System.out.println(b));
    System.out.println("-------- 打印方法参数名字 end --------");




    //方法最最最后输出
    Object result = proceedingJoinPoint.proceed(args);
    System.out.println("result输出 ==="+ result);

    System.out.println("doAround() ing ......");
    return result;
  }

  /**
   * 每个切点之后做的事情
   */
  @After("checkSign()")
  public void doAfter(){
    System.out.println("doAfter ing ...");
  }

  /**
   * 返回之后
   */
  @AfterReturning("checkSign()")
  public void doAfterReturning() {
    // ...
    System.out.println("doAfterReturning ing .....");
  }
}
