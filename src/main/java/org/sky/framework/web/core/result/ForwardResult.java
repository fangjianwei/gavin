package org.sky.framework.web.core.result;

import org.sky.framework.common.enumeration.ResultType;

public class ForwardResult implements Result{
	
	private static final String defaultEncoding = "utf-8";
	private static final String defaultContentType = "text/html";
	
	private String encoding;
	private String jumpURL;
	private String contentType;
	
	public ForwardResult(String url){
		this.jumpURL = url;
	}
	
	public ForwardResult(String url,String encoding){
		this.jumpURL = url;
		this.encoding = encoding;
	}
	
	
	public ForwardResult(String url,String encoding,String contentType){
		this.jumpURL = url;
		this.encoding = encoding;
		this.contentType = contentType;
	}	

	public String getResult() {
		return this.jumpURL;
	}

	public String getResultType() {
		return ResultType.forward.getValue();
	}
	
	public String getCharacterEncoding() {
		String characterEncoding = defaultEncoding;
		if( encoding!=null&&!"".equals(encoding) ){
			characterEncoding = encoding;
		}
		return characterEncoding;
	}	
	
	public String getContentType() {
		String currentContentType = defaultContentType;
		if( contentType!=null&&!"".equals(contentType) ){
			currentContentType = this.contentType;
		}
		return currentContentType;
	}

}
