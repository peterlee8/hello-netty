package com.imooc.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @Author peterLee
 * @Date 2018/10/1 上午9:38
 * @Describtion :
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //用于记录和管理所有的channel
    private static ChannelGroup clients =new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //获取客户端传递过来的消息
        String content = msg.text();
        System.out.println("接受到的消息："+content);

        /*for (Channel channel :clients){
            channel.writeAndFlush(new TextWebSocketFrame(String.format("[服务器接收到消息]%s,消息为：%s", LocalDateTime.now(), content)));
        }*/

        clients.writeAndFlush(new TextWebSocketFrame(String.format("[服务器接收到消息]%s,消息为：%s", LocalDateTime.now(), content)));

    }

    /**
     * 当客户端连接之后，获取客户端的channel并且放到channelGroup中管理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        clients.add(channel);

        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当触发handlerRemoved(),ChannelGroup会自动的移除对应的客户端Channel
        //clients.remove(ctx.channel());
        System.out.println("handlerRemoved()  channelId 长id ="+ctx.channel().id().asLongText());
        System.out.println("handlerRemoved()  channelId 短id="+ctx.channel().id().asShortText());
    }
}
