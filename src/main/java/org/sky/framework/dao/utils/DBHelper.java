package org.sky.framework.dao.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sky.framework.common.utils.GavinBeanUtils;
import org.sky.framework.dao.annotation.Column;
import org.sky.framework.dao.annotation.Id;
import org.sky.framework.dao.annotation.Ref;
import org.sky.framework.dao.annotation.Table;
import org.sky.framework.dao.annotation.Transient;
import org.sky.framework.dao.dto.Col;
import org.sky.framework.dao.dto.DBMapping;
import org.sky.framework.dao.dto.PrimaryKey;
import org.sky.framework.dao.dto.RefDB;

public class DBHelper {
	
//	public static void ObjectToDBColumn( Object obj ){
//		
//	}
	/**
	 * 根据annotation提取数据库映射信息。
	 * @param clazz
	 * @return
	 * @throws Exception 
	 */
	public static DBMapping combinationDBMapping( Class<?> clazz ) throws Exception{
		DBMapping mapping = new DBMapping();

		Field[] fields = clazz.getDeclaredFields();
		if(fields==null||fields.length==0) return mapping; 
		
		String tablename = clazz.getSimpleName();;
		Table table = clazz.getAnnotation(Table.class);
		if( table!=null&&!"".equals(table.value().trim()) ){
			tablename = table.value().trim();
		}
		
		mapping.setTableName(tablename);
		
		for( Field field:fields ){
			String fieldName = field.getName();
			Transient trans = field.getAnnotation(Transient.class);
			Id idAnnotation = field.getAnnotation(Id.class);
			Column columnAnnotation = field.getAnnotation(Column.class);
			Ref refDBAnnotation = field.getAnnotation(Ref.class);
			
			String transientValue = trans(trans);
			if( trans!=null&&"".equals(transientValue) ) continue;
			
			Col col = column(columnAnnotation);

			if( col==null ){
				col = new Col();
				col.setName( fieldName );
			}
			
			PrimaryKey primaryKey = id(idAnnotation);
			if( primaryKey!=null ){
				primaryKey.setName(col.getName());
				mapping.addPrimaryKey(fieldName,primaryKey);
			}
			
			RefDB refDB = refDB(refDBAnnotation);
			col.setRefDB(refDB);

			col.setTransientValue(transientValue);
			
			mapping.addColumn(fieldName,col);
			
		}
		
		if( !mapping.validate() ){
			throw new Exception("类的annotation错误！");
		}
		
		return mapping;
	}
	
	private static String trans( Transient trans ){
		if(trans==null) return "";
		
		String transientValue = trans.value();
		return transientValue;
	}
	
	private static PrimaryKey id( Id idAnnotation ){
		if( idAnnotation==null ) return null;
		String type = idAnnotation.type();
		Class<?> clazz = idAnnotation.clazz();
		
		PrimaryKey primaryKey = new PrimaryKey();
		primaryKey.setType(type);
		primaryKey.setClazz(clazz);
		return primaryKey;
	}
	
	private static Col column( Column columnAnnotation ){
		if(columnAnnotation==null) return null;
		
		Col col = null;
		
		String name = columnAnnotation.value();
		if( !"".equals(name) ){
			col = new Col();
			col.setName(name);
		}
		
		return col;
	}
	
	private static RefDB refDB( Ref refDBAnnotation ){
		if( refDBAnnotation==null ) return null;
		String refName = refDBAnnotation.refName();
		String[] refKey = refDBAnnotation.refKey();
		String[] targetKey = refDBAnnotation.targetKey();
		String otherConditions = refDBAnnotation.otherConditions();
		
		RefDB refdb = new RefDB();
		refdb.setRefKey(refKey);
		refdb.setTargetKey(targetKey);
		refdb.setRefName(refName);
		refdb.setOtherConditions(otherConditions);
		
		return refdb;
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
