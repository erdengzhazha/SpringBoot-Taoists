package com.ovopark.tao.netty.javanio.buffer;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;

/**
 *  rewind() 倒带
 */
@Slf4j
public class RewindTest {

  static IntBuffer intBuffer = null;

  public static void InitIntBuffer(){
    intBuffer = IntBuffer.allocate(20);
    print();
    for (int i=0;i<5;i++){
      intBuffer.put(i+1);
    }
  }

  public static void flipTest(){
    //翻转缓冲区，从写模式翻转成读模式
    intBuffer.flip();
    for(int i=0;i<5;i++){
      if(i==2){
        intBuffer.mark();
      }
      int j = intBuffer.get();
      log.info("j = "+ j);
    }
    intBuffer.reset();
    print();
  }

  static void print(){
    log.info("-----------------after allocate------------------");
    log.info("position = "+ intBuffer.position());
    log.info("limit = "+ intBuffer.limit());
    log.info("capacity = "+ intBuffer.capacity());
  }


  public static void rewindTest(){
    //倒带
    intBuffer.rewind();
    //输出缓冲区属性
    print();
  }

  public static void main(String[] args) {
    InitIntBuffer();
    flipTest();
    /**
     * （1）position重置为0，所以可以重读缓冲区中的所有数据。
     *
     * （2）limit保持不变，数据量还是一样的，仍然表示能从缓冲区中读取多少个元素。
     *
     * （3）mark标记被清理，表示之前的临时位置不能再用了。”
     *
     */
    rewindTest();
  }


}
