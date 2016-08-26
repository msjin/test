package com.msj.design.proxy;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibProxy implements BeanPostProcessor{

	
	public Object build(Class<?> interfaces) throws BeansException {
		Enhancer enhancer = new Enhancer();
		enhancer.setInterfaces(new Class[]{interfaces});
		enhancer.setCallback(new MethodInterceptor() {

			@Override
			public Object intercept(Object instance, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				Object result = null;
				
				method.invoke(method.getClass(), args);
				return result;
			}
		});
		
		return enhancer.create();
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("-------------------postProcessBeforeInitialization----------------------");
		load(bean);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	
	public void load(Object bean){
		@SuppressWarnings("rawtypes")
		Class c = bean.getClass();
		System.out.println(c.getName());
	}

}
