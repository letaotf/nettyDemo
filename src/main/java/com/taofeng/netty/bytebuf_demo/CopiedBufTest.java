package com.taofeng.netty.bytebuf_demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;


/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2020/3/30 10:18 PM
 * @since V1.0
 */
public class CopiedBufTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //创建堆缓冲区
        ByteBuf copiedBuffer = Unpooled.copiedBuffer("abcdefg", Charset.forName("utf-8"));
        ByteBuf copy = copiedBuffer.copy(0, 2);
        System.out.println("sliceResult:"+ copy.toString(Charset.forName("utf-8")));
        copiedBuffer.setByte(0,'t');
        System.out.println("sliceResult:"+ copy.toString(Charset.forName("utf-8")));

    }
}
