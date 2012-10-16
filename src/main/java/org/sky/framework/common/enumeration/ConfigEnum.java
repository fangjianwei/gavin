package org.sky.framework.common.enumeration;

public enum ConfigEnum {
	bean("bean"),
	beanId("id"),
	beanClazz("clazz"),
	beanScope("scope"),
	
	property("property"),
	propertyName("name"),
	propertyValue("value"),
	propertyType("type"),
	
	aop("aop"),
	aopRule("rule"),
	
	annotation("annotation"),
	scan("scan"),
	scanBasePackage("basepackage");
	
	private String value;
	
	private ConfigEnum( String value ){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
}
