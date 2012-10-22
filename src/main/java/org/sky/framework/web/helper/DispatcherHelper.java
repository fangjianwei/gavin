package org.sky.framework.web.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.sky.framework.common.enumeration.ClassType;

public class DispatcherHelper {

	/**
	 * 获取请求方法 <br>
     * 例如：
     * 请求地址为<code>http://localhost:8080/project/login.login.do/fangjw/1234<code> <br>
     * 返回login.login.do
	 * @param request
	 * @return
	 */
    public static String getInvokeMapping( HttpServletRequest request ){
    	String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		
		return getInvokeMapping(contextPath,uri);
    }
    
    /**
     * 获取请求方法 <br>
     * 例如：
     * <code>contextPath="project" uri="/project/login.login.do/fangjw/1234<code> <br>
     * 返回login.login.do
     * @param contextPath
     * @param uri
     * @return
     */
    public static String getInvokeMapping(String contextPath,String uri){
		
		if( !StringUtils.isBlank(contextPath) ){
			uri = uri.substring(contextPath.length()+1);
		}else{
			uri = uri.substring(1);
		}
		
		int pos = uri.indexOf("/");
		if( pos!=-1 ){
			uri = uri.substring(0,pos);
		}

		return uri;
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
    
	public static String[] getRestFullParam( String contextPath,String uri ){
    	if( StringUtils.isNotBlank(contextPath) ){
    		uri = uri.replace(contextPath, "");
    	}
    	
    	uri = uri.substring(1);
    	int splitPos = uri.indexOf("/");
    	if( splitPos!=-1 ){
    		uri = uri.substring(splitPos+1);
    	}else{
    		return null;
    	}
    	
    	String[] objs = uri.split("/");
    	
    	return objs;
    }
	
	public static ServletTranslator combineServletTranslator( HttpServletRequest request,HttpServletResponse response){
		ServletTranslator translator = new ServletTranslator();
		return translator.init(request, response);
	}
	
   public static Object setParamToBean(Class<?> beanClass, ServletTranslator translator) {
		// TODO Auto-generated method stub
	   	Object bean = null;
    	try {
    		String beanPackage = ClassUtils.getPackageName(beanClass);
    		if("java.lang".equals(beanPackage)) return null;
    		
			bean = beanClass.newInstance();
			Field[] fields = beanClass.getDeclaredFields();
			
			if( fields==null||fields.length==0 ) return bean;
			
			Map<String,Object> properties = new HashMap<String, Object>();
			for( Field field:fields ){
				String name = field.getName();
				String typeName = field.getType().getName();
				
				String parameterizedType = null;
				if(field.getGenericType() instanceof ParameterizedType){
					parameterizedType = ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0].toString();
					parameterizedType = parameterizedType.replace("class ", "");
				}
				
				Object value = null;
				if( ClassType.List.value.equals(typeName) ){
					value = ParamHelper.convert2List(name, translator, parameterizedType);
				}
				else if( ClassType.Set.value.equals(typeName) ){	
					value = ParamHelper.convert2Set(name,translator,parameterizedType);
				}
				else if( ClassType.String.value.equals(typeName) ){	
					value = ParamHelper.convert2String(name, translator);
				}
				else if( ClassType.StringArray.value.equals(typeName) ){	
					value = ParamHelper.convert2StringArray(name, translator);
				}
				else if( ClassType.ShortBase.value.equals(typeName) ){	
					value = ParamHelper.convert2ShortBase(name,translator);
				}
				else if( ClassType.ShortBaseArray.value.equals(typeName) ){		
					value = ParamHelper.convert2ShortBaseArray(name,translator);					
				}
				else if( ClassType.Short.value.equals(typeName) ){					
					value = ParamHelper.convert2Short(name,translator);					
				}
				else if( ClassType.ShortArray.value.equals(typeName) ){					
					value = ParamHelper.convert2ShortArray(name,translator);					
				}
				else if( ClassType.IntBase.value.equals(typeName) ){					
					value = ParamHelper.convert2Int(name, translator);					
				}
				else if( ClassType.IntBaseArray.equals(typeName) ){					
					value = ParamHelper.convert2IntArray(name, translator);					
				}
				else if( ClassType.Integer.value.equals(typeName) ){					
					value = ParamHelper.convert2Integer(name, translator);					
				}
				else if( ClassType.IntegerArray.value.equals(typeName) ){					
					value = ParamHelper.convert2IntegerArray(name, translator);					
				}
				else if( ClassType.FloatBase.value.equals(typeName) ){
					value = ParamHelper.convert2FloatBase(name, translator);
				}
				else if( ClassType.FloatBaseArray.equals(typeName) ){
					value = ParamHelper.convert2FloatBaseArray(name, translator);
				}
				else if( ClassType.Float.value.equals(typeName) ){					
					value = ParamHelper.convert2Float(name, translator);
				}
				else if( ClassType.FloatArray.value.equals(typeName) ){
					value = ParamHelper.convert2FloatArray(name, translator);
				}
				else if( ClassType.DoubleBase.value.equals(typeName) ){
					value = ParamHelper.convert2DoubleBase(name, translator);
				}
				else if( ClassType.DoubleBaseArray.value.equals(typeName) ){
					value = ParamHelper.convert2DoubleBaseArray(name, translator);
				}
				else if( ClassType.Double.value.equals(typeName) ){
					value = ParamHelper.convert2Double(name, translator);
				}
				else if( ClassType.DoubleArray.value.equals(typeName) ){
					value = ParamHelper.convert2DoubleArray(name, translator);
				}	
				
				properties.put(name, value);
			}
			
			BeanUtils.populate(bean, properties);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}
   
   public static Object[] convertMethodParam( Class<?>[] paramClasses,String uri,String contextPath ){
   		Object[] methodParam = new Object[paramClasses.length];
   	
   		String[] restFullParams = DispatcherHelper.getRestFullParam(contextPath,uri);
   	    	
   		if( restFullParams!=null&&restFullParams.length!=0 ){
	   		int restFullParamsLength = restFullParams.length;
	   		int classLength = paramClasses.length;
	   		for( int i=0;i<restFullParamsLength;i++){
	   			if(classLength<i){
	   				break;
	   			}
	   			
	   			String value = restFullParams[i];
	   			Class<?> clazz = paramClasses[i];
	   			String name = clazz.getName();
	   	
	   			if( "int".equals(name)||"java.lang.Integer".equals(name) ){
	   				methodParam[i] = Integer.parseInt(value);
	   			}else if("float".equals(name)||"java.lang.Float".equals(name)){
	   				methodParam[i] = Float.parseFloat(value);
	   			}else if("double".equals(name)||"java.lang.Double".equals(name)){
	   				methodParam[i] = Double.parseDouble(value);
	   			}else if( "java.lang.String".equals(name) ){
	   				methodParam[i] = value;
	   			}
	   		}
	   		
	   		if( restFullParamsLength<classLength ){
	   			Class<?> beanClass = paramClasses[restFullParamsLength];
	   			ServletTranslator translator = ServletTranslator.getTranslator();
	   			methodParam[restFullParamsLength] = DispatcherHelper.setParamToBean(beanClass,translator);
	   		}
   		}
   		return methodParam;
   }
   
}
