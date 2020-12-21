/**
 * @projectName netty
 * @package com.stu.netty
 * @className com.stu.netty.NettyClient
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.stu.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * NettyClient
 * @description
 * @author huangqiang-2
 * @date 2020/12/21 19:09
 * @version 1.0
 */
public class NettyClient {
    public static void main(String[] args) {
        EventLoopGroup clients = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clients)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("客户端启动完毕");
            ChannelFuture sync = bootstrap.connect("127.0.0.1", 6666).sync();
            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            clients.shutdownGracefully();
        }
    }
}