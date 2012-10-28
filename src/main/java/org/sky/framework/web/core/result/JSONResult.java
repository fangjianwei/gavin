package org.sky.framework.web.core.result;

import org.sky.framework.common.enumeration.ResultType;

import com.google.gson.Gson;

public class JSONResult implements Result{
	
	private static final String defaultEncoding = "utf-8";
	
	private String encoding;
	private Object obj;
	private String rootName;
	
	public JSONResult( Object obj,String encoding ){
		this.obj = obj;
		this.encoding = encoding;
	}
	
	public JSONResult( Object obj,String encoding ,String rootName){
		this.obj = obj;
		this.encoding = encoding;
		this.rootName = rootName;
	}
	
	public String getCharacterEncoding() {
		String characterEncoding = defaultEncoding;
		if( encoding!=null&&!"".equals(encoding) ){
			characterEncoding = encoding;
		}
		return characterEncoding;
	}	

	public String getResult() {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		if( this.rootName!=null&&"".equals(this.rootName) ){
			json = "{\"" + this.rootName + "\":" + json + "}";
		}
		return json; 
	}

	public String getResultType() {
		return ResultType.outprint.getValue();
	}
	
	public String getContentType(){
		return "application/json";
	}
	
}
