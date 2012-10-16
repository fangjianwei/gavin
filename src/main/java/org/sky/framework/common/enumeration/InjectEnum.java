package org.sky.framework.common.enumeration;

public enum InjectEnum {
	byType("byType"),
	byId("byId");
	
	private InjectEnum( String value ){
		this.value = value;
	}
	
	private String value;
	
	public String getValue(){
		return this.value;
	}
}
