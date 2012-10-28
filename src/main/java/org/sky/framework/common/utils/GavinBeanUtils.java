package org.sky.framework.common.utils;

import java.math.BigDecimal;
import java.util.Date;

public class GavinBeanUtils {

	public static boolean isPrimitive(Class<?> clazz) {
		String className = clazz.getName();
		if ( "int".equals(className) || "java.lang.Integer".equals(className) ) {
			return true;
		}
		else if ( "java.lang.String".equals(className) ) {
			return true;
		}
		else if ( "long".equals(className) || "java.lang.Long".equals(className) ) {
			return true;
		}
		else if ( "float".equals(className) || "java.lang.Float".equals(className) ) {
			return true;
		}
		else if ( Date.class.isAssignableFrom(clazz) ) {
			return true;
		}
		else if ( "byte".equals(className) || "java.lang.Byte".equals(className) ) {
			return true;
		}
		else if ( "short".equals(className) || "java.lang.Short".equals(className) ) {
			return true;
		}
		else if ( "double".equals(className) || "java.lang.Double".equals(className) ) {
			return true;
		}
		else if ( "char".equals(className) || "java.lang.Character".equals(className) ) {
			return true;
		}
		else if ( "boolean".equals(className) || "java.lang.Boolean".equals(className) ) {
			return true;
		}
		else if ( "[B".equals(className) ) {
			return true;
		}
		else if ( BigDecimal.class.isAssignableFrom(clazz) ) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isList(Class<?> clazz) {
		if ( "java.util.List".equals(clazz.getName()) ) {
			return true;
		}
		
		Class<?>[] interfaces = clazz.getInterfaces();
		if ( interfaces==null || interfaces.length==0 ) return false;
		
		for ( Class<?> c : interfaces ) {
			if ( "java.util.List".equals(c.getName()) ) {
				return true;
			}
		}
		
		return false;
	}

	public static boolean isMap(Class<?> clazz) {
		if ( "java.util.Map".equals(clazz.getName()) ) {
			return true;
		}

		Class<?>[] interfaces = clazz.getInterfaces();
		if ( interfaces==null || interfaces.length==0 ) return false;
		
		for ( Class<?> c : interfaces ) {
			if ( "java.util.Map".equals(c.getName()) ) {
				return true;
			}
		}
		
		return false;
	}

	public static boolean isArray(Class<?> clazz) {
		String className = clazz.getName();
		if ( className.startsWith("[") ) return true;
		else return false;
	}

}
