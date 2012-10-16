package org.sky.framework.common.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Configuration {
	private boolean annotation;
	
	private Map<String, Bean> beans;
	
	private Map<String,Bean> actions;
	
	private Set<String> scans;
	
	private Set<Aop> aops;

	public boolean isAnnotation() {
		return annotation;
	}

	public void setAnnotation(boolean annotation) {
		this.annotation = annotation;
	}

	public Map<String, Bean> getBeans() {
		return beans;
	}

	public void setBeans(Map<String, Bean> beans) {
		this.beans = beans;
	}
	
	public void addBean( Bean bean ){
		if( this.beans==null ){
			this.beans = new HashMap<String, Bean>();
		}
		this.beans.put(bean.getId(), bean);
	}
	
	public Bean getBean( String id ){
		if(beans==null) return null;
		return beans.get(id);
	}

	public Set<String> getScans() {
		return scans;
	}

	public void setScans(Set<String> scans) {
		this.scans = scans;
	}
	
	public void addScan( String scan ){
		if( this.scans==null ){
			this.scans = new HashSet<String>();
		}
		this.scans.add(scan);
	}

	public Map<String, Bean> getActions() {
		return actions;
	}

	public void setActions(Map<String, Bean> actions) {
		this.actions = actions;
	}

	public void addAction( Bean bean ){
		if( this.actions==null ){
			this.actions = new HashMap<String, Bean>();
		}
		this.actions.put(bean.getId(), bean);
	}
	
	public Bean getAction( String id ){
		if( actions==null ) return null;
		return this.actions.get(id);
	}

	public Set<Aop> getAops() {
		return aops;
	}

	public void setAops(Set<Aop> aops) {
		this.aops = aops;
	}
	
	public void addAop( Aop aop ){
		if( this.aops==null ){
			this.aops = new HashSet<Aop>();
		}
		this.aops.add(aop);
	}
}
