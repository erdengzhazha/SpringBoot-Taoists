package com.ovopark.tao.netty.javanio.buffer;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;

@Slf4j
public class UseBuffer {

    static IntBuffer intBuffer = null;

    public static void allocatTest(){
        //调用allocate方法，而不是使用 new ,创建了一个容量为 20的 IntBuffer实例对象
        intBuffer = IntBuffer.allocate(20);
        print();
    }

    static void print(){
        log.info("-----------------after allocate------------------");
        log.info("position = "+ intBuffer.position());
        log.info("limit = "+ intBuffer.limit());
        log.info("capacity = "+ intBuffer.capacity());
    }

    public static void main(String[] args) {
        /**
         * 一个缓冲区在新建后，处于写入的模式，
         * position写入位置为0，
         * 最大可写上限limit为的初始化值（这里是20），
         * 而缓冲区的容量capacity也是初始化值
         */
        allocatTest();
        for(int i=0;i<5;i++){
            intBuffer.put(i);
        }


        /**
         * position变成了5，
         * 指向了第6个可以写入的元素位置。
         * 而limit最大写入元素的上限、
         * capacity最大容量的值，并没有发生变化
         */
        print();
    }


}
