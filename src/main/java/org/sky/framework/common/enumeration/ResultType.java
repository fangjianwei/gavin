package org.sky.framework.common.enumeration;

public enum ResultType {
	redirect("redirect"),
	forward("forward"),
	outprint("outprint");
	
	private String value;
	
	private ResultType( String value ){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
