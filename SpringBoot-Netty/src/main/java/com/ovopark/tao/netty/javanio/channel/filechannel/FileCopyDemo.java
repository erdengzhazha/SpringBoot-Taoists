package com.ovopark.tao.netty.javanio.channel.filechannel;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.io.*;

/**
 * 使用FileChannel 完成文件复制
 */
@Slf4j
public class FileCopyDemo {

  /**
   * 复制文件
   *
   * @param srcPath  //源文件
   * @param destPath //复制的目标路径
   */
  public static void blockCopyFile(String srcPath, String destPath) {
    InputStream input = null;
    OutputStream output = null;
    File srcFile = new File(srcPath);
    File destFile = new File(destPath);

    try {
      //如果目标文件不存在，则新建
      if (!destFile.exists()) {
        destFile.createNewFile();
      }

      long startTime = System.currentTimeMillis();

      input = new FileInputStream(srcFile);
      output = new FileOutputStream(destFile);
      byte[] buf = new byte[1024];
      int bytesRead;
      while ((bytesRead = input.read(buf)) != -1) {
        output.write(buf, 0, bytesRead);
      }
      output.flush();
      long endTime = System.currentTimeMillis();
      log.debug("IO流复制毫秒数：" + (endTime - startTime));

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        input.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        output.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


  public static void main(String[] args) {
    String srcPath = "/Users/wei/idea_PersonProject/SpringBoot-Taoists/SpringBoot-Netty/src/main/java/com/ovopark/tao/netty/javanio/channel/my.txt";
    String destPath = "/Users/wei/idea_PersonProject/SpringBoot-Taoists/SpringBoot-Netty/src/main/java/com/ovopark/tao/netty/javanio/channel/my02.txt";
    blockCopyFile(srcPath,destPath);
  }
}
