package com.ovopark.tao.netty.javanio.channel.filechannel;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件通道 测试
 * @apiNote “FileChannel是专门操作文件的通道。通过FileChannel，
 *          既可以从一个文件中读取数据，也可以将数据写入到文件中。
 *          特别申明一下，FileChannel为阻塞模式，不能设置为非阻塞模式。”
 */
@Slf4j
public class FileChannelDemo {

  static String srcFile = "/Users/wei/idea_PersonProject/SpringBoot-Taoists/SpringBoot-Netty/src/main/java/com/ovopark/tao/netty/javanio/channel/my.txt";
  /**
   * 获取一个FileChannel
   * （1）可以通过文件的输入流、输出流获取FileChannel文件通道, 示例如下：
   *  (2) RandomAccessFile 文件随机访问类，获取FileChannel文件通道：
   */
  public static FileChannel initFileChannel(String srcFile,Integer type){
    FileChannel fileChannel = null;
    switch (type){
      case 1:
        //创建一条文件输入流
        FileInputStream fis;
      {
        try {
          fis = new FileInputStream(srcFile);
          //获取文件流的通道
          fileChannel = fis.getChannel();
          //创建一条文件输出流
          FileOutputStream fos = new FileOutputStream(srcFile);
          //获取文件流的通道
          fileChannel = fos.getChannel();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      }
      case 2:
        try {
          RandomAccessFile randomAccessFile = new RandomAccessFile(new File(srcFile),"rw");
          fileChannel = randomAccessFile.getChannel();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      default:
    }
    return fileChannel;
  }

  /**
   * “在大部分应用场景，从通道读取数据都会调用通道的int read（ByteBufferbuf）方法
   * ，它从通道读取到数据写入到ByteBuffer缓冲区，并且返回读取到的数据量。”
   * @apiNote
   * “虽然对于通道来说是读取数据，但是对于ByteBuffer缓冲区来说是写入数据
   * ，这时，ByteBuffer缓冲区处于写入模式”
   *
   * 摘录来自: 尼恩. “Netty、Redis、Zookeeper高并发实战。” Apple Books.
   */
  public static void readFileChannel(){
    //获取通道
    FileChannel fileChannel = initFileChannel(srcFile, 2);
    //获取一个字节缓冲区
    ByteBuffer buf = ByteBuffer.allocate(50);
    //buf.flip();
    try {
      int length = -1;
//      while((fileChannel.read(buf))!=-1){
//        buf.clear();
//        int read = fileChannel.read(buf);
//        System.out.println(read);
//
//      }
      //将这个
      while (true){
        int read = fileChannel.read(buf);
        System.out.println(read);
        if(read ==length){
          break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        fileChannel.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * <p>“写入数据到通道，在大部分应用场景，都会调用通道的int write（ByteBufferbuf）方法。
   * 此方法的参数——ByteBuffer缓冲区，是数据的来源。write方法的作用，
   * 是从ByteBuffer缓冲区中读取数据，然后写入到通道自身，而返回值是写入成功的字节数。”<p/>
   * @apiNote
   */
  public static void writeFileChannel(){
    //获取通道
    FileChannel fileChannel = initFileChannel(srcFile, 2);
    ByteBuffer buf = ByteBuffer.allocate(20);
    IntBuffer intBuffer = IntBuffer.allocate(20);
    print(intBuffer);
    buf.put(new Byte("65"));
    buf.put(new Byte("66"));
    buf.put(new Byte("67"));
    buf.put(new Byte("68"));
    buf.flip(); //变为读模式
    //调用write方法，将buf的数据写入通道.
    while (true){
      try {
        fileChannel.force(true);
        int write = fileChannel.write(buf);
        System.out.println(write);
        if(write == 0){
          break;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  static void print(Buffer o){
    log.info("-----------------after allocate------------------");
    log.info("position = "+ o.position());
    log.info("limit = "+ o.limit());
    log.info("capacity = "+ o.capacity());
  }
  public static void main(String[] args) {
    //readFileChannel();
    writeFileChannel();
  }


}
