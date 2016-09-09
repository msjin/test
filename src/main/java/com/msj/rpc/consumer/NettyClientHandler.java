package com.msj.rpc.consumer;

import org.springframework.stereotype.Component;

import com.msj.rpc.common.RpcResponse;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@SuppressWarnings("rawtypes")
@Component
public class NettyClientHandler extends SimpleChannelInboundHandler<RpcResponse> {

	private ChannelHandlerContext handlerContext;
	
	private Channel channel;
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		super.handlerAdded(ctx);
		this.handlerContext = ctx;
		this.channel = ctx.channel();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
	}
	
	
	public void send(String msg){
		this.handlerContext.writeAndFlush(msg);
	}
	
	public Channel getChannel(){
		return this.channel;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcResponse msg) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
