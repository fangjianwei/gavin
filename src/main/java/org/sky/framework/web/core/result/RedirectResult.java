package org.sky.framework.web.core.result;

import org.sky.framework.common.enumeration.ResultType;

public class RedirectResult implements Result{

	private static final String defaultEncoding = "utf-8";
	private static final String defaultContentType = "text/html";
	
	private String encoding;
	private String jumpURL;
	private String contentType;
	
	public RedirectResult( String jumpURL ){
		this.jumpURL = jumpURL;
	}
	
	public String getResultType() {
		return ResultType.redirect.getValue();
	}

	public String getResult() {
		return this.jumpURL;
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
