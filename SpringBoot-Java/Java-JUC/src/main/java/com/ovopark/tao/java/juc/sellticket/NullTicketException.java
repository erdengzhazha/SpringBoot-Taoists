package com.ovopark.tao.java.juc.sellticket;

public class NullTicketException extends Exception {
  public NullTicketException(){}
  public NullTicketException(String msg){
    super(msg);
  }
}