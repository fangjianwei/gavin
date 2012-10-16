package org.sky.framework.common.config;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.sky.framework.common.annotation.Controller;
import org.sky.framework.common.annotation.Inject;
import org.sky.framework.common.annotation.Repository;
import org.sky.framework.common.annotation.Scope;
import org.sky.framework.common.annotation.Service;
import org.sky.framework.common.enumeration.AnnotationEnum;
import org.sky.framework.common.enumeration.ConfigEnum;
import org.sky.framework.common.enumeration.InjectEnum;
import org.sky.framework.common.enumeration.ScopeEnum;
import org.sky.framework.common.utils.ClassScanUtils;
import org.sky.framework.common.utils.logging.Logger;
import org.sky.framework.common.utils.logging.LoggerFactory;

public class ConfigUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
	
	private static final String DEFAULT_CONFIG_FILE_NAME = "context.xml";
	
	private static Configuration configuration;
	
	private static ConfigUtil configUtil = null;
	
	public static ConfigUtil getInstance(){
		if( configUtil==null ){
			configUtil = new ConfigUtil();
		}
		return configUtil;
	}
	
	public Configuration getConfiguration(String name){
		if(configuration==null){
			loadConfigurationFromXML(name);
			loadConfigurationByScan();
		}
		return configuration;
	}
	
	public Configuration getConfigurationDefault(){
		if(configuration==null){
			loadConfigurationFromDefaultXML();
			loadConfigurationByScan();
		}
		return configuration;
	}
	
	private void loadConfigurationFromDefaultXML(){
		loadConfigurationFromXML( DEFAULT_CONFIG_FILE_NAME );
	}
	
	@SuppressWarnings("unchecked")
	private void loadConfigurationFromXML(String name){
		if( StringUtils.isBlank(name) ){
			name = DEFAULT_CONFIG_FILE_NAME;
		}
		
		if( logger.isDebugEnabled() ){
			logger.debug("framework load config-file's name is " + name);
		}
		
		configuration = new Configuration();
		
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(name);
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(in);
			Element beens = doc.getRootElement();
			List<Element> elements = beens.elements();
			for( Element ele:elements ){
				if( ConfigEnum.bean.getValue().equalsIgnoreCase(ele.getName()) ){
					beanConvert(ele);
				}else if(  ConfigEnum.aop.getValue().equalsIgnoreCase(ele.getName())  ){
					aopConvert(ele);
				}else if( ConfigEnum.annotation.getValue().equalsIgnoreCase(ele.getName()) ){
					configuration.setAnnotation(true);
				}else if( ConfigEnum.scan.getValue().equalsIgnoreCase(ele.getName())  ){
					scanConvert(ele);
				}else if( ConfigEnum.aop.getValue().equals(ele.getName()) ){
					aopConvert(ele);
				}
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private void beanConvert( Element ele ){
		Bean bean = new Bean();
		bean.setId(ele.attributeValue(ConfigEnum.beanId.getValue()));
		bean.setClazz(ele.attributeValue(ConfigEnum.beanClazz.getValue()));
		bean.setScope(ele.attributeValue(ConfigEnum.beanScope.getValue()));
		
		List<Element> propertyEles = ele.elements();
		if(propertyEles==null) return;
		
		for( Element propertyEle:propertyEles ){
			BeanProperty beanProperty = new BeanProperty();
			beanProperty.setName(propertyEle.attributeValue(ConfigEnum.propertyName.getValue()));
			beanProperty.setType(propertyEle.attributeValue(ConfigEnum.propertyType.getValue()));
			beanProperty.setValue(propertyEle.attributeValue(ConfigEnum.propertyValue.getValue()));
			
			bean.addProperty(beanProperty);
		}
		
		configuration.addBean(bean);
	}
	
	private void aopConvert( Element ele ){
		// TODO aop imple
		Aop aop = new Aop();
		configuration.addAop(aop);
	}
	
	private void scanConvert( Element ele ){
		configuration.addScan(ele.attributeValue(ConfigEnum.scanBasePackage.getValue()));
	}
	
	private void loadConfigurationByScan(){
		//return if no scan 
		if(configuration==null||!configuration.isAnnotation()) return;
		
		Set<String> scans = configuration.getScans();

		if(scans==null){
			logger.warn("annotation scan basepackage config is null");
			return;
		}
		
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		for( String scan:scans ){
			classes.addAll(ClassScanUtils.getClass(scan));
		}
		
		if( classes.isEmpty() ) return;
		
		for( Class<?> clazz:classes ){
			classAnnotationScan(clazz);
		}
	}
	
	private void classAnnotationScan( Class<?> clazz ){
		Annotation[] annotations = clazz.getAnnotations();
		for( Annotation annotation:annotations ){
			String anno = annotation.annotationType().getSimpleName();
			if(AnnotationEnum.Controller.getValue().equals(anno)){
				controllerScan(clazz);
			}else if(AnnotationEnum.Service.getValue().equals(anno)){
				serviceScan(clazz);
			}else if(AnnotationEnum.Repository.getValue().equals(anno)){
				repositoryScan(clazz);
			}
			
		}
	}
	
	private void controllerScan( Class<?> clazz ){
		Bean bean = convertBeanByScan(clazz);
		Controller controller = clazz.getAnnotation(Controller.class);
		if( StringUtils.isBlank(controller.value()) ){
			bean.setId(clazz.getSimpleName());
		}else{
			bean.setId(controller.value());
		}
		configuration.addAction(bean);
	}
	
	private void serviceScan( Class<?> clazz ){
		Bean bean = convertBeanByScan(clazz);
		Service service = clazz.getAnnotation(Service.class);
		if( StringUtils.isBlank(service.value()) ){
			bean.setId(clazz.getSimpleName());
		}else{
			bean.setId(service.value());
		}
		configuration.addBean(bean);
	}
	
	private void repositoryScan( Class<?> clazz ){
		Bean bean = convertBeanByScan(clazz);
		Repository repository = clazz.getAnnotation(Repository.class);
		if( StringUtils.isBlank(repository.value()) ){
			bean.setId(clazz.getSimpleName());
		}else{
			bean.setId(repository.value());
		}
		configuration.addBean(bean);
	}	
	
	private Bean convertBeanByScan( Class<?> clazz ){
		Bean bean = new Bean();
		
		Scope scope = clazz.getAnnotation(Scope.class);
		if( scope==null ){
			bean.setScope(ScopeEnum.singleton.getValue());
		}
		
		bean.setClazz(clazz.getName());
		
		Field[] fields = clazz.getDeclaredFields();
		if( fields!=null){
			for( Field field:fields ){
				Inject inject = field.getAnnotation(Inject.class);
				if( inject==null ) continue;
				
				BeanProperty beanProperty = new BeanProperty();
				
				if(StringUtils.isBlank(inject.id())){
					beanProperty.setType(InjectEnum.byType.getValue());
				}else{
					beanProperty.setType(InjectEnum.byId.getValue());
					beanProperty.setValue(inject.id());
				}
				
				beanProperty.setName(field.getName());
				bean.addProperty(beanProperty);
				
			}
		}
		return bean;
	}
	
}
