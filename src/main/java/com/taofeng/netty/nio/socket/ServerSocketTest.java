package com.taofeng.netty.nio.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Objects;
import java.util.Set;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2019/10/22 下午2:11
 * @since V1.0
 */
@Slf4j
public class ServerSocketTest {

    public static void main(String[] args) throws IOException {

        //开启socket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口号
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //创建选择器
        Selector selector = Selector.open();
        //接受连接请求
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (!Thread.interrupted()) {
            try {
                //阻塞判断是否有时间
                while (selector.select() == 0) {
                    continue;
                }
                Set<SelectionKey> keys = selector.keys();
                if (CollectionUtils.isEmpty(keys)) {
                    System.out.println("选择器为空");
                }
                for (SelectionKey next : keys) {
                    //连接事件
                    if (next.isAcceptable()) {
                        //socket 监听链接
                        SocketChannel accept = serverSocketChannel.accept();
                        if (Objects.nonNull(accept)) {
//                        ByteBuffer byteBuffer = ByteBuffer.allocate(24);
//                        while (accept.read(byteBuffer) != -1){
//                            //开始写入
//                            byteBuffer.flip();
//                            byte[] bytes = new byte[byteBuffer.limit()];
//                            byteBuffer.get(bytes);
//                            System.out.println("得到的输出结果:"+ new String(bytes));
//                            byteBuffer.clear();
//                        }
//                        break;
                            //设置非阻塞问题
                            accept.configureBlocking(false);
                            //注册
                            accept.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        }
                    }
                    //读事件
                    if (next.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) next.channel();
                        ByteBuffer byteBuffer = (ByteBuffer) next.attachment();
                        socketChannel.read(byteBuffer);
                        System.out.println(new String(byteBuffer.array()));
                        byteBuffer.put("你好".getBytes());
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                    }
                    //写事件
                    if (next.isWritable()) {
                        SocketChannel socketChannel = (SocketChannel) next.channel();
                        ByteBuffer byteBuffer = (ByteBuffer) next.attachment();
                        byteBuffer.put("你好".getBytes());
                        byteBuffer.clear();
                        socketChannel.write(byteBuffer);
                        next.interestOps(SelectionKey.OP_READ);
                    }
                    keys.remove(next);
                }

            } catch (Exception e){
                log.error("失败");
            }
        }
        serverSocketChannel.close();
        System.out.println("结束");
    }
}
