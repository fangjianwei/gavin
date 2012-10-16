package org.sky.framework.web.helper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sky.framework.common.enumeration.ClassType;
import org.sky.framework.common.utils.logging.Logger;
import org.sky.framework.common.utils.logging.LoggerFactory;


public class ParamHelper {
	
	private static final Logger log = LoggerFactory.getLogger(ParamHelper.class);
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> convert2List( String paramName, ServletTranslator translator,String parameterizedType ){
		Object[] objs = convert2ObjectArray(paramName, translator, parameterizedType);
		List list = Arrays.asList(objs);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Set<T> convert2Set( String paramName, ServletTranslator translator,String parameterizedType ){
		Object[] objs = convert2ObjectArray(paramName, translator, parameterizedType);
		Set set = new HashSet(objs.length);
		for( Object obj:objs ){
			set.add(obj);
		}
		return set;
	}
	
	public static Object[] convert2ObjectArray( String paramName, ServletTranslator translator,String parameterizedType ){
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return null;
		
		if( parameterizedType==null ){
			parameterizedType = ClassType.String.value;

		}
		
		Object[] returnObj = null;
		if(ClassType.Integer.value.equals(parameterizedType)){
			returnObj = string2IntegerArray( values );
		}else if( ClassType.String.value.equals(parameterizedType) ){
			returnObj = values;
		}else if( ClassType.Float.value.equals(parameterizedType) ){
			returnObj = string2FloatArray( values );
		}else if( ClassType.Double.value.equals(parameterizedType) ){
			returnObj = string2DoubleArray(values);
		}else if( ClassType.Short.value.equals(parameterizedType) ){
			returnObj = string2ShortArray(values);
		}else{
			//set = null;
			log.error("参数'paramName':只支持Short、Integer、String、Folat、Double类型");
			//throw new RuntimeException("参数'paramName':只支持Integer、String、Folat、Double类型");
		}
		
		return returnObj;
		
	}

	private static Short[] string2ShortArray( String[] values ){
		int valueLength = values.length;
		Short[] returnObj = new Short[valueLength];
		for( int i=0;i<valueLength;i++ ){
			String s = values[i];
			returnObj[i] = Short.parseShort(s);
		}
		return returnObj;
	}
	
	private static Integer[] string2IntegerArray( String[] values ){
		int valueLength = values.length;
		Integer[] returnObj = new Integer[valueLength];
		for( int i=0;i<valueLength;i++ ){
			String s = values[i];
			returnObj[i] = Integer.parseInt(s);
		}
		return returnObj;
	}
	
	private static Double[] string2DoubleArray( String[] values ){
		int valueLength = values.length;
		Double[] returnObj = new Double[valueLength];
		for( int i=0;i<valueLength;i++ ){
			String s = values[i];
			returnObj[i] = Double.parseDouble(s);
		}
		return returnObj;
	}
	
	private static Float[] string2FloatArray( String[] values ){
		int valueLength = values.length;
		Float[] returnObj = new Float[valueLength];
		for( int i=0;i<valueLength;i++ ){
			String s = values[i];
			returnObj[i] = Float.parseFloat(s);
		}
		return returnObj;
	}
	
	public static String convert2String( String paramName, ServletTranslator translator ){
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return null;
		
		return values[0];
	}
	
	public static String[] convert2StringArray( String paramName, ServletTranslator translator ){
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return null;
		
		return values;
	}
	
	public static int convert2Int( String paramName, ServletTranslator translator ){
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return 0;
		
		return Integer.parseInt(values[0]);
	}
	
	public static int[] convert2IntArray( String paramName, ServletTranslator translator ){
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return null;
		
		int[] returnInt = new int[values.length];
		
		for(int i=0;i<values.length;i++){
			String value = values[i];
			returnInt[i] = Integer.parseInt(value);
		}
		
		return returnInt;
	}
	
	public static Integer convert2Integer( String paramName, ServletTranslator translator ){
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return 0;
		
		return Integer.valueOf(values[0]);
	}
	
	public static Integer[] convert2IntegerArray( String paramName, ServletTranslator translator ){
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return null;
		
		Integer[] returnInteger = new Integer[values.length];
		
		for(int i=0;i<values.length;i++){
			String value = values[i];
			returnInteger[i] = Integer.valueOf(value);
		}
		
		return returnInteger;
	}
}
