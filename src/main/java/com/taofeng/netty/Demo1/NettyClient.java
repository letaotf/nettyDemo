package com.taofeng.netty.Demo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>客户端</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2019/12/26 2:04 PM
 * @since V1.0
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        //线程组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);
        //客户端启动器
        Bootstrap bootstrap = new Bootstrap();
        //添加线程组，相关操作
        bootstrap.group(eventLoopGroup)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,1)
                .attr(AttributeKey.newInstance("letao"),"letao---test")
                .channel(NioSocketChannel.class)
                .handler(new TestClientHandler());
        //链接远程的节点
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for(; ;){
            channelFuture.channel().writeAndFlush(bufferedReader.readLine());

        }

    }
}
