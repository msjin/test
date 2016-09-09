package com.msj.rpc.consumer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import com.msj.design.proxy.HelloWorld;
import com.msj.rpc.common.RpcRequest;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

@Component
public class CglibProxy{
	@Resource
	private NettyClientHandler nettyClientHandler;
	
	public Object build(Class<?> interfaces) throws BeansException {
		Enhancer enhancer = new Enhancer();
		enhancer.setInterfaces(new Class[]{interfaces});
		enhancer.setCallback(new MethodInterceptor() {

			@Override
			public Object intercept(Object instance, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				Object result = null;
				String serviceId = method.getDeclaringClass().getName();
				RpcRequest request = new RpcRequest();
				request.setParams(args);
				request.setCommand(method.getName());
				request.setServiceId(serviceId);
				
				NettyClient client = new NettyClient();
				client.connect();
				
				Channel channel= nettyClientHandler.getChannel();
				ChannelFuture future = channel.writeAndFlush(request);
				future.addListener(new ChannelFutureListener() {
					
					@Override
					public void operationComplete(ChannelFuture future) throws Exception {
						if(future.isSuccess()){
						}else{
							future.cause().printStackTrace();
							future.channel().close();
						}
					}
				});
				return result;
			}
		});
		
		return enhancer.create();
	}
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		try {
			Class<?> c = Class.forName("com.msj.rpc.consumer.CglibProxy");
			Object obj = c.newInstance();
			Method[] method = c.getDeclaredMethods();
			for(Method m:method){
				if(m.getName().equals("build")){
					m.invoke(obj, HelloWorld.class);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
