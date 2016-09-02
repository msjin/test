package com.msj.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.util.CharsetUtil;

public class NettyTest {

	public static void main(String[] args) {
		ByteBuf buf = new PooledByteBufAllocator().heapBuffer(100);
		buf.writeBytes("hello".getBytes());
		
		System.out.println(buf.toString(CharsetUtil.UTF_8));
		System.out.println(buf.maxCapacity());
		System.out.println(buf.capacity());
		System.out.println(buf.writerIndex());
		System.out.println(buf.readerIndex());
		System.out.println(buf.isDirect());
	}

}
