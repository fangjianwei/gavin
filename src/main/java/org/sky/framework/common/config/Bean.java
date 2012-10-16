package org.sky.framework.common.config;

import java.util.HashSet;
import java.util.Set;

public class Bean {
	private String id;
	
	private String clazz;
	
	private String scope;
	
	private Set<BeanProperty> propertys;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Set<BeanProperty> getPropertys() {
		return propertys;
	}

	public void setPropertys(Set<BeanProperty> propertys) {
		this.propertys = propertys;
	}
	
	public void addProperty( BeanProperty property ){
		System.out.println("+++++++++++"+property.getName());
		if( this.propertys==null ){
			this.propertys = new HashSet<BeanProperty>();
		}
		this.propertys.add(property);
	}
	
	
}
