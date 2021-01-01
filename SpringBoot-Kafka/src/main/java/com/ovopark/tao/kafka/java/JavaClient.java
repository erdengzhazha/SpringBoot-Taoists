package com.ovopark.tao.kafka.java;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @apiNote Kafka的 纯java客户端
 * @author XiuEr
 * @Date 2021 1/1
 */
@Slf4j
public class JavaClient {

  public static void main(String[] args) throws InterruptedException {

    String[] topics = {"topic1", "topic2"};
    ContainerProperties containerProps = new ContainerProperties(topics);
    final CountDownLatch latch = new CountDownLatch(2);
    containerProps.setMessageListener(new MessageListener<Integer, String>() {

      @Override
      public void onMessage(ConsumerRecord<Integer, String> message) {
        log.info("received: " + message);
        latch.countDown();
      }

    });
    KafkaMessageListenerContainer<Integer, String> container = createContainer(containerProps);
    container.setBeanName("testAuto");
    container.start();

//    Thread.sleep(100000000); // wait a bit for the container to start
    KafkaTemplate<Integer, String> template = createTemplate();
    template.setDefaultTopic("topic1");
    template.sendDefault(0, "foo");
    template.sendDefault(2, "bar");
    template.sendDefault(0, "baz");
    template.sendDefault(2, "qux");
    template.flush();
//    assertTrue(latch.await(60, TimeUnit.SECONDS));
//    container.stop();
    log.info("Stop auto");
    log.info("执行操作中。。。");
    for(int i=0;i<1000;i++){
      System.out.print(i);
    }
    log.info("执行结束");
    container.stop();
    log.info("容器停掉了");
    log.info("设置容器的监听topics");
//    topics = new String[]{"topic3"};
    container.start();
    template.sendDefault(0, "磊哥");
    template.sendDefault(2, "小伙伴");
    template.sendDefault(0, "搞笑");
    template.sendDefault(2, "鸡哥");

  }

  public static KafkaMessageListenerContainer<Integer, String> createContainer(
          ContainerProperties containerProps) {
    Map<String, Object> props = consumerProps();
    DefaultKafkaConsumerFactory<Integer, String> cf =
            new DefaultKafkaConsumerFactory<Integer, String>(props);
    KafkaMessageListenerContainer<Integer, String> container =
            new KafkaMessageListenerContainer<>(cf, containerProps);
    return container;
  }
  public static Map<String, Object> consumerProps() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.96.11.161:9092");
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
    props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    return props;
  }

  public static KafkaTemplate<Integer, String> createTemplate() {
    Map<String, Object> senderProps = senderProps();
    ProducerFactory<Integer, String> pf =
            new DefaultKafkaProducerFactory<Integer, String>(senderProps);
    KafkaTemplate<Integer, String> template = new KafkaTemplate<>(pf);
    return template;
  }

  public static Map<String, Object> senderProps() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.96.11.161:9092");
    props.put(ProducerConfig.RETRIES_CONFIG, 0);
    props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
    props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
    props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return props;
  }
}
    