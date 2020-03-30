package com.taofeng.netty.Demo1;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * <p>添加handel</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2019/12/26 2:12 PM
 * @since V1.0
 */
public class TestServerHandler extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        //获取出pipeline
        ChannelPipeline pipeline = ch.pipeline();
        //可读字节长度
        pipeline.addLast(new FixedLengthFrameDecoder(1));
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new NettyServerHendler());
    }
}
