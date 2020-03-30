package com.taofeng.netty.Demo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2019/12/26 5:18 PM
 * @since V1.0
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        //主线程
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //工作线程
        NioEventLoopGroup workGroup = new NioEventLoopGroup(2);
        //加入参数
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workGroup)
                   .channel(NioServerSocketChannel.class)
                   .handler(new NettyTestHendler())
                   .childHandler(new TestServerHandler());
        ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
        channelFuture.channel().closeFuture().sync();

    }
}
