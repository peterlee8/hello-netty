package com.imooc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * @Author peterLee
 * @Date 2018/9/30 下午3:27
 * @Describtion :
 */
@Component
public class WSServer {

    private static class SingletonWSServer{
        final static WSServer instance =new WSServer();
    }
    //通过单利模式获取WSServer，目的是启动加载
    public static WSServer getInstance(){
        return SingletonWSServer.instance;
    }

    private NioEventLoopGroup mainGroup;
    private NioEventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public WSServer(){
         mainGroup = new NioEventLoopGroup();
         subGroup = new NioEventLoopGroup();
         server =new ServerBootstrap();
         server.group(mainGroup,subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitializer());
    }

    public void start(){
         this.future = server.bind(8088);
        System.err.println("netty WebSocket 启动完毕.....");

    }

   /*public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup mainGroup = new NioEventLoopGroup();
        NioEventLoopGroup subGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap server =new ServerBootstrap();
            server.group(mainGroup,subGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(null);

            ChannelFuture future = server.bind(8088).sync();

            future.channel().closeFuture().sync();

        }finally {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }


    }*/
}
