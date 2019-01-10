package com.samlic.emulator.client;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.samlic.emulator.entity.InterfaceCase;

public class InterfaceCaseServiceClientImplTest {
	//@Test
	public void addTest() {		
		InterfaceCaseServiceClientImpl service = new InterfaceCaseServiceClientImpl("localhost:8080", new RestTemplate());
		InterfaceCase data = new InterfaceCase();
		data.setName("junit");
		data.setUrl("/test/junit");
		data.setMatchRule("method='POST'");
		data.setResponse("<h>Junit Test</h>");
		data.setContentType("text/html");
		data.setStatus(0);
		data.setGrouping("junit");
		data.setCreateTime(new Date());
		data.setUpdateTime(new Date());
		
		service.addInterfaceCase(data);
	}
	
	@Test
	public void test() {		
		InterfaceCaseServiceClientImpl service = new InterfaceCaseServiceClientImpl("localhost:8080", new RestTemplate());
		List<InterfaceCase> list = service.queryAll();
		int initSize = list.size();
		
		InterfaceCase data = new InterfaceCase();
		data.setName("junit");
		data.setUrl("/test/junit");
		data.setMatchRule("method='POST'");
		data.setResponse("<h>Junit Test</h>");
		data.setContentType("text/html");
		data.setStatus(0);
		data.setGrouping("junit");
		data.setCreateTime(new Date());
		data.setUpdateTime(new Date());
		
		service.addInterfaceCase(data);
		
		list = service.queryAll();		
		Assert.assertTrue(list.size() == initSize + 1);
		
		service.deleteInterfaceCase(data);
		list = service.queryAll();
		
		Assert.assertTrue(list.size() == initSize);
	}
}
