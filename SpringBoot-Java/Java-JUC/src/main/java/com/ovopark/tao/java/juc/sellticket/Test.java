package com.ovopark.tao.java.juc.sellticket;

public class Test {
  public static void main(String[] args) {
    Ticket ticket = new Ticket();
    Thread formatTicket = new InitializeTicket(ticket);//初始化票务信息
    formatTicket.run();
    try {
      formatTicket.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    //新建四个卖票线程
    Thread t1 = new TicketThread(ticket);
    Thread t2 = new TicketThread(ticket);
    Thread t3 = new TicketThread(ticket);
    Thread t4 = new TicketThread(ticket);
    t1.setName("1号售票口");
    t2.setName("2号售票口");
    t3.setName("3号售票口");
    t4.setName("4号售票口");
    t1.start();
    t2.start();
    t3.start();
    t4.start();
  }
}
