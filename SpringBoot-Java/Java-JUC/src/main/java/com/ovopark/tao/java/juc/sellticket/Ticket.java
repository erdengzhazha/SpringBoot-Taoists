package com.ovopark.tao.java.juc.sellticket;

public class Ticket {
  //票务系统初始化票务信息可以容纳的容量
  private String[] str = new String[1000];

  public String[] getStr() {
    return str;
  }

  public void setStr(String[] str) {
    this.str = str;
  }

  //指针指向第一张票
  private int index = 0;

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  //初始化票务信息
  public void initializeTicket(){
    for (int i = 0; i < 200; i++) {
      str[i] = "车票编号:"+(i+1);
    }
  }
}
//卖票接口
interface SaleTicket {
  String saleTicket() throws NullTicketException;
}
