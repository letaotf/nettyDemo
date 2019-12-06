package com.taofeng.netty.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * <p>netty客户端</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2019/12/6 下午1:40
 * @since V1.0
 */
public class NettyClient {

    public static void main(String[] args) {
        //事件组
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            //启动类
            Bootstrap bootstrap = new Bootstrap();
            //
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new TestClientInitializer());
            //未链接成功进行阻塞
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(8080)).sync();
            //为没有关闭时一直阻塞
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e){

        }

    }
}
