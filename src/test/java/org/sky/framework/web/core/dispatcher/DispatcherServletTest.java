package org.sky.framework.web.core.dispatcher;

import static org.easymock.EasyMock.*;


import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.sky.framework.web.core.result.Result;

public class DispatcherServletTest{
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	@Before
	public void setUp(){
		request = createMock(HttpServletRequest.class);
		response = createMock(HttpServletResponse.class);
	}
	
	@Test
	public void responseRedirect() throws Exception{
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
	
	@Test
	public void responseForward() throws Exception{
		String encoding = "utf-8";
    	String uri = "/test/ddd.forward.do/322/22";
    	String url = "http://localhost:8080/test/dd.forward.do/322/22";
    	String contextPath = "/test";
    	
		//EasyMock.expect(request.getParameterMap()).andReturn(params);
		expect(request.getContextPath()).andReturn(contextPath).anyTimes();
		expect(request.getRequestURI()).andReturn(uri).anyTimes();
		expect(request.getRequestURL()).andReturn(new StringBuffer(url)).anyTimes();
		expect(request.getParameterMap()).andReturn(null).anyTimes();
		
		expect(response.getCharacterEncoding()).andReturn(encoding);

		RequestDispatcher dispatcher = createMock(RequestDispatcher.class);
		dispatcher.forward(request, response);
		//expectLastCall().anyTimes();
		
		expect(request.getRequestDispatcher(isA(String.class))).andReturn(dispatcher);
		
		response.sendRedirect(isA(String.class));
		//expectLastCall().anyTimes();
		
		response.setCharacterEncoding(isA(String.class));
		//expectLastCall().anyTimes();
		
		response.setContentType(isA(String.class));
		expectLastCall().anyTimes();
		
		replay(request);
		replay(response);
		
		DispatcherServlet se = new DispatcherServlet();
		se.service(request, response);

	}
	
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
		//expectLastCall();
		
		response.setContentType(isA(String.class));
		expectLastCall().anyTimes();
		
		replay(request);
		replay(response);
		
		DispatcherServlet se = new DispatcherServlet();
		se.service(request, response);
		
		Result result = se.dispatcherService(request, response);
		String json = "{\"name\":\"fangjianwei\",\"age\":30,\"add\":\"厦门30 street\"}";
		Assert.assertEquals(json, result.getResult());
	}
	
	@Test
	public void resopnseXMLOutprint() throws Exception{
		String encoding = "utf-8";
    	String uri = "/test/ddd.xmlOutprint.do/first/20";
    	String url = "http://localhost:8080/test/ddd.xmlOutprint.do/first/20";
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
		//expectLastCall().anyTimes();
		
		response.setContentType(isA(String.class));
		expectLastCall().anyTimes();
		
		replay(request);
		replay(response);
		
		DispatcherServlet se = new DispatcherServlet();
		se.service(request, response);
		
		Result result = se.dispatcherService(request, response);
		String json = "<?xml version='1.0' encoding='utf-8'?><TestBean><add><![CDATA[厦门30 street]]></add><age>30</age><name><![CDATA[fangjianwei]]></name></TestBean>";
		Assert.assertEquals(json, result.getResult());
	}
	
}
