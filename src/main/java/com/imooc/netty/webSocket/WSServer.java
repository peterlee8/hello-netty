package com.imooc.netty.webSocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author peterLee
 * @Date 2018/9/30 下午3:27
 * @Describtion :
 */
public class WSServer {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup mainGroup = new NioEventLoopGroup();
        NioEventLoopGroup subGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap server =new ServerBootstrap();
            server.group(mainGroup,subGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WSServerInitializer());

            ChannelFuture future = server.bind(8088).sync();

            future.channel().closeFuture().sync();

        }finally {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }


    }
}
