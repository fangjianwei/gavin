package org.sky.framework.dao.dto;

public class RefDB {
	private String refName;
	
	private String[] refKey;
	
	private String[] targetKey;

	private String otherConditions;

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public String[] getRefKey() {
		return refKey;
	}

	public void setRefKey(String[] refKey) {
		this.refKey = refKey;
	}
	
	public String[] getTargetKey() {
		return targetKey;
	}

	public void setTargetKey(String[] targetKey) {
		this.targetKey = targetKey;
	}

	public String getOtherConditions() {
		return otherConditions;
	}

	public void setOtherConditions(String otherConditions) {
		this.otherConditions = otherConditions;
	}
}
