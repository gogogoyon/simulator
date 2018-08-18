package com.samlic.emulator.core;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.samlic.emulator.entity.InterfaceCase;

public class HttpDispatchServlet  extends HttpServlet {

	private static final long serialVersionUID = -515214332751283547L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		OutputForwarder forder = new DefaultOutputForwarder(response);
		HttpRequestResolver requestResolver = new HttpRequestResolver(request);
		InterfaceCase interfaceCase = InterfaceCaseManager.findInterfaceCase(requestResolver);
		if(interfaceCase != null) {
			forder = new InterfaceCaseOutputForwarder(requestResolver, response, interfaceCase);
		}
		
		forder.forward();
	}
}
