package com.samlic.emulator.jdbc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitializingBeanTest implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet");
	}
	
	@PostConstruct
	public void postConstruct() throws Exception {
		System.out.println("postConstruct");
	}


}
