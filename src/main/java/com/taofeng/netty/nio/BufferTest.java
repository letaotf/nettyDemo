package com.taofeng.netty.nio;

import java.nio.ByteBuffer;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2019/10/21 下午5:41
 * @since V1.0
 */
public class BufferTest {


    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println("-------基础配置-----------");
        System.out.println("position:"+byteBuffer.position());
        System.out.println("limit:"+ byteBuffer.limit());
        System.out.println("capacity:"+byteBuffer.capacity());
        System.out.println("-------写入数据-----------");
        byte[] bytes = new byte[]{'a','b','c'};
        byteBuffer = byteBuffer.put(bytes);
        System.out.println("position:"+byteBuffer.position());
        System.out.println("limit:"+ byteBuffer.limit());
        System.out.println("capacity:"+byteBuffer.capacity());
        //开启度配置
        byteBuffer.flip();
        System.out.println("---------开始读数据--------");
        System.out.println("position:"+byteBuffer.position());
        System.out.println("limit:"+ byteBuffer.limit());
        System.out.println("capacity:"+byteBuffer.capacity());
        byte[] outWrite = new byte[byteBuffer.limit()];
        byteBuffer.get(outWrite);
        System.out.println("---------开始读数据完成--------");
        System.out.println("position:"+byteBuffer.position());
        System.out.println("limit:"+ byteBuffer.limit());
        System.out.println("capacity:"+byteBuffer.capacity());
        System.out.println("结果："+ new String(outWrite));
        System.out.println("---------开始读数据重复度读-------");
        byteBuffer.rewind();
        byteBuffer.get(outWrite,1,2);
        System.out.println("结果："+ new String(outWrite));

    }
}
