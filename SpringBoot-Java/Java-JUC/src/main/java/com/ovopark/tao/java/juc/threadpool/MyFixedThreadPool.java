package com.ovopark.tao.java.juc.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyFixedThreadPool {

  //全局变量
  public int j=0;
  public static List<Integer> list2;
  void doSomething(){
    System.out.println(Thread.currentThread().getName()+"is doing ... and i= "+j +"list2 ="+list2.get(j));
    j++;
  }

  void doSomething2(String name){
    System.out.println(name+"is doing ... and j= "+j);
    j++;
  }



  public static void main(String[] args) {

    List<Integer> list = new ArrayList<>(1000);
    for(int i =0 ;i<100;i++){
      list.add(i,i);
    }
    list2=list;

    //创建一个线程池 ,用 10个线程处理
//    ExecutorService executorService = Executors.newFixedThreadPool(10);
//    executorService.execute(new Runnable() {
//      @Override
//      public void run() {
//        while (j<100){
//          myFixedThreadPool.doSomething();
//        }
//      }
//    });
    Thread thread = new myThread("线程1");
    Thread thread1 = new myThread("线程2");
    Thread thread2 = new myThread("线程3");
    Thread thread3 = new myThread("线程4");
    thread.start();
    thread1.start();
    thread2.start();
    thread3.start();
  }


  public static class myThread extends Thread{
    MyFixedThreadPool myFixedThreadPool = new MyFixedThreadPool();
    private String name;
    public myThread(String name){
        this.name = name;
    }
    @Override
    public void run() {
//      while (j<10000000){
//        myFixedThreadPool.doSomething2(name);
//      }
      for(int i=0;i<list2.size();i++){
        System.out.println(name);
        System.out.println("list2.get ="+list2.get(i));
        //myFixedThreadPool.doSomething2(name);
        System.out.println("-------------------");
      }
    }
  }
}
