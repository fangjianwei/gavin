package org.sky.framework.web.core.result;

import org.sky.framework.common.enumeration.ResultType;

public class ForwardResult implements Result{
	
	private static final String utf8 = "utf-8";

	private String jumpURL;
	
	public ForwardResult(String url){
		this.jumpURL = url;
	}
	
	public String getCharacterEncoding() {
		return utf8;
	}

	public String getResult() {
		return this.jumpURL;
	}

	public String getResultType() {
		return ResultType.forward.getValue();
	}

}
