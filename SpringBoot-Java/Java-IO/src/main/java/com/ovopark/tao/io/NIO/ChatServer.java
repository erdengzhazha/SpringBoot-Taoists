package com.ovopark.tao.io.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 聊天服务器
 */
public class ChatServer {

    public static void main(String[] args) throws IOException {

        // 雇一个服务员
        Selector selector = Selector.open();
        // 服务套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8900));
        serverSocketChannel.configureBlocking(false);

        // 将accept事件绑定到selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            //阻塞在select上
            selector.select();
            // 服务员不停的去询问
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){// 如果是accpet事件
                    ServerSocketChannel ssc = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel = ssc.accept();
                    System.out.println("accept new conn: " + socketChannel.getRemoteAddress());
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    ChatHolder.join(socketChannel);
                }else if(selectionKey.isReadable()){
                    // 如果是读事件
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    // 将数据读入到buffer中
                    int length = socketChannel.read(buffer);
                    if(length>0){
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        // 将数据读入到byte数组中
                        buffer.get(bytes);
                        // 换行符会跟着消息一起传过来
                        String content = new String (bytes,"UTF-8").replace("\r\n","");
                        if(content.equalsIgnoreCase("quit")){
                            // 退出群聊
                            ChatHolder.quit(socketChannel);
                            selectionKey.cancel();
                            socketChannel.close();
                        }else {
                            // 扩散
                            ChatHolder.propagate(socketChannel,content);
                        }
                    }
                }
                iterator.remove();
            }
        }
    }

    public static class ChatHolder{
        private static final Map<SocketChannel,String> USER_MAP = new ConcurrentHashMap<>();

        public static void join(SocketChannel socketChannel){
            //有人加入给他分配一个id
            String userId = "用户"+ ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
            send(socketChannel,"您的id为："+userId+"\n\r");
            for(SocketChannel channel: USER_MAP.keySet()){
                send(channel,userId+"加入了群聊"+ "\n\r");
            }

            // 将当前的用户加入到map中
            USER_MAP.put(socketChannel,userId);
        }

        /**
         * 退出群聊
         * @param socketChannel
         */
        public static void quit(SocketChannel socketChannel){
            String  userId = USER_MAP.get(socketChannel);
            send(socketChannel,"您退出了群聊\n\r");
            USER_MAP.remove(socketChannel);

            for(SocketChannel channel : USER_MAP.keySet()){
                send(channel,userId+"退出了群聊");
            }
        }

        /**
         * 传播说话
         * @param socketChannel
         * @param content
         */
        public static void propagate(SocketChannel socketChannel,String content){
            String userId = USER_MAP.get(socketChannel);
            for(SocketChannel channel: USER_MAP.keySet()){
                if(channel!=socketChannel){
                    send(channel,userId+": "+content+"\n\t");
                }
            }
        }

        /**
         * 发送消息
         * @param socketChannel
         * @param msg
         */
        public static void send(SocketChannel socketChannel,String msg){
            try {
                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                writeBuffer.put(msg.getBytes());
                writeBuffer.flip();
                socketChannel.write(writeBuffer);
            }catch (Exception e){
                e.printStackTrace();
            }
        }



    }
}
