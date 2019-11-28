package com.taofeng.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Objects;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2019/10/22 下午2:11
 * @since V1.0
 */
public class ServerSocketTest {

    public static void main(String[] args) throws IOException {

        //开启socket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口号
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        while (true){
            //socket 监听链接
            SocketChannel accept = serverSocketChannel.accept();
            if(Objects.nonNull(accept)){
                ByteBuffer byteBuffer = ByteBuffer.allocate(24);
                while (accept.read(byteBuffer) != -1){
                    //开始写入
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.limit()];
                    byteBuffer.get(bytes);
                    System.out.println("得到的输出结果:"+ new String(bytes));
                    byteBuffer.clear();
                }
                break;
            }
        }
        serverSocketChannel.close();
        System.out.println("结束");
    }
}
