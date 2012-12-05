package org.sky.framework.dao.dto;


public class Col {
	private String name;
	
	private RefDB refDB;
	
	private boolean trans;
	
	private String transientValue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RefDB getRefDB() {
		return refDB;
	}

	public void setRefDB(RefDB refDB) {
		this.refDB = refDB;
	}

	public boolean isTrans() {
		return trans;
	}

	public void setTrans(boolean trans) {
		this.trans = trans;
	}

	public String getTransientValue() {
		return transientValue;
	}

	public void setTransientValue(String transientValue) {
		this.transientValue = transientValue;
	}	
}
