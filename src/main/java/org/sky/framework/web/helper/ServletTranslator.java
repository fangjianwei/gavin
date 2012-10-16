package org.sky.framework.web.helper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTranslator implements Serializable{
	
	/***/
	private static final long serialVersionUID = 1L;

	static ThreadLocal<ServletTranslator> servletTranslator = new ThreadLocal<ServletTranslator>();
	
	private static final String HTTP_REQUEST = "org.sky.framework.httprequest";
	private static final String HTTP_RESPONSE = "org.sky.framework.httpresponse";
	private static final String SESSION = "org.sky.framework.session";
	private static final String PARAMETERS = "org.sky.framework.paramters";
	
	private Map<String,Object> context = new HashMap<String, Object>();
	
	public HttpServletRequest getRequest(){
		return (HttpServletRequest) get( HTTP_REQUEST );
	}
	
	public void setRequest( HttpServletRequest request ){
		put( HTTP_REQUEST,request );
	}
	
	public HttpServletResponse getResponse(){
		return (HttpServletResponse) get( HTTP_RESPONSE ); 
	}
	
	public void setResponse( HttpServletResponse response ){
		put( HTTP_RESPONSE,response );
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> getSession(){
		return (Map<String,Object>)get( SESSION );
	}
	
	public void setSession( Map<String,Object> session ){
		put( SESSION,session );
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,String[]> getParameters(){
        return (Map<String, String[]>) get(PARAMETERS);
	}
	
	public void setParameters( Map<String,String[]> params ){
		put(PARAMETERS,params);
	}
	
	public Object get( String key ){
		return context.get(key);
	}
	
	public void put( String key,Object obj ){
		context.put(key, obj);
	}
	
	public static ServletTranslator getTranslator(){
		return (ServletTranslator) servletTranslator.get();
	}
	
	public static void setTranslator( ServletTranslator translator ){
		servletTranslator.set(translator);
	}
	
	@SuppressWarnings("unchecked")
	public ServletTranslator init( HttpServletRequest request,HttpServletResponse response ){
		this.setRequest(request);
		this.setResponse(response);
		this.setParameters(request.getParameterMap());
		ServletTranslator.setTranslator(this);
		return this;
	}
}
