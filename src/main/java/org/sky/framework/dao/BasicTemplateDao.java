package org.sky.framework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.sky.framework.common.dto.PageFilter;
import org.sky.framework.dao.utils.DBHelper;
import org.sky.framework.dao.utils.DBManager;

@SuppressWarnings({"rawtypes","unchecked"})
public class BasicTemplateDao implements BasicDao{

	public void deleteById(Object id,Class clazz) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DBManager.getInstance().getPrimaryConnection();
			String sql = "delete from xxx where xx=?";
			ps = conn.prepareStatement(sql);
			DBHelper.setPreparedStatementValue(ps, new Object[]{id});
			ps.executeUpdate();
			conn.commit();
		}catch( Exception e ){
			DBHelper.rollback(conn);
			throw e;
		}finally{
			DBHelper.close(conn,ps);
		}
		
	}
	
	public void delete(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(Connection conn, Object id,Class clazz) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void delete(Connection con, Object obj) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public Object selectById(Connection conn, Object id, Class clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Object selectById(Object id, Class clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Map selectByIdForMap(Connection conn, Object id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Map selectByIdForMap(Object id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Object selectOne(Connection conn, String sql, Class clazz)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Object selectOne(String sql, Class clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Object selectOne(Connection conn, String sql, Object[] param,
			Class clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Object selectOne(String sql, Object[] param, Class clazz)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Map selectOneForMap(Connection conn, String sql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Map selectOneForMap(String sql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Map selectOneForMap(Connection conn, String sql, Object[] param)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Map selectOneForMap(String sql, Object[] param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public  List selectAll(Connection conn, Class clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public  List selectAll(Class clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public  Map selectAllForMap(Connection conn, Class clazz)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public  Map selectAllForMap(Class clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public  List selectQuery(Connection conn, String sql, Class clazz)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public  List selectQuery(String sql, Class clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public  List selectQuery(Connection conn, String sql, Object[] param,
			Class clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public  List selectQuery(String sql, Object[] param, Class clazz)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List selectQuery(Connection conn, String sql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List selectQuery(String sql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List selectQuery(Connection conn, String sql, Object[] param)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List selectQuery(String sql, Object[] param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultSet executeQuery(Connection conn, String sql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultSet executeQuery(Connection conn, String sql, Object[] param)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void executeUpdate(Connection conn, String sql) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void executeUpdate(String sql) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void executeUpdate(Connection conn, String sql, Object[] param)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void executeUpdate(String sql, Object[] param) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void update(Connection conn, Object obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void update(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public  List selectPage(Connection conn, String sql,
			PageFilter pageFilter, Class clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public  List selectPage(String sql, PageFilter pageFilter, Class clazz)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List selectPage(Connection conn, String sql, PageFilter pageFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List selectPage(String sql, PageFilter pageFilter) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public  List selectPage(Connection conn, String sql, Object[] param,
			PageFilter pageFilter, Class clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public  List selectPage(String sql, Object[] param,
			PageFilter pageFilter, Class clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List selectPage(Connection conn, String sql, Object[] param,
			PageFilter pageFilter) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List selectPage(String sql, Object[] param, PageFilter pageFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}	
	
	public <T> T insert(Connection conn, T t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T insert(T t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T save(Connection conn, T t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T save(T t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
