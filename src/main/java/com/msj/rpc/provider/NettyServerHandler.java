package com.msj.rpc.provider;

import java.lang.reflect.Method;

import com.alibaba.fastjson.JSONObject;
import com.msj.rpc.common.RpcRequest;
import com.msj.rpc.common.RpcResponse;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcRequest request) throws Exception {
		Object obj = Class.forName(request.getServiceId()).newInstance();
		
		ByteBuf out = new PooledByteBufAllocator().directBuffer();
		RpcResponse<Object> response = new RpcResponse<Object>();
		Method[] methods = obj.getClass().getDeclaredMethods();
		for(Method method:methods){
			if(method.getName().equals(request.getCommand())){
				Object result = method.invoke(obj, request.getParams());
				response.setResult(result);
				response.setStatus("200");
			}
		}
		out.writeBytes(JSONObject.toJSONString(response).getBytes());
		ctx.writeAndFlush(out);
	}

	
}
