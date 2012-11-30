package org.sky.framework.dao.utils;

import java.util.Set;

public class DBMapping {
	
	private Set<PrimaryKey> primaryKeys;
	
	private String tableName;
	
	private Set<Column> cloumns;
		
	public Set<PrimaryKey> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(Set<PrimaryKey> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Set<Column> getCloumns() {
		return cloumns;
	}

	public void setCloumns(Set<Column> cloumns) {
		this.cloumns = cloumns;
	}

	class PrimaryKey{
		
		private String name;
		
		private String type;
		
		private String clazz;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getClazz() {
			return clazz;
		}

		public void setClazz(String clazz) {
			this.clazz = clazz;
		}
	}
	
	class Column{
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
	
	class RefDB{
		private String name;
		
		private String refName;
		
		private String refKey;

		private String otherConditions;
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getRefName() {
			return refName;
		}

		public void setRefName(String refName) {
			this.refName = refName;
		}

		public String getRefKey() {
			return refKey;
		}

		public void setRefKey(String refKey) {
			this.refKey = refKey;
		}

		public String getOtherConditions() {
			return otherConditions;
		}

		public void setOtherConditions(String otherConditions) {
			this.otherConditions = otherConditions;
		}
	}
	
}
