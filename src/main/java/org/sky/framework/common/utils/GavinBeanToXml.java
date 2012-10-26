package org.sky.framework.common.utils;

import java.beans.PropertyDescriptor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.iterators.ArrayIterator;
import org.apache.commons.lang.StringUtils;


/**
 * ClassName：Class BeanToXml<br>

 * @since JDK1.5 or higher
 */
public class GavinBeanToXml {
	
	public static String toXml( Object bean ){
		return toXml( bean, null );
	}
	
	
	public static String toXml( Object bean, String encoding ){
		if ( StringUtils.isBlank(encoding) ) encoding = "UTF-8";
		if ( bean==null ) return null;
		
		String xmlPart = parseObject( null, bean );
		
		String xml = "<?xml version='1.0' encoding='" + encoding + "'?>";
		
		if ( !StringUtils.isBlank(xmlPart) ) {
			xml += xmlPart;
		}
		
		return xml;
	}
	
	private static String parseObject( String name, Object obj ){
		if ( obj==null ) return null;
		
		Class<?> clazz = obj.getClass();
		String xmlPart = null;
		
		if ( StringUtils.isBlank(name) ) {
			if ( clazz.isArray() ) {
				name = "array";
			}
			else {
				name = getSimpleName( clazz );
			}
		}
		
		if ( GavinBeanUtils.isPrimitive(clazz) ) {
			xmlPart = parsePrimitive( name, obj );
		}
		else if ( GavinBeanUtils.isList(clazz) ) {
			xmlPart = parseList( name, (List<?>) obj );
		}
		else if ( GavinBeanUtils.isMap(clazz) ) {
			xmlPart = parseMap( name, (Map<?,?>) obj );
		}
		else if ( GavinBeanUtils.isArray(clazz) ) {
			xmlPart = parseArray( name, obj );
		}
		else {
			xmlPart = parseBean( name, obj );
		}
		
		return xmlPart;
	}
	
	
	/**
	 * obtain SimpleName of the class.
	 *
	 * @param clazz the clazz
	 * @return  SimpleName
	 */
	private static String getSimpleName( Class<?> clazz ) {
		String name = clazz.getSimpleName();
		if ( name==null ) {
			name = clazz.getName();
			int pos = name.lastIndexOf(".");
			if ( pos>=0 ) {
				name = name.substring(pos+1);
			}
		}
		
		name = name.replace("$", "_");

		if ( name==null || name.length()==0 ) {
			name = "objectBean";
		}
		
		return name;
	}
	
	
	/**
	 *
	 * @param name the name
	 * @param bean the bean
	 * @return the String
	 */
	private static String parseBean( String name, Object bean ){
		StringBuffer sb = new StringBuffer();
		
		Class<?> clazz = bean.getClass();
		if ( StringUtils.isBlank(name) ) name = getSimpleName(clazz);
		
		Object propertyValue = null;
		String propertyName = null;
		String xmlPart = null;
		
		sb.append( "<" + name + ">" );
		
		PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(bean);
		Class<?> propertyClass = null;
		//String propertyClassName = null;
		
		for ( PropertyDescriptor p : properties ) {
			try {
				propertyName = p.getName();
				propertyClass = p.getPropertyType();
				if ( propertyClass == null ) continue;
				
				//propertyClassName = propertyClass.getName();
				if ( !GavinBeanUtils.isPrimitive(propertyClass) && !GavinBeanUtils.isList(propertyClass) && !GavinBeanUtils.isArray(propertyClass) && !GavinBeanUtils.isMap(propertyClass) ) {
					continue;
				}
				
				if ( "class".equals(propertyName) ) continue;
				propertyValue = PropertyUtils.getProperty( bean, propertyName );
				xmlPart = parseObject( propertyName, propertyValue );
				if ( !StringUtils.isBlank(xmlPart) ) sb.append( xmlPart );
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		sb.append( "</" + name + ">" );
		
		return sb.toString();
	}
	
	
	/**
	 *
	 * @param name the name
	 * @param list the list
	 * @return the String
	 */
	private static String parseList( String name, List<?> list ){
		StringBuffer sb = new StringBuffer();
		
		sb.append( "<" ).append( name ).append( ">" );
		
		if ( list!=null && !list.isEmpty() ) {
			String xmlPart = null;
			for ( Object o : list ) {
				xmlPart = parseObject( "row", o );
				if ( !StringUtils.isBlank(xmlPart) ) {
					sb.append( xmlPart );
				}
			}
		}
		
		sb.append( "</" ).append( name ).append( ">" );
		return sb.toString();
	}
	
	
	/**
	 *
	 * @param name the name
	 * @param map the map
	 * @return the String
	 */
	private static String parseMap( String name, Map map ){
		StringBuffer sb = new StringBuffer();
		
		sb.append( "<" ).append( name ).append( ">" );
		
		if ( map!=null && !map.isEmpty() ) {
			String xmlPart = null;
			Entry entry = null;
			Object key = null;
			Object value = null;
			for ( Object o : map.entrySet() ) {
				entry = (Entry) o;
				key = entry.getKey();
				value = entry.getValue();
				xmlPart = "<entry>";
				xmlPart += parseObject( "key", key );
				xmlPart += parseObject( "value", value );
				xmlPart += "</entry>";
				if ( !StringUtils.isBlank(xmlPart) ) {
					sb.append( xmlPart );
				}
			}
		}
		
		sb.append( "</" ).append( name ).append( ">" );
		return sb.toString();
	}
	
	private static String parseArray( String name, Object array ){
		StringBuffer sb = new StringBuffer();
		
		sb.append( "<" ).append( name ).append( ">" );
		
		if ( array != null ) {
			ArrayIterator iter = new ArrayIterator( array );
			Object o = null;
			String xmlPart = null;
			while ( iter.hasNext() ) {
				o = iter.next();
				xmlPart = parseObject( "element", o );
				if ( !StringUtils.isBlank(xmlPart) ) {
					sb.append( xmlPart );
				}
			}
		}
		
		sb.append( "</" ).append( name ).append( ">" );
		return sb.toString();
	}
	

	private static String parsePrimitive( String name, Object bean ){
		String value = "";
		Class<?> clazz = bean.getClass();
		String className = clazz.getName();
		
		if ( Date.class.isAssignableFrom(clazz) ) {
			value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( (Date) bean );
		}
		else {
			if ( "java.lang.String".equals(className) ) {
				value = "<![CDATA[" + (String) bean + "]]>";
			}
			else {
				value = bean.toString();
			}
		}
		
		StringBuffer sb = new StringBuffer();
		if ( StringUtils.isBlank(value) ) {
			sb.append( "<").append(name).append("/>");
		}
		else {
			sb.append( "<").append(name).append(">").append(value).append("</").append(name).append(">" );
		}
		return sb.toString();
	}
}
