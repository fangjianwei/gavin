package org.sky.framework.web.core.result;

import org.sky.framework.common.enumeration.ResultType;

public class RedirectResult implements Result{

	private static final String utf8 = "utf-8";
	
	private String jumpURL;
	
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
		return utf8;
	}
	
	

}
