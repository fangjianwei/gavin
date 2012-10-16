package org.sky.framework.ioc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import org.sky.framework.common.config.Bean;
import org.sky.framework.common.config.BeanProperty;
import org.sky.framework.common.enumeration.InjectEnum;
import org.sky.framework.common.proxy.BeanFactory;

public class BeanInitWithIoc {
	
	public static Object injectBean( Object target,Bean bean ){	
		try {
			Set<BeanProperty> beanPropertys = bean.getPropertys();
			if(beanPropertys==null) return target;
			
			for( BeanProperty beanProperty:beanPropertys ){
				String name = beanProperty.getName();
				String value = beanProperty.getValue();
				String type = beanProperty.getType();
				Object property = null;
				if( InjectEnum.byId.getValue().equals(type) ){
					property = BeanFactory.getBean(value);
				}else{
					//TODO 根据类型实例话对象
					//property = 
				}
				
				name = "set" + name.substring(0,1).toUpperCase()+name.substring(1);
				
				Method injectMethod = null;
				Method[] methods = target.getClass().getDeclaredMethods();
				if( methods==null||methods.length==0) continue;
				for( Method m:methods ){
					if( m.getName().equals(name)&&m.getParameterTypes().length==1){
						injectMethod = m;
						break;
					}
				}
				
				//Class<?> paramClass = null;
				//if(property instanceof Proxy){
				//if( Proxy.isProxyClass(property.getClass()) ){
				//	paramClass = property.getClass().getInterfaces()[0];
				//	System.out.println("==="+property.getClass().getInterfaces()[0]);
				//}else{
				//	paramClass = property.getClass().getSuperclass();
				//}
				//injectMethod = target.getClass().getMethod(name, paramClass);
				if( !injectMethod.isAccessible() ){
					injectMethod.setAccessible(true);
				}
				injectMethod.invoke(target, property);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		//} catch (NoSuchMethodException e) {
		//	e.printStackTrace();
		} catch ( InvocationTargetException e ){
			e.printStackTrace();
		} catch (IllegalAccessException e ){
			e.printStackTrace();
		}
		
		return target;
	}
	
}
