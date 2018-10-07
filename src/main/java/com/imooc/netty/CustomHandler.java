package com.imooc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @Author peterLee
 * @Date 2018/9/30 下午2:47
 * @Describtion : 创建自定义助手类
 *  SimpleChannelInboundHandler:对于请求来讲，其实相当于[入站，入境]
 */
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        //获取channel
        Channel channel = ctx.channel();

        if (msg instanceof HttpRequest){
            //显示客户端的远程地址
            System.out.println(channel.remoteAddress());

            //定义发送 数据消息
            ByteBuf content = Unpooled.copiedBuffer("Hello netty~", CharsetUtil.UTF_8);

            //构建一个响应
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,content);

            //为响应增加数据类型和长度
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            //把响应刷到客户端
            ctx.writeAndFlush(response);
        }

    }

    /**
     *  channel 注册
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 注册");
        super.channelRegistered(ctx);
    }

    /**
     * channel的移除
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel的移除");
        super.channelUnregistered(ctx);
    }

    /**
     * channel 的活跃
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 的活跃");
        super.channelActive(ctx);
    }

    /**
     * channel 非活跃
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 非活跃");
        super.channelInactive(ctx);
    }

    /**
     *  channel 读取完毕....
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 读取完毕....");
        super.channelReadComplete(ctx);
    }

    /**
     * 用户事件触发
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("用户事件触发");
        super.userEventTriggered(ctx, evt);
    }

    /**
     * channel 可写更改
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 可写更改");
        super.channelWritabilityChanged(ctx);
    }

    /**
     * channel 捕获到异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("channel 捕获到异常");
        super.exceptionCaught(ctx, cause);
    }

    /**
     *  助手类添加
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("助手类添加");
        super.handlerAdded(ctx);
    }

    /**
     * 助手类移除
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("助手类移除");
        super.handlerRemoved(ctx);
    }
}
