package org.sky.framework.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.sky.framework.common.dto.PageFilter;


public interface BasicDao {

	public void deleteById( Connection conn,Object id,Class<?> clazz ) throws Exception;
	
	public void deleteById( Object id,Class<?> clazz ) throws Exception;
	
	public void delete( Connection con,Object obj ) throws Exception;
	
	public void delete( Object obj ) throws Exception;
	
	public Object selectById( Connection conn, Object id,Class<?> clazz ) throws Exception;
	
	public Object selectById( Object id,Class<?> clazz ) throws Exception;
	
	public Map<String,Object> selectByIdForMap( Connection conn, Object id ) throws Exception;
	
	public Map<String,Object> selectByIdForMap( Object id ) throws Exception;	
	
	public Object selectOne( Connection conn,String sql,Class<?> clazz ) throws Exception;
	
	public Object selectOne( String sql,Class<?> clazz ) throws Exception;
	
	public Object selectOne( Connection conn,String sql,Object[] param,Class<?> clazz ) throws Exception;
	
	public Object selectOne( String sql,Object[] param,Class<?> clazz ) throws Exception;	
	
	public Map<String,Object> selectOneForMap( Connection conn,String sql ) throws Exception;
	
	public Map<String,Object> selectOneForMap( String sql ) throws Exception;
	
	public Map<String,Object> selectOneForMap( Connection conn,String sql,Object[] param ) throws Exception;
	
	public Map<String,Object> selectOneForMap( String sql,Object[] param ) throws Exception;

	public List<Object> selectAll( Connection conn,Class<?> clazz ) throws Exception;
	
	public List<Object> selectAll( Class<?> clazz ) throws Exception;

	public Map<String,Object> selectAllForMap( Connection conn,Class<?> clazz ) throws Exception;
	
	public Map<String,Object> selectAllForMap( Class<?> clazz ) throws Exception;
	
	public List<Object> selectQuery( Connection conn, String sql,Class<?> clazz ) throws Exception;
	
	public List<Object> selectQuery( String sql,Class<?> clazz ) throws Exception;
	
	public List<Object> selectQuery( Connection conn, String sql,Object[] param,Class<?> clazz ) throws Exception;
	
	public List<Object> selectQuery( String sql,Object[] param,Class<?> clazz ) throws Exception;	

	public List<Map<String,Object>> selectQuery( Connection conn, String sql ) throws Exception;
	
	public List<Map<String,Object>> selectQuery( String sql ) throws Exception;
	
	public List<Map<String,Object>> selectQuery( Connection conn, String sql,Object[] param ) throws Exception;
	
	public List<Map<String,Object>> selectQuery( String sql,Object[] param ) throws Exception;
	
	public ResultSet executeQuery( Connection conn, String sql ) throws Exception;
	
	public ResultSet executeQuery( Connection conn, String sql,Object[] param ) throws Exception;
	
	public void executeUpdate( Connection conn,String sql ) throws Exception;
	
	public void executeUpdate( String sql ) throws Exception;
	
	public void executeUpdate( Connection conn,String sql,Object[] param ) throws Exception;
	
	public void executeUpdate( String sql,Object[] param ) throws Exception;
	
	public void update( Connection conn,Object obj ) throws Exception;
	
	public void update( Object obj ) throws Exception;
	
	public List<Object> selectPage( Connection conn,String sql,PageFilter pageFilter,Class<?> clazz ) throws Exception;
	
	public List<Object> selectPage( String sql,PageFilter pageFilter,Class<?> clazz ) throws Exception;
	
	public List<Map<String,Object>> selectPage( Connection conn,String sql,PageFilter pageFilter ) throws Exception;
	
	public List<Map<String,Object>> selectPage( String sql,PageFilter pageFilter ) throws Exception;
	
	public List<Object> selectPage( Connection conn,String sql,Object[] param,PageFilter pageFilter,Class<?> clazz ) throws Exception;
	
	public List<Object> selectPage( String sql,Object[] param,PageFilter pageFilter,Class<?> clazz ) throws Exception;
	
	public List<Map<String,Object>> selectPage( Connection conn,String sql,Object[] param,PageFilter pageFilter ) throws Exception;
	
	public List<Map<String,Object>> selectPage( String sql,Object[] param,PageFilter pageFilter ) throws Exception;
	
	public <T> T insert( Connection conn,T t ) throws Exception;
	
	public <T> T insert( T t ) throws Exception;
	
	public <T> T save( Connection conn,T t ) throws Exception;
	
	public <T> T save( T t ) throws Exception;
	
}
