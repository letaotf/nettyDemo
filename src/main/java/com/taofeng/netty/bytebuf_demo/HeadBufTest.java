package com.taofeng.netty.bytebuf_demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2020/3/30 3:59 PM
 * @since V1.0
 */
public class HeadBufTest {

    public static void main(String[] args) {
        byte[] result = new byte[]{'a','b','c'};
        //创建堆缓冲区
        ByteBuf headBuf = Unpooled.buffer();
        //写入值
        System.out.println("开始---------------写入");
        headBuf.writeBytes(result);
        System.out.println("readIndex:"+ headBuf.readerIndex());
        System.out.println("writeIndex:"+ headBuf.writerIndex());
        System.out.println("capacity:"+ headBuf.capacity());
        System.out.println("开始---------------读取");
        byte[] redaResult = new byte[3];
        headBuf.readBytes(redaResult);
        System.out.println("readIndex:"+ headBuf.readerIndex());
        System.out.println("writeIndex:"+ headBuf.writerIndex());
        System.out.println("capacity:"+ headBuf.capacity());
        System.out.println("结果:"+ new String(redaResult));

    }
}
