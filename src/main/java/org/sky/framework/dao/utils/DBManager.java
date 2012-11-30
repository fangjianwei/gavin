package org.sky.framework.dao.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager {
	private final static String configFile = "datasource.properties";
	
	private final static String primaryKey = "primary.datasource";
	
	private static Map<String,DataSource> dataSourcePool = new HashMap<String, DataSource>(); 
	
	private static Properties datasourceConfig = new Properties();
	
	private static DBManager dbHelperInstance = null;

	private DBManager(){
	}
	
	static{
		try {
			loadConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DBManager getInstance(){
		if( dbHelperInstance==null ){
			synchronized (dbHelperInstance) {
				if( dbHelperInstance==null ){
					dbHelperInstance = new DBManager();
					dbHelperInstance.init();
				}
			}
		}
		return dbHelperInstance;
	}
	

	private static void loadConfig() throws Exception{
		InputStream in = DBManager.class.getClassLoader().getResourceAsStream(configFile);
		if( in==null ){
			throw new Exception("can not find datasource.properties file");
		}
		
		datasourceConfig.load(in);
	}
	
	private void init(){
		Set<Entry<Object, Object>> set = datasourceConfig.entrySet();
		for( Entry<Object, Object> entry:set ){
			String key = (String) entry.getKey();
			String jndiName = (String) entry.getValue();
			
			if( primaryKey.equals(key.trim()) ) continue;
			
			this.createDataSource(jndiName.trim());
		}
	}
	
	/**
	 * 从dataSourcePool中获取DataSource，如果没有则建立DataSource
	 * 
	 * @param jndiName
	 * @return
	 * @throws NamingException
	 */
	public DataSource getDataSource( String jndiName )  {
		DataSource datasource = findDataSource(jndiName);
		
		if( datasource==null ){
			datasource = createDataSource(jndiName);
		}
		
		return datasource;
	}
	
	public DataSource findDataSource( String jndiName ){
		DataSource datasource = dataSourcePool.get(jndiName);
		return datasource;
	}
	
	private synchronized DataSource createDataSource( String jndiName ){
		DataSource datasource = null;
		try {
			
			datasource = dataSourcePool.get(jndiName);
			if( datasource==null ){
				// 初始化查找命名空间  
				Context initContext = new InitialContext();  
				datasource = (DataSource) initContext.lookup(jndiName);
				
				addSourceToPool(jndiName, datasource);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return datasource;
	}
	
	public void addSourceToPool( String jndiName,DataSource datasource ){
		dataSourcePool.put(jndiName, datasource);
	}
	
	public Connection getPrimaryConnection() throws SQLException{
		String jndiName = (String) datasourceConfig.get(primaryKey);
		return this.getConnection(jndiName);
	}
	
	public Connection getConnection( String jndiName ) throws SQLException{
		DataSource datasource = this.getDataSource(jndiName);
		Connection conn = null;
		if( datasource!=null ){
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
		}
		return conn;
	}

}
