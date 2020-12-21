/**
 * @projectName netty
 * @package com.stu.netty
 * @className com.stu.netty.NettyClientHandler
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.stu.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;

/**
 * NettyClientHandler
 * @description
 * @author huangqiang-2
 * @date 2020/12/21 19:09
 * @version 1.0
 */
public class NettyClientHandler extends ChannelDuplexHandler {
    /**
      * NettyClientHandler
      * @description当通道就绪就会触发该方法
      * @param ctx: 
      * @return: void
      * @date 2020/12/21 20:01
      * @author huangqiang-2
      * @version 1.0.0
      */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ctx "+ ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("我是客户端".getBytes(CharsetUtil.UTF_8)));
    }

    /**
      * NettyClientHandler
      * @description读取服务器发来的消息
      * @param ctx:
      * @param msg:
      * @return: void
      * @date 2020/12/21 20:23
      * @author huangqiang-2
      * @version 1.0.0
      */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端收到服务端的消息："+byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
      * NettyClientHandler
      * @description发生异常时关闭通道
      * @param ctx:
      * @param cause:
      * @return: void
      * @date 2020/12/21 20:22
      * @author huangqiang-2
      * @version 1.0.0
      */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}