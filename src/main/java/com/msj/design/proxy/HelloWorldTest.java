package com.msj.design.proxy;

public class HelloWorldTest {

	public static void main(String[] args) {
		HelloWorldHandler handler = new HelloWorldHandler();
		
		//创建动态代理对象
		HelloWorld proxy = (HelloWorld) handler.createProxyInstance(new HelloWorldImpl());
		
		proxy.Say("Jin");
		
		String status = proxy.run("Jin");
		System.out.println(status);
	}

}
