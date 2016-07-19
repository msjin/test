package com.msj.spring.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	private static Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping("/say")
	public String say(String user){
		log.info("{} say hello",user);		
		return "ok";
	}
}
