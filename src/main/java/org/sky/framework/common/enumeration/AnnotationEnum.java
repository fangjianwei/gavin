package org.sky.framework.common.enumeration;


public enum AnnotationEnum {
	Service("Service"),
	Controller("Controller"),
	Repository("Repository"),
	
	Aop("Aop"),
	Inject("Inject");
	
	
	private AnnotationEnum( String value ){
		this.value = value;
	}
	
	private String value;
	
	public String getValue(){
		return this.value;
	}
}
