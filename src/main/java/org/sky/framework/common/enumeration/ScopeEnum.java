package org.sky.framework.common.enumeration;

public enum ScopeEnum {
	singleton("singleton"),
	prototype("prototype");
	
	public final String value;
	
	private ScopeEnum( String value ){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
