package com.taofeng.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2019/10/21 下午9:10
 * @since V1.0
 */
public class ChannelTest {


    public static void main(String[] args) {
        try {
            allocate();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 非直接缓冲区
     */
    public static void allocate() throws IOException{

        //读取文件
        FileInputStream fileInputStream = new FileInputStream("/Users/letao/Desktop/"+"1.txt");
        //写文件
        FileOutputStream outputStream = new FileOutputStream("/Users/letao/Desktop/"+"2.txt");

        //获取读管道
        FileChannel readChannle = fileInputStream.getChannel();
        //获取写的管道流
        FileChannel writeChannle = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        while (readChannle.read(byteBuffer) != -1){
            //开启度模式
            byteBuffer.flip();
            writeChannle.write(byteBuffer);
            byteBuffer.clear();
        }

        fileInputStream.close();
        outputStream.close();

        readChannle.close();
        writeChannle.close();;

    }

    /**
     * 直接缓冲区
     */
    public static void allocateDirect() throws IOException {

        //只读通道
        FileChannel readChannel = FileChannel.open(Paths.get("/Users/letao/Desktop/"+"1.txt"), StandardOpenOption.READ);
        //只写的通道
        FileChannel writeChannel = FileChannel.open(Paths.get("/Users/letao/Desktop/"+"2.txt"), StandardOpenOption.WRITE,StandardOpenOption.CREATE,StandardOpenOption.READ);

        //读的缓冲区
        MappedByteBuffer readMap = readChannel.map(FileChannel.MapMode.READ_ONLY, 0, readChannel.size());
        //写的缓冲区
        MappedByteBuffer writeMap = writeChannel.map(FileChannel.MapMode.READ_WRITE, 0, readChannel.size());

        byte[] result = new byte[readMap.limit()];

        readMap.get(result);

        writeMap.put(result);

        readChannel.close();

        writeChannel.close();

    }

    public static void scatteringReads() throws IOException {

        //只读通道
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/letao/Desktop/"+"1.txt","rw");

        FileChannel channel = randomAccessFile.getChannel();

        ByteBuffer buffer1  = ByteBuffer.allocate(100);
        ByteBuffer buffer2  = ByteBuffer.allocate(100);

        ByteBuffer[] dsts = new ByteBuffer[]{buffer1,buffer2};

        channel.read(dsts);

        for (ByteBuffer byteBuffer : dsts) {
            // 切换为读取模式
            byteBuffer.flip();
        }

        RandomAccessFile randomAccessFile2 = new RandomAccessFile("/Users/letao/Desktop/"+"2.txt","rw");

        FileChannel channel1 = randomAccessFile2.getChannel();

        channel1.write(dsts);

        channel.close();

        channel1.close();

    }

}
