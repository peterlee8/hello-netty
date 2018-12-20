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
    private static ChannelGroup users =new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //获取客户端传递过来的消息
        String content = msg.text();
        //1.获取客户端发送过来的消息
        //2.判断消息类型，根据消息的类型来处理不同的业务
        //  2.1当websocket第一次open的时候，初始化channel，把用户的channel和userid关联起来
        //  2.2聊天类型的消息，把聊天记录保存到数据库，同时标记消息的签收状态[未签收]
        //  2.3签收消息类型，针对具体的消息进行签收，修改数据库中对应消息签收状态[已签收]
        //  2.4心跳类型的消息

    }

    /**
     * 当客户端连接之后，获取客户端的channel并且放到channelGroup中管理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        users.add(channel);

        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当触发handlerRemoved(),ChannelGroup会自动的移除对应的客户端Channel
        users.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生异常之后关闭连接（关闭channel）,随后从ChannelGroup中移除
        ctx.channel().close();
        users.remove(ctx.channel());
    }
}
