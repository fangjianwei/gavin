package org.sky.framework.common.utils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

import org.sky.framework.common.enumeration.ClassType;

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
	
	public static boolean isString( String className ){
		if( ClassType.String.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isInt( String className ){
		if( ClassType.IntBase.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isInteger( String className ){
		if( ClassType.Integer.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isLongBase( String className ){
		if( ClassType.LongBase.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isLong( String className ){
		if( ClassType.Long.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isFloatBase( String className ){
		if( ClassType.LongBase.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isFloat( String className ){
		if( ClassType.Long.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}	
	
	public static boolean isDoubleBase(String className) {
		if( ClassType.DoubleBase.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isDouble(String className) {
		if( ClassType.Double.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isByteBase(String className) {
		if( ClassType.ByteBase.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isByte(String className) {
		if( ClassType.Byte.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isShortBase(String className) {
		if( ClassType.ShortBase.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isShort(String className) {
		if( ClassType.Short.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}	
	
	public static boolean isBooleanBase(String className) {
		if( ClassType.BooleanBase.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isBoolean(String className) {
		if( ClassType.Boolean.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}	
	
	public static boolean isBigDecimal( String className ){
		if( "java.math.BigDecimal".equals(className) ){
			return true;
		}else{
			return false;
		}	
	}

	public static boolean isByteBaseArray(String className) {
		if( ClassType.ByteBaseArray.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}

	public static boolean isByteArray(String className) {
		if( ClassType.ByteArray.value.equals(className) ){
			return true;
		}else{
			return false;
		}
	}	
	
	public static boolean isList(Class<?> clazz) {
		if ( ClassType.List.value.equals(clazz.getName()) ) {
			return true;
		}
		
		Class<?>[] interfaces = clazz.getInterfaces();
		if ( interfaces==null || interfaces.length==0 ) return false;
		
		for ( Class<?> c : interfaces ) {
			if ( ClassType.List.value.equals(c.getName()) ) {
				return true;
			}
		}
		
		return false;
	}

	public static boolean isMap(Class<?> clazz) {
		if ( ClassType.Map.value.equals(clazz.getName()) ) {
			return true;
		}

		Class<?>[] interfaces = clazz.getInterfaces();
		if ( interfaces==null || interfaces.length==0 ) return false;
		
		for ( Class<?> c : interfaces ) {
			if ( ClassType.Map.value.equals(c.getName()) ) {
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
	
    public static Method getMethodByName( String methodName,Class<?> targetClass ){
		Method[] methods = targetClass.getDeclaredMethods();
		if( methods==null||methods.length==0){
			return null;
		}
		
		Method method = null;
		for( Method m:methods ){
			if( m.getName().equals(methodName)){
				method = m;
				break;
			}
		}
		return method;
    }

}
