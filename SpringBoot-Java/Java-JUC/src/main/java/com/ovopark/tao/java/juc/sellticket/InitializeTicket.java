package com.ovopark.tao.java.juc.sellticket;

public class InitializeTicket extends Thread {
  private Ticket ticket;

  public InitializeTicket(Ticket ticket) {
    this.ticket = ticket;
  }

  @Override
  public void run() {
    ticket.initializeTicket();
  }
}
