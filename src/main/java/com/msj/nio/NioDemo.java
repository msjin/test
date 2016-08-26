package com.msj.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class NioDemo {

	public static void main(String[] args) {
		try {
			NioDemo demo = new NioDemo();
			demo.nioServer();
			System.out.println("server start...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void buffere() throws IOException{
		
		ByteBuffer bu = ByteBuffer.allocate(50);
		
		RandomAccessFile afile = new RandomAccessFile(new File("E:/test/1.txt"), "rw");
		FileChannel channel = afile.getChannel();
		int ok = channel.read(bu);
		
		StringBuffer result = new StringBuffer();
		while(ok != -1){
			bu.flip();
			while(bu.hasRemaining()){
				Charset cs = Charset.forName("UTF-8");
				
				result.append(bu.getChar());
			}
			bu.clear();
			ok = channel.read(bu);
		}
		channel.close();
		afile.close();
		
		System.out.println(Charset.forName("UTF-8")  
                .newDecoder().decode(bu).toString());
		
		
	}
	
	public void nioServer() throws IOException{
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.socket().bind(new InetSocketAddress(8056));
		channel.configureBlocking(false);
		Selector selector = Selector.open();
		channel.register(selector, SelectionKey.OP_ACCEPT);
		
		while(true){
			int readyChannels = selector.select();
			if(readyChannels == 0) continue;
			
			@SuppressWarnings("rawtypes")
			Iterator it = selector.selectedKeys().iterator();
			
			while(it.hasNext()){
				SelectionKey key = (SelectionKey) it.next();
				
				if(key.isAcceptable()){
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					SocketChannel sChannel = server.accept();
					
					registerChannel(selector, sChannel, SelectionKey.OP_READ);
					System.out.println("accept");
				}else if(key.isConnectable()){
					System.out.println("Connectable");
				}else if(key.isReadable()){
					readDataFromSocket(key);
					System.out.println("read");
				}else if(key.isWritable()){
					System.out.println("writable");
				}
				it.remove();
			}
			
		}
	}
	
	
	protected void registerChannel(Selector selector, SelectableChannel channel, int ops) throws IOException{
		if(channel == null)
			return;
		
		channel.configureBlocking(false);
		channel.register(selector, ops);
		
	}
	
	private ByteBuffer buffer = ByteBuffer.allocate(1024);
	
	protected void readDataFromSocket(SelectionKey key) throws IOException{
		SocketChannel socketChannel = (SocketChannel) key.channel();
		
		int count;
		buffer.clear();
		
		StringBuffer result = new StringBuffer();
		while((count = socketChannel.read(buffer)) > 0){
			buffer.flip();
			Charset.defaultCharset().encode(buffer.asCharBuffer());
			while(buffer.hasRemaining()){
				result.append((char)buffer.get());
			}
			System.out.println(result);
			buffer.clear();
		}
		
		if(count < 0){
			socketChannel.close();
		}
	}
	
	
}

