package com.ovopark.tao.netty.javanio.buffer;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;

/**
 * 测试 buffer 从写到读的反转
 * 如果从读到写转换， 需要调用 clear() 或者 compact()
 */
@Slf4j
public class FlipTest {


    static IntBuffer intBuffer = null;

    public static void initIntBuffer(){
        intBuffer = IntBuffer.allocate(20);
        print();
        for (int i=0;i<5;i++){
            intBuffer.put(i+1);
        }
    }

    public static void flipTest(){
        //翻转缓冲区，从写模式翻转成读模式
        intBuffer.flip();
        print();
    }

    static void print(){
        log.info("-----------------after allocate------------------");
        log.info("position = "+ intBuffer.position());
        log.info("limit = "+ intBuffer.limit());
        log.info("capacity = "+ intBuffer.capacity());
    }
    public static void main(String[] args) {
        initIntBuffer();
        flipTest();
        int i = intBuffer.get();
        i = intBuffer.get();
        i = intBuffer.get();
        i = intBuffer.get(3);
        System.out.println("拿出来的数据"+ i);
        print();
        System.out.println("------------------转换写模式--------------");
        intBuffer.clear();
        print();
        /**
         * 调用flip方法后，
         * 之前写入模式下的position值5，
         * 变成了可读上限limit值5；
         * 而新的读取模式下的position值，
         * 简单粗暴地变成了0，表示从头开始读取。
         */

        /**
         * 对flip()方法的从写入到读取转换的规则，详细的介绍如下：
         * 首先，设置可读的长度上限limit。将写模式下的缓冲区中内容的最后写入位置position值，作为读模式下的limit上限值。
         * 其次，把读的起始位置position的值设为0，表示从头开始读。
         * 最后，清除之前的mark标记，因为mark保存的是写模式下的临时位置。在读模式下，如果继续使用旧的mark标记，会造成位置混乱。
         */


        /**
         * Q: 在读取完成后，如何再一次将缓冲区切换成写入模式呢？
         * A: 可以调用Buffer.clear()清空或者Buffer.compact()压缩方法，它们可以将缓冲区转换为写模式。
         */
    }
}
