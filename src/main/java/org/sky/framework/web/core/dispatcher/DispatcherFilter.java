package org.sky.framework.web.core.dispatcher;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class DispatcherFilter implements Filter{
	
	public void init( FilterConfig config ) throws ServletException {
		// TODO Auto-generated method stub
		
	}	

	public void doFilter( ServletRequest req, ServletResponse resp,
			FilterChain chain ) throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		
		if( StringUtils.isBlank(contextPath) ){
			
		}
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
