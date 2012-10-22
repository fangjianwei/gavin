package org.sky.framework.web.core.dispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easymock.EasyMock;

public class DispatcherServletTest {
	
	public void testResopnseRedirect(){
		String encoding = "utf-8";
    	String uri = "/test/login.jsp.do/322/22";
    	String url = "http://localhost:8080/test/login.jsp.do/322/22";
    	String contextPath = "/test";
		
		HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
		HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
		
		
		//EasyMock.expect(request.getParameterMap()).andReturn(params);
		EasyMock.expect(request.getContextPath()).andReturn(contextPath);
		EasyMock.expect(request.getRequestURI()).andReturn(uri);
		EasyMock.expect(request.getRequestURL()).andReturn(new StringBuffer(url));
		
		EasyMock.expect(response.getCharacterEncoding()).andReturn(encoding);
		
		
		EasyMock.replay(request);
		EasyMock.replay(response);
		
	}
	
}
