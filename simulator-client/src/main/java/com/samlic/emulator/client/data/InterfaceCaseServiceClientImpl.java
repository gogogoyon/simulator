package com.samlic.emulator.client.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

public class InterfaceCaseServiceClientImpl implements InterfaceCaseService {
	private String server;
	private RestTemplate restTemplate;
	
	public InterfaceCaseServiceClientImpl(String server, RestTemplate restTemplate) {
		this.server = "http://" + server;
		this.restTemplate = restTemplate;
	}


	public void addInterfaceCase(InterfaceCase data) {
		HttpHeaders formHeaders = new HttpHeaders();
		formHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		formHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> entity = new HttpEntity<String>(data.toString(), formHeaders);
		System.out.println(data.toString());
		WebState ret = restTemplate.postForObject(server + "/interfaceCase/add", entity, WebState.class);
		if(!ret.isSuccess()) {
			throw new IllegalStateException("Failed to add interface case.");
		}
		
		data.setId((Integer)ret.getData());
	}


	public void deleteInterfaceCase(InterfaceCase data) {
		HttpHeaders formHeaders = new HttpHeaders();
		formHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		formHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> entity = new HttpEntity<String>("id=" + data.getId(), formHeaders);
		WebState ret = restTemplate.postForObject(server + "/interfaceCase/delete", entity, WebState.class);
		if(!ret.isSuccess()) {
			throw new IllegalStateException("Failed to delete interface case.");
		}
	}

	public void updateInterfaceCase(InterfaceCase data) {
		HttpHeaders formHeaders = new HttpHeaders();
		formHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		formHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> entity = new HttpEntity<String>(data.toString(), formHeaders);
		WebState ret = restTemplate.postForObject(server + "/interfaceCase/update", entity, WebState.class);
		if(!ret.isSuccess()) {
			throw new IllegalStateException("Failed to update interface case.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<InterfaceCase> queryAll() {		
		WebState ret = restTemplate.getForObject(server + "/interfaceCase/all", WebState.class);
		if(!ret.isSuccess()) {			
			throw new IllegalStateException("Failed to query interface case.");
		}
		
		List<InterfaceCase> dataList = new ArrayList<InterfaceCase>();
		List<Map<String, ?>> list = (List<Map<String, ?>>)ret.getData();
		for(Map<String, ?> item : list) {
			dataList.add(JSON.parseObject(JSON.toJSONString(item), InterfaceCase.class));
		}
		
		return dataList;
	}
	
}
