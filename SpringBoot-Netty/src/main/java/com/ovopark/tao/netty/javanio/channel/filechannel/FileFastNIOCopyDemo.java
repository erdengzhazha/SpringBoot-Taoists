package com.ovopark.tao.netty.javanio.channel.filechannel;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

@Slf4j
public class FileFastNIOCopyDemo {

  /**
   * 复制文件
   *
   * @param srcPath
   * @param destPath
   */
  public static void nioCopyFile(String srcPath, String destPath) {

    File srcFile = new File(srcPath);
    File destFile = new File(destPath);

    try {
      //如果目标文件不存在，则新建
      if (!destFile.exists()) {
        destFile.createNewFile();
      }


      long startTime = System.currentTimeMillis();

      FileInputStream fis = null;
      FileOutputStream fos = null;
      FileChannel inChannel = null;
      FileChannel outChannel = null;
      try {
        fis = new FileInputStream(srcFile);
        fos = new FileOutputStream(destFile);
        inChannel = fis.getChannel();
        outChannel = fos.getChannel();
        long size = inChannel.size();
        long pos = 0;
        long count = 0;
        while (pos < size) {
          //每次复制最多1024个字节，没有就复制剩余的
          count = size - pos > 1024 ? 1024 : size - pos;
          //复制内存,偏移量pos + count长度
          pos += outChannel.transferFrom(inChannel, pos, count);
        }

        //强制刷新磁盘
        outChannel.force(true);
      } finally {
        outChannel.close();
        fos.close();
        inChannel.close();
        fis.close();
      }
      long endTime = System.currentTimeMillis();
      log.debug("base 复制毫秒数：" + (endTime - startTime));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    String srcPath = "/Users/wei/idea_PersonProject/SpringBoot-Taoists/SpringBoot-Netty/src/main/java/com/ovopark/tao/netty/javanio/channel/my.txt";
    String destPath = "/Users/wei/idea_PersonProject/SpringBoot-Taoists/SpringBoot-Netty/src/main/java/com/ovopark/tao/netty/javanio/channel/my04.txt";
    nioCopyFile(srcPath,destPath);
  }
}
