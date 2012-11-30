package org.sky.framework.dao.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sky.framework.common.utils.GavinBeanUtils;

public class DBHelper {
	
//	public static void ObjectToDBColumn( Object obj ){
//		
//	}
	
	public static DBMapping combination( Class<?> clazz ){
		//TODO auto combination
		DBMapping mapping = new DBMapping();

		Field[] fields = clazz.getDeclaredFields();
		if(fields==null||fields.length==0) return mapping; 
		
		for( Field field:fields ){
			
		}
		
		return mapping;
	}
	
	
	public static void setPreparedStatementValue( PreparedStatement ps,Object[] params ) throws SQLException{
		if( params==null||params.length==0 ) return;
		
		for( int i=0;i<params.length;i++ ){
			Object param = params[i];
			String className = param.getClass().getName();
			
			if( GavinBeanUtils.isString(className) ){
				ps.setString(i+1, (String)param);
			}else if( GavinBeanUtils.isInt(className)||GavinBeanUtils.isInteger(className) ){
				ps.setInt(i+1, (Integer) param);
			}else if( GavinBeanUtils.isFloatBase(className)||GavinBeanUtils.isFloat(className) ){
				ps.setFloat(i+1, (Float) param);
			}else if( GavinBeanUtils.isDoubleBase(className)||GavinBeanUtils.isDouble(className) ){
				ps.setDouble(i+1, (Double) param);
			}else if( GavinBeanUtils.isLongBase(className)||GavinBeanUtils.isLong(className) ){
				ps.setLong(i+1, (Long) param);
			}else if( GavinBeanUtils.isShortBase(className)||GavinBeanUtils.isShort(className) ){
				ps.setShort(i+1, (Short)param);
			}else if( GavinBeanUtils.isByteBase(className)||GavinBeanUtils.isByte(className) ){
				ps.setByte(i+1, (Byte)param);
			}else if( GavinBeanUtils.isByteBaseArray(className)||GavinBeanUtils.isByteArray(className) ){
				ps.setBytes(i+1, (byte[])param);
			}else if( GavinBeanUtils.isBooleanBase(className)||GavinBeanUtils.isBoolean(className) ){
				ps.setBoolean(i+1, (Boolean)param);
			}else if( GavinBeanUtils.isBigDecimal(className) ){
				ps.setBigDecimal(i+1, (BigDecimal)param);
			}else{
				ps.setObject(i+1, param);
			}
			
		}
	}

	public static void close( Connection conn ){
		try {
			if( conn!=null ) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close( Connection conn,ResultSet rs,PreparedStatement ps ){
		try {
			if( conn!=null ) conn.close();
			if( rs!=null ) rs.close();
			if( ps!=null ) ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close( Connection conn,PreparedStatement ps ){
		try {
			if( conn!=null ) conn.close();
			if( ps!=null ) ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close( ResultSet rs,PreparedStatement ps ){
		try {
			if( rs!=null ) rs.close();
			if( ps!=null ) ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close( Connection conn,ResultSet rs,Statement st){
		try {
			if( conn!=null ) conn.close();
			if( rs!=null ) rs.close();
			if( st!=null ) st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close( ResultSet rs,Statement st){
		try {
			if( rs!=null ) rs.close();
			if( st!=null ) st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close( ResultSet rs ){
		try {
			if( rs!=null ) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close( Statement st ){
		try {
			if( st!=null ) st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close( PreparedStatement ps ){
		try {
			if( ps!=null ) ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback( Connection conn ){
		try {
			if(conn!=null) conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public static void main( String[] args ){
//		char[] cc = new char[2];
//		System.out.println(cc.getClass().getName());
//	}
	
}
