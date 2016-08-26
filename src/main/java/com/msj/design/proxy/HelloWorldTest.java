package com.msj.design.proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")*/
public class HelloWorldTest {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	public static void main(String[] args) {
//		new HelloWorldTest().proxy();
		new HelloWorldTest().cglibProxyTest();
	}
	
	public void proxy(){
		HelloWorldHandler handler = new HelloWorldHandler();
		
		//创建动态代理对象
		HelloWorld proxy = (HelloWorld) handler.createProxyInstance(new HelloWorldImpl());
		
		proxy.Say("Jin");
		
		String status = proxy.run("Jin");
		System.out.println(status);
	}
	
	public void cglibProxyTest(){
		
		CglibProxy c = new CglibProxy();
		HelloWorld o = (HelloWorld)c.build(HelloWorld.class);
		
		o.run("tim");
	}

}
