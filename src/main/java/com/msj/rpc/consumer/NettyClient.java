package com.msj.rpc.consumer;

import javax.annotation.Resource;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

	private String host = "127.0.0.1";
	private int port = 8088;
	
	@Resource
	private NettyClientHandler nettyClientHandler;
	
	public NettyClient() {
		super();
	}

	public NettyClient(String host, int port){
		this.host = host;
		this.port = port;
	}
	
	public void connect() throws InterruptedException{
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
			.channel(NioSocketChannel.class)
			.remoteAddress(host, port)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(nettyClientHandler);
				}
			});
			
			ChannelFuture f = b.connect().sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			group.shutdownGracefully().sync();
		}
	}
	
}
