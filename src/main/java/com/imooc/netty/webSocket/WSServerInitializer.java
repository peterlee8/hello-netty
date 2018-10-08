package com.imooc.netty.webSocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Author peterLee
 * @Date 2018/10/1 上午9:12
 * @Describtion :
 */
public class WSServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        //webSocket基于http协议，所以要有http编解码器
        pipeline.addLast(new HttpServerCodec());
        //对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        //对http message进行聚合，聚合成FullHttpRequest或FullHttpResponse
        //几乎在netty编程中都会用到此handler
        pipeline.addLast(new HttpObjectAggregator(1024*64));

        //=================以上是支持http协议==========
        /**
         * webSocket服务器处理的协议，用户指定给客户端连接访问的路由：/ws
         * 本handler会帮你处理一些繁重的复杂的事
         * 会帮你处理握手动作：handshaking(close,ping,pong) ping+pong = 心跳
         * 对于webSocket来讲，都是以frames进行传输的，不同数据类型对应的frames也不同
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //自定的handler
        pipeline.addLast(new ChatHandler());


    }
}
