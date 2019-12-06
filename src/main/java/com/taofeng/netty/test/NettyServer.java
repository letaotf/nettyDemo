package com.taofeng.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2019/12/6 下午1:58
 * @since V1.0
 */
public class NettyServer {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                     .channel(NioServerSocketChannel.class)
                     .childHandler(new TestServerInitializer());

            ChannelFuture sync = bootstrap.bind(8080).sync();

            sync.channel().closeFuture().sync();
        } catch (Exception e){

        }
    }
}
