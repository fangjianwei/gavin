package org.sky.framework.web.core.dispatcher;

import static org.easymock.EasyMock.*;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hamcrest.core.IsAnything;
import org.junit.Before;
import org.junit.Test;
public class DispatcherServletTest{
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	@Before
	public void setUp(){
		request = createMock(HttpServletRequest.class);
		response = createMock(HttpServletResponse.class);
	}
	
	@Test
	public void resopnseRedirect() throws Exception{
		String encoding = "utf-8";
    	String uri = "/test/ddd.redirect.do/322/22";
    	String url = "http://localhost:8080/test/dd.redirect.do/322/22";
    	String contextPath = "/test";
    	
		//EasyMock.expect(request.getParameterMap()).andReturn(params);
		expect(request.getContextPath()).andReturn(contextPath).anyTimes();
		expect(request.getRequestURI()).andReturn(uri).anyTimes();
		expect(request.getRequestURL()).andReturn(new StringBuffer(url)).anyTimes();
		expect(request.getParameterMap()).andReturn(null).anyTimes();
		
		expect(response.getCharacterEncoding()).andReturn(encoding).anyTimes();

		response.sendRedirect(isA(String.class));
		expectLastCall().anyTimes();
		
		replay(request);
		replay(response);
		
		DispatcherServlet se = new DispatcherServlet();
		se.service(request, response);

	}
	
//	@Test
//	public void resopnseForward() throws Exception{
//		String encoding = "utf-8";
//    	String uri = "/test/ddd.redirect.do/322/22";
//    	String url = "http://localhost:8080/test/dd.redirect.do/322/22";
//    	String contextPath = "/test";
//    	
//		//EasyMock.expect(request.getParameterMap()).andReturn(params);
//		expect(request.getContextPath()).andReturn(contextPath).anyTimes();
//		expect(request.getRequestURI()).andReturn(uri).anyTimes();
//		expect(request.getRequestURL()).andReturn(new StringBuffer(url)).anyTimes();
//		expect(request.getParameterMap()).andReturn(null).anyTimes();
//		
//		expect(response.getCharacterEncoding()).andReturn(encoding);
//
//		response.sendRedirect(isA(String.class));
//		expectLastCall().anyTimes();
//		
//		replay(request);
//		replay(response);
//		
//		DispatcherServlet se = new DispatcherServlet();
//		se.service(request, response);
//
//	}
	
	@Test
	public void resopnseJsonOutprint() throws Exception{
		String encoding = "utf-8";
    	String uri = "/test/ddd.jsonOutprint.do/first/20";
    	String url = "http://localhost:8080/test/ddd.jsonOutprint.do/first/20";
    	String contextPath = "/test";
		
		Map<String,String[]> params = new HashMap<String, String[]>();
		params.put("name",new String[]{"fangjianwei"});
		params.put("age", new String[]{"30"});
		params.put("add", new String[]{"厦门30 street"});
    	
		//EasyMock.expect(request.getParameterMap()).andReturn(params);
		expect(request.getContextPath()).andReturn(contextPath).anyTimes();
		expect(request.getRequestURI()).andReturn(uri).anyTimes();
		expect(request.getRequestURL()).andReturn(new StringBuffer(url)).anyTimes();
		expect(request.getParameterMap()).andReturn(params).anyTimes();
		
		expect(response.getCharacterEncoding()).andReturn(encoding).anyTimes();

		OutputStream out = createMock(OutputStream.class);
		expect(response.getWriter()).andReturn(new PrintWriter(out));
		
		response.setCharacterEncoding(isA(String.class));
		expectLastCall();
		
		replay(request);
		replay(response);
		
		DispatcherServlet se = new DispatcherServlet();
		se.service(request, response);

	}
}
