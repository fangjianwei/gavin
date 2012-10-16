package org.sky.framework.web.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.TestCase;

import org.easymock.EasyMock;

public class DisppatcherHelperTest extends TestCase{

	public void testGetInvokeMapping(){
    	String uri = "/test/login.jsp.do/322/22";
    	String contextPath = "/test";
      	String invokeM = DispatcherHelper.getInvokeMapping(contextPath, uri);
      	assertEquals("login.jsp.do", invokeM);
      	
    	uri = "/login.jsp.do/322/22";
    	contextPath = "";
    	invokeM = DispatcherHelper.getInvokeMapping(contextPath, uri);
    	
    	assertEquals("login.jsp.do", invokeM);
	}
	
	public void testGetMethodByName(){
		Method method= DispatcherHelper.getMethodByName("getMethodByName", DispatcherHelper.class);
		assertNotNull(method);
	}
	
	public void testGetRestFullParam(){
    	String[] obj = DispatcherHelper.getRestFullParam("", "/login.jsp.do/322/22");
    	
    	assertEquals("322", obj[0]);
    	assertEquals("22", obj[1]);
	}
	
	public void testCombineServletTranslator(){
		//数据
		String encoding = "UTF-8";
		String paramName = "names";
		String[] paramValues = {"test1","test2"};
		
		Map<String,String[]> params = new HashMap<String, String[]>();
		params.put(paramName, paramValues);
		//end
		
		HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
		HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
		
		EasyMock.expect(request.getParameterMap()).andReturn(params);
		EasyMock.expect(response.getCharacterEncoding()).andReturn(encoding);
		
		EasyMock.replay(request);
		EasyMock.replay(response);
				
		DispatcherHelper.combineServletTranslator(request, response);
		ServletTranslator transtor = ServletTranslator.getTranslator();
		String[] transtorRequestParamValues = transtor.getParameters().get(paramName);
		
		assertEquals("test1", transtorRequestParamValues[0]);
		assertEquals("test2", transtorRequestParamValues[1]);
		assertEquals(encoding, transtor.getResponse().getCharacterEncoding());
		
	}
	
	public void testSetParamToBean(){
		String paramName = "names";
		String[] paramValues = {"test1","test2"};
		
		Map<String,String[]> params = new HashMap<String, String[]>();
		params.put(paramName, paramValues);
		ServletTranslator translator = new ServletTranslator();
		translator.setParameters(params);
		
		DispatcherHelperBeanForTest testBean = (DispatcherHelperBeanForTest) DispatcherHelper.setParamToBean(DispatcherHelperBeanForTest.class, translator);
		assertNotNull(testBean);
		assertEquals("test1", testBean.getNames().get(0));
		assertEquals("test2", testBean.getNames().get(1));
	}
	
	public void testConvertMethodParam(){
		String paramName = "names";
		String[] paramValues = {"test1","test2"};
		Map<String,String[]> params = new HashMap<String, String[]>();
		params.put(paramName, paramValues);
		ServletTranslator translator = new ServletTranslator();
		translator.setParameters(params);
		
		Class<?>[] parameterClasses = {java.lang.String.class,java.lang.String.class,java.lang.Integer.class,DispatcherHelperBeanForTest.class};
		Object[] objs = DispatcherHelper.convertMethodParam(parameterClasses,"/login.login.do/fangjianwei/avi22/122","");
		assertEquals("fangjianwei", objs[0]);
		assertEquals("avi22", objs[1]);
		assertEquals(new Integer(122), objs[2]);
		
		DispatcherHelperBeanForTest bean = (DispatcherHelperBeanForTest) objs[3];
		
		assertEquals("test1", bean.getNames().get(0));
		assertEquals("test2", bean.getNames().get(1));
	}
	
	public void testConvertMethodParam2(){
		String paramName = "names";
		String[] paramValues = {"test1","test2"};
		Map<String,String[]> params = new HashMap<String, String[]>();
		params.put(paramName, paramValues);
		ServletTranslator translator = new ServletTranslator();
		translator.setParameters(params);
		
		Class<?>[] parameterClasses = {java.lang.String.class,java.lang.String.class,java.lang.Integer.class,java.lang.String.class};
		Object[] objs = DispatcherHelper.convertMethodParam(parameterClasses,"/login.login.do/fangjianwei/avi22/122","");
		assertEquals("fangjianwei", objs[0]);
		assertEquals("avi22", objs[1]);
		assertEquals(new Integer(122), objs[2]);
		assertNull(objs[3]);
	}
}
