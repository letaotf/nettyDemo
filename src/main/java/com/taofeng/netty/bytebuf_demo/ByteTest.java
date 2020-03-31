package com.taofeng.netty.bytebuf_demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2020/3/30 9:41 PM
 * @since V1.0
 */
public class ByteTest {

    public static void main(String[] args) {

        byte[] result = new byte[]{'a','b','c'};
        //创建堆缓冲区
        ByteBuf headBuf = Unpooled.buffer();
        headBuf.writeBytes(result);
        System.out.println("capacity:"+ headBuf.capacity());
        for (int i = 0; i < headBuf.capacity(); i++) {
            byte aByte = headBuf.getByte(i);
            System.out.println(aByte);
        }
    }
}
