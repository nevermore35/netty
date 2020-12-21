/**
 * @projectName netty
 * @package com.stu.netty
 * @className com.stu.netty.NettyServerHandler
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
 * NettyServerHandler
 * @description服务端的handler
 * @author huangqiang-2
 * @date 2020/12/21 19:08
 * @version 1.0
 */
public class NettyServerHandler extends ChannelDuplexHandler {

    /**
     * NettyServerHandler
     * @description 读取客户端发送的消息
     * 在处理非常耗时的操作时，可以使用netty的taskQueue异步处理，具体的做法看代码示例
     * 重复使用ctx.channel().eventLoop().execute()时，由于是同一个线程去执行，两块的内容会同步执行
     * @param ctx:包含所有上下文的信息，包括通道channel和管道pipeline
     * @param msg:就是客户端发送的数据
     * @return: null
     * @date 2020/12/21 19:44
     * @author huangqiang-2
     * @version 1.0.0
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //运用netty的taskQueue在此处进行异步处理
        ctx.channel().eventLoop().execute(() -> {

        });
        System.out.println("ctx :" + ctx);
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端发送的数据：" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端的地址" + ctx.channel().remoteAddress());
    }

    /**
     * NettyServerHandler
     * @description数据读取完毕
     * @param ctx:
     * @return: void
     * @date 2020/12/21 19:49
     * @author huangqiang-2
     * @version 1.0.0
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("已收到消息".getBytes(CharsetUtil.UTF_8)));
    }

    /**
     * NettyServerHandler
     * @description发生异常，关闭通道
     * @param ctx:
     * @param cause:
     * @return: void
     * @date 2020/12/21 19:51
     * @author huangqiang-2
     * @version 1.0.0
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}