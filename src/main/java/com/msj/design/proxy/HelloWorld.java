package com.msj.design.proxy;

import org.springframework.stereotype.Component;

@Component
public interface HelloWorld {
	
	public void Say(String name);

	public String run(String name);
	
	public String run(String name, int rank);
}
