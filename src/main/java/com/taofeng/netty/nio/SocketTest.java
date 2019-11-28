package com.taofeng.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2019/10/22 下午2:05
 * @since V1.0
 */
public class SocketTest {


    public static void main(String[] args) throws IOException {
        //开启Socket
        SocketChannel socketChannel = SocketChannel.open();
        //链接Socket
        socketChannel.connect(new InetSocketAddress(8080));
        //需要传输的结果
        String name = "我是陶峰-----124252353";
        //换车区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //写入缓冲区
        byteBuffer.put(name.getBytes());
        //设置写的位置
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            socketChannel.write(byteBuffer);
        }
        socketChannel.close();
    }


}
