package com.msj.rpc.provider;

import com.msj.netty.EchoServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

private final int port;
	
	public NettyServer(int port){
		this.port = port;
	}
	
	public static void main(String[] args) throws InterruptedException {
		int port = 8088;
		new EchoServer(port).star();;
	}
	
	public void star() throws InterruptedException{
		final NettyServerHandler serverhandler = new NettyServerHandler();
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
			.channel(NioServerSocketChannel.class)
			.localAddress(port)
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(serverhandler);
				}
			});
			
			ChannelFuture f = b.bind().sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			group.shutdownGracefully().sync();
		}
	}
	
}
