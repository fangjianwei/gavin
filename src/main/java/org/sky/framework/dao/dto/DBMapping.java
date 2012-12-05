package org.sky.framework.dao.dto;

import java.util.HashMap;
import java.util.Map;

public class DBMapping {
	
	private Map<String,PrimaryKey> primaryKeys;
	
	private String tableName;
	
	private Map<String,Col> columns;
		
	public Map<String,PrimaryKey> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(Map<String,PrimaryKey> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public void addPrimaryKey(String key,PrimaryKey primaryKey) {
		if( this.primaryKeys==null ){
			this.primaryKeys = new HashMap<String,PrimaryKey>();
		}
		
		this.primaryKeys.put(key,primaryKey);
	}

	public Map<String,Col> getColumns() {
		return columns;
	}

	public void setColumns(Map<String,Col> columns) {
		this.columns = columns;
	}
	
	public void addColumn(String key,Col column) {
		if( this.columns==null ){
			this.columns = new HashMap<String,Col>();
		}
		
		this.columns.put(key,column);
	}
	
	public boolean validate(){
		boolean valid = true;
		
		if(this.tableName==null||"".equals(this.tableName)){
			valid = false;
		}else if( this.columns==null||this.columns.size()==0 ){
			valid = false;
		}
		
		return valid;
	}
	
}
