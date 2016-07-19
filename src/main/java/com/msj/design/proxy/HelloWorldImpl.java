package com.msj.design.proxy;

public class HelloWorldImpl implements HelloWorld {

	public void Say(String name) {
		System.out.println(name + " say hello world!");
	}

	public String run(String name) {
		System.out.println(name + " running...");
		return "OK";
	}
	

}
