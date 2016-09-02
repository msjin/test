package com.msj.rpc.example;

import org.springframework.stereotype.Component;

import com.msj.design.proxy.HelloWorld;
import com.msj.rpc.consumer.annotation.RemoteProvider;

@Component
public class ClientProcess {

	@RemoteProvider
	private HelloWorld helloWorld;
	
	public void say(String name){
		helloWorld.Say(name);
	}
	
	public String run(String name, int rank){
		return helloWorld.run(name, rank);
	}
	
}
