package org.sky.framework.web.core.dispatcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sky.framework.common.enumeration.ResultType;
import org.sky.framework.common.proxy.BeanFactory;
import org.sky.framework.common.utils.logging.Logger;
import org.sky.framework.common.utils.logging.LoggerFactory;
import org.sky.framework.web.core.result.Result;
import org.sky.framework.web.helper.DispatcherHelper;

public class DispatcherServlet extends HttpServlet{

	private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
	
	/***/
	private static final long serialVersionUID = 1L;
	
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String invokeMapping = DispatcherHelper.getInvokeMapping(request);
		String[] resourcePaths = invokeMapping.split("\\.");
		if( resourcePaths.length!=3 ){
			//TODO redirect to error page
			throw new ServletException("request error,resourcePaths.length="+resourcePaths.length);
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
		
		Class<?> returnType = method.getReturnType();
		
		if( !theMethodResultTypeIsCorrect(returnType) ){
			throw new ServletException("the return type is not correct,it should be the Result.java or void");
		}
		
		Object resultObj = null;
		try {
			
			if( !method.isAccessible() ){
				method.setAccessible(true);
			};
			
			resultObj = method.invoke(action,paramterObjects);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		if("void".equals(returnType.getName())) return;
		
		if(resultObj==null) return;
		
		if( !(resultObj instanceof Result) ) return;
		
		Result result = (Result) resultObj;
		
		if( ResultType.outprint.getValue().equals(result.getResultType()) ){
			outprint(request,response,result);
		}else if( ResultType.redirect.getValue().equals(result.getResultType()) ){
			log.debug("servlet response redirect url:"+result.getResult());
			response.sendRedirect(result.getResult());
		}else if( ResultType.forward.getValue().equals(result.getResultType()) ){
			log.debug("servlet response forward url:"+result.getResult());
			request.getRequestDispatcher(result.getResult()).forward(request, response);
		}

    }
    
    private void outprint( HttpServletRequest request, HttpServletResponse response,Result result ){
    	PrintWriter out = null;
    	if( result.getCharacterEncoding()!=null ){
    		response.setCharacterEncoding(result.getCharacterEncoding());
    	}
    	
    	try {
    		log.debug(result.getResult());
    		
			out = response.getWriter();
			out.print(result.getResult());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
    
    private boolean theMethodResultTypeIsCorrect( Class<?> returnClass ){
  
    	String resultClassName = Result.class.getName();
    	String returnClassName = returnClass.getName();
    	
		if("void".equals(returnClassName)) return true;
    	if( returnClass.isInterface()&&returnClassName.equals(resultClassName) ){
    		return true;
    	}
		
		boolean resultTypeIsCorrect = false;
		Class<?>[] interfaceClass = returnClass.getInterfaces();
		for( Class<?> clazz:interfaceClass ){
			if( clazz.getName().equals(resultClassName) ){
				resultTypeIsCorrect = true;
				break;
			}
		}
		return resultTypeIsCorrect;
    }
          
}
