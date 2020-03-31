package com.taofeng.netty.bytebuf_demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2020/3/30 9:23 PM
 * @since V1.0
 */
public class CompositieBufTest {

    public static void main(String[] args) {
        byte[] result = new byte[]{'a','b','c'};
        //创建堆缓冲区
        CompositeByteBuf byteBuf = Unpooled.compositeBuffer();

        // 头和尾
        ByteBuf head = Unpooled.buffer();
        ByteBuf body = Unpooled.directBuffer();
        body.writeBytes(result);
        byteBuf.addComponents(head,body);

        System.out.println("readIndex:"+ byteBuf.readerIndex());
        System.out.println("writeIndex:"+ byteBuf.writerIndex());
        System.out.println("capacity:"+ byteBuf.capacity());

        byte[] redaResult = new byte[byteBuf.readerIndex()];
        byteBuf.readBytes(redaResult);

        System.out.println("结果:"+ new String(redaResult));

        //字节数组
//        for (ByteBuf buf : byteBuf) {
//            System.out.println("结果"+ buf.toString());
//        }

    }
}
