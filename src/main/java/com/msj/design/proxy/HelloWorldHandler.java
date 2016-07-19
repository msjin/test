package com.msj.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloWorldHandler implements InvocationHandler {
	
	private Object target;
	
	public HelloWorldHandler(){
		super();
	}
	
	public HelloWorldHandler(Object obj){
		super();
		this.target = obj;
	}
	
	public Object createProxyInstance(Object obj){
		this.target = obj;
		//创建动态代理对象
		return Proxy.newProxyInstance(
				this.target.getClass().getClassLoader(),
				this.target.getClass().getInterfaces(),
				this);
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		doBefore();
		
		Object result = null;
		result = method.invoke(target, args);
		
		doAfter();
		return result;
	}
	
	private void doBefore(){
		System.out.println("before method invoke");
	}
	
	private void doAfter(){
		System.out.println("after method invoke");
	}

}
