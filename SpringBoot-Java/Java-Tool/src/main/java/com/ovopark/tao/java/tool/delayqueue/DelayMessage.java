package com.ovopark.tao.java.tool.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟消息实体类
 * @author Xiu_Er 13813641925@163.com
 * @date 2020年11月30日
 */
public class DelayMessage implements Delayed {

    private String message;   // 延迟消息数据
    private long ttl;         // 延迟消息到期时间（过期时间）

    /**
     * 构造函数
     * @param message 消息数据
     * @param ttl 延迟时间，单位毫秒
     */
    public DelayMessage(String message, long ttl) {
        setMessage(message);
        this.ttl = System.currentTimeMillis() + ttl;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        // 计算该延迟消息距离过期还剩多少时间
        long remaining = ttl - System.currentTimeMillis();
        return unit.convert(remaining, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        // 比较、排序：对消息的延时大小进行排序，用于实现将延时时间最小的消息放到队列头部
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
