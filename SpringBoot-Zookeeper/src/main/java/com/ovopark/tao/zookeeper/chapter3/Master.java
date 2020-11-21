package com.ovopark.tao.zookeeper.chapter3;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
@Slf4j
public class Master implements Watcher {

    ZooKeeper zk;
    String hostPort;


    Master(String hostPort){
        this.hostPort = hostPort;
    }

    void startZK() throws IOException {
        zk = new ZooKeeper(hostPort , 15000, this);
    }

    void stopZK() throws InterruptedException {
        zk.close();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("-------------------------------");
        log.info(watchedEvent.toString());
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Master m = new Master("");
        m.startZK();
        //wait for a bit
        Thread.sleep(60000);
        //立即断开连接
        m.stopZK();
    }
}
