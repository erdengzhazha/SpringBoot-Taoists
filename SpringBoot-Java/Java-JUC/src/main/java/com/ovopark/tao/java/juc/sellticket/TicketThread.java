package com.ovopark.tao.java.juc.sellticket;

public class TicketThread extends Thread implements SaleTicket{
  private Ticket tic;
  public TicketThread(Ticket tic) {
    this.tic = tic;
  }
  @Override
  public void run() {
    for (int i = 0; i < 60; i++) {
      try {
        String s = this.saleTicket();
        System.out.println(this.getName()+"成功购票,购得车票--->"+s);
      } catch (NullTicketException e) {
        System.out.println(e);
      }
    }
  }

  @Override
  public String saleTicket() throws NullTicketException {
    synchronized (tic){
      if(null != tic.getStr()[tic.getIndex()]){
        String s =tic.getStr()[tic.getIndex()];
        //给ticket指针向后移位
        tic.setIndex(1+tic.getIndex());
        return s;
      }else {
        throw new NullTicketException("车票已售罄");
      }
    }
  }
}