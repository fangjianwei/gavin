package org.sky.framework.common.proxy;

import java.util.HashMap;
import java.util.Map;

import org.sky.framework.common.config.Bean;
import org.sky.framework.common.config.ConfigUtil;
import org.sky.framework.common.config.Configuration;
import org.sky.framework.common.enumeration.ScopeEnum;
import org.sky.framework.common.utils.logging.Logger;
import org.sky.framework.common.utils.logging.LoggerFactory;
import org.sky.framework.ioc.BeanInitWithIoc;

public class BeanFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(BeanFactory.class);
	
	private static final Map<String,Object> beanContainer = new HashMap<String, Object>();
	private static final Map<String,Object> controlledContainer = new HashMap<String, Object>();
	
	private static String configFileName;
	
	public static void configFileName( String name ){
		configFileName = name;
	}
	
	public static Object createControlledBean( String id ){
		Configuration configuration = ConfigUtil.getInstance().getConfiguration(configFileName);

		if( configuration==null ){
			return null;
		}
		
		Bean bean = configuration.getAction(id);
		if( bean==null ) return null;
		
		return createObject(id,bean);
	}
	
	public static Object createBean( String id ){
		Configuration configuration = ConfigUtil.getInstance().getConfiguration(configFileName);

		if( configuration==null ){
			return null;
		}
		
		Bean bean = configuration.getBean(id);
		if( bean==null ) return null;
		
		return createObject(id,bean);
	}
	
	private static Object createObject( String id,Bean bean ){
		Object instance = null;
		String className = bean.getClazz();
		if(className==null||"".equals(className)) return null;
		
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);

			Configuration configuration = ConfigUtil.getInstance().getConfiguration(configFileName);
			if( configuration.getAops()!=null ){
				GavinProxy gavinProxy = GavinProxyFactory.getProxy(clazz);
				instance = gavinProxy.newProxyInstance(clazz, configuration.getAops());
			}else{
				instance = clazz.newInstance();
			}
			instance = BeanInitWithIoc.injectBean(instance, bean);

		} catch (ClassNotFoundException e) {
			if( logger.isDebugEnabled() ) logger.debug("",e);
			return null;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	
		

		
		if( instance!=null&&ScopeEnum.singleton.getValue().equals(bean.getScope()) ){
			beanContainer.put(id, instance);
		}
		
		logger.debug("bean created,id="+bean.getId()+",clazz="+bean.getClazz()+",scope="+bean.getScope());
		
		return instance;
	}

	public static Object getBean(String id){
		Object bean = null;
		if(beanContainer.containsKey(id)){
			bean = beanContainer.get(id);
		}else{
			bean = createBean(id);
		}
		return bean;
	}
	
	public static Object getControlledBean(String id){
		Object bean = null;
		if(controlledContainer.containsKey(id)){
			bean = controlledContainer.get(id);
		}else{
			bean = createControlledBean(id);
		}
		return bean;
	}
	
}
