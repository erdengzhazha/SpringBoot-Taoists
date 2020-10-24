package com.ovopark.tao.java.juc.threadlocal;

/**
 * <p> 测试ThreadLocal特性 </p>
 * @author Xiu_Er 13813641925@163.com
 */
public class ThreadLocalTest {
  private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

  private static void testThreadLocal() {
    threadLocal.set("a");
    System.out.println(threadLocal.get());
    Thread t = new Thread() {
      @Override
      public void run() {
        super.run();
        threadLocal.set("droidyue.com");
        System.out.println(threadLocal.get());
      }
    };
    t.start();
  }

  public static void main(String[] args) {
    testThreadLocal();
  }
}
