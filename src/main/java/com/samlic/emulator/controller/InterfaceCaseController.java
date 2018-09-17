package com.samlic.emulator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.samlic.emulator.core.InterfaceCaseService;
import com.samlic.emulator.entity.InterfaceCase;

@Controller
@RequestMapping(path="/interfaceCase")
public class InterfaceCaseController extends BaseHandler {
	@Autowired
	private InterfaceCaseService caseService;
	
	@RequestMapping(path="/all")
	@ResponseBody
	public WebState getAll() {
		return wrap(caseService.queryAll());
	}

	@RequestMapping(path="/add")
	@ResponseBody
	public WebState add(InterfaceCase data) {
		System.out.println(data.toString());
		caseService.addInterfaceCase(data);
		
		return wrap(data.getId());
	}
	
	@RequestMapping(path="/delete")
	@ResponseBody
	public WebState delete(InterfaceCase data) {
		caseService.deleteInterfaceCase(data);
		
		return wrap();
	}
	
	@RequestMapping(path="/update")
	@ResponseBody
	public WebState update(InterfaceCase data) {
		caseService.updateInterfaceCase(data);
		
		return wrap();
	}
}
