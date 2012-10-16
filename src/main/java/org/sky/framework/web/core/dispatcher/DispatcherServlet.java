package org.sky.framework.web.core.dispatcher;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sky.framework.common.proxy.BeanFactory;
import org.sky.framework.web.helper.DispatcherHelper;

public class DispatcherServlet extends HttpServlet{

	/***/
	private static final long serialVersionUID = 1L;
	
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String invokeMapping = DispatcherHelper.getInvokeMapping(request);
		String[] resourcePaths = invokeMapping.split(".");
		if( resourcePaths.length!=3 ){
			//TODO redirect to error page
			throw new ServletException("request error");
		}
		
		String actionId = resourcePaths[0];
		String methodName = resourcePaths[1];
		
		Object action = BeanFactory.getControlledBean(actionId);
		
		Method method = DispatcherHelper.getMethodByName(methodName, action.getClass());
		if( method==null ){
			//TODO redirect to error page
			throw new ServletException("request error,can not find '" + methodName + "' method in '" + actionId + "' servlet");
		}

		DispatcherHelper.combineServletTranslator(request, response);
		
		Class<?>[] parameterClasses = method.getParameterTypes();
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		Object[] paramterObjects = DispatcherHelper.convertMethodParam(parameterClasses,uri,contextPath);
		
		try {
			
			if( !method.isAccessible() ){
				method.setAccessible(true);
			}
			
			method.invoke(action,paramterObjects);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
    }
          
}
