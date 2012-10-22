package org.sky.framework.web.core.result;

import org.sky.framework.common.enumeration.ResultType;

import com.google.gson.Gson;

public class JSONResult implements Result{
	
	public JSONResult( Object obj,String encoding ){
		this.obj = obj;
		this.characterEncoding = encoding;
	}

	private String characterEncoding;
	private Object obj;
	
	public String getCharacterEncoding() {
		return this.characterEncoding;
	}

	public String getResult() {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	public String getResultType() {
		return ResultType.outprint.getValue();
	}

	public void setCharacterEncoding( String encoding ){
		this.characterEncoding = encoding;
	}
	
}
