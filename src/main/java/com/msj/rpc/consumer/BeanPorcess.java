package com.msj.rpc.consumer;

import java.lang.reflect.Field;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.msj.rpc.consumer.annotation.RemoteProvider;

@Component
public class BeanPorcess implements BeanPostProcessor {

	@Resource
	private CglibProxy cglibProxy;
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		load(bean);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	
	public void load(Object bean){
		Field[] fields = bean.getClass().getDeclaredFields();
		for(Field field:fields){
			if(field.isAnnotationPresent(RemoteProvider.class)){
//				RemoteProvider remoteProvider = field.getAnnotation(RemoteProvider.class);
				try {
					field.setAccessible(true);
					field.set(bean,cglibProxy.build(field.getType()));
				} catch (BeansException | IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
