package com.ovopark.tao.java.juc.threadpool;


import org.junit.jupiter.api.Test;

public class TestThreadPool {


  @Test
  public void testMyThreadPool(){
  // 创建3个线程的线程池
      ThreadPool t = ThreadPool.getThreadPool(3);
      t.execute(new Runnable[] { new Task("线程 1"), new Task("线程 2"), new Task("线程 3") });
      //t.execute(new Runnable[] { new Task("线程 4"), new Task("线程 5"), new Task("线程 6") });
      System.out.println(t);
      t.destroy();// 所有线程都执行完成才destory
      System.out.println(t);

  }
  // 任务类
  static class Task implements Runnable {
    //private static volatile int i = 1;
    public String name;

    public Task(String name){
      this.name = name;
    }

    @Override
    public void run() {// 执行任务
      for (int i=0;i<100;i++){
        System.out.println("任务 " + i + " 完成");
      }
    }
  }
}
