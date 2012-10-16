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
	
	public static short convert2ShortBase( String paramName, ServletTranslator translator ){
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return 0;
		
		return Short.parseShort(values[0]);
	}
	
	public static short[] convert2ShortBaseArray( String paramName, ServletTranslator translator ){
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return null;
		
		short[] returnShort = new short[values.length];
		
		for(int i=0;i<values.length;i++){
			String value = values[i];
			returnShort[i] = Short.parseShort(value);
		}
		
		return returnShort;
	}
	
	public static Short convert2Short( String paramName, ServletTranslator translator ){
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return 0;
		
		return Short.parseShort(values[0]);
	}
	
	public static Short[] convert2ShortArray( String paramName, ServletTranslator translator ){
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return null;
		
		Short[] returnShort = string2ShortArray(values);
		
		return returnShort;
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
		
		if( values==null||values.length==0 ) return new Integer(0);
		
		return Integer.valueOf(values[0]);
	}
	
	public static Integer[] convert2IntegerArray( String paramName, ServletTranslator translator ){
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return null;
		
		Integer[] returnInteger = string2IntegerArray(values);
		
		return returnInteger;
	}

	public static float convert2FloatBase(String paramName,ServletTranslator translator) {
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return 0;
		
		return Float.valueOf(values[0]);
	}

	public static float[] convert2FloatBaseArray(String paramName,ServletTranslator translator) {
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return null;
		
		float[] returnFloat = new float[values.length];
		
		for(int i=0;i<values.length;i++){
			String value = values[i];
			returnFloat[i] = Float.valueOf(value);
		}
		
		return returnFloat;
	}
	
	public static Float convert2Float(String paramName,ServletTranslator translator) {
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return new Float(0);
		
		return Float.valueOf(values[0]);
	}

	public static Float[] convert2FloatArray(String paramName,ServletTranslator translator) {
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return null;
		
		Float[] returnFloat = string2FloatArray(values);
		
		return returnFloat;
	}	
	
	public static double convert2DoubleBase(String paramName,ServletTranslator translator) {
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return new Double(0);
		
		return Double.valueOf(values[0]);
	}

	public static double[] convert2DoubleBaseArray(String paramName,ServletTranslator translator) {
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return null;
		
		double[] returnFloat = new double[values.length];
		
		for(int i=0;i<values.length;i++){
			String value = values[i];
			returnFloat[i] = Double.valueOf(value);
		}
		
		return returnFloat;
	}	
	
	public static Double convert2Double(String paramName,ServletTranslator translator) {
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return new Double(0);
		
		return Double.valueOf(values[0]);
	}

	public static Double[] convert2DoubleArray(String paramName,ServletTranslator translator) {
		String[] values = (String[]) translator.getParameters().get(paramName);
		
		if( values==null||values.length==0 ) return null;
		
		Double[] returnFloat = string2DoubleArray(values);
		
		return returnFloat;
	}
	
}
