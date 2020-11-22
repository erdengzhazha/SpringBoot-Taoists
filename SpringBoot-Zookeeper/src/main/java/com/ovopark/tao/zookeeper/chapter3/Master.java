package com.ovopark.tao.zookeeper.chapter3;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import javax.jnlp.ClipboardService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class Master implements Watcher {

    ZooKeeper zk;
    String hostPort;
    Random random = new Random();
    String serverId = Integer.toHexString(random.nextInt());
    List<ACL> aclList = new ArrayList<>(1);
    static  boolean isLeader = false;

    Master(String hostPort){
        this.hostPort = hostPort;
    }

    void startZK() throws IOException {
        zk = new ZooKeeper(hostPort , 15000, this);
    }

    void stopZK() throws InterruptedException {
        zk.close();
    }

    //return true if there is a Master
    boolean checkMaster() {
        while(true) {
            try {
                Stat stat = new Stat();
                byte[] data = zk.getData("/master", false, stat);
                log.info("data = "+new String(data));
                isLeader = new String(data).equals(serverId);
                log.info("isLeader???? = "+isLeader);
                return  true ;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        }
    }

    void runForMaster() throws InterruptedException {
        while (true) {
            log.info("Running for master");
            try {
                zk.create("/master",
                        serverId.getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            } catch (KeeperException e) {
                e.printStackTrace();
                break;
            } finally {
                if(checkMaster()) {
                    break;
                }
            }

        }

    }





    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("-------------------------------");
        log.info(watchedEvent.toString());
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Master m = new Master("47.96.11.161:2181");
        m.startZK();
        m.runForMaster();
        if(isLeader){
            log.info("I'm the leader");
            //wait for a bit
            Thread.sleep(60000);
        } else {
            log.info("Someone else is the leader");
        }

        //立即断开连接
        m.stopZK();
    }
}
