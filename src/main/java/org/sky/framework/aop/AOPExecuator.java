package org.sky.framework.aop;

import java.lang.reflect.Method;
import java.util.Set;

import org.sky.framework.common.config.Aop;
import org.sky.framework.common.config.Aop.AOPPointCut;
import org.sky.framework.common.config.Aop.AOPPointCutRequest;
import org.sky.framework.common.config.ConfigUtil;
import org.sky.framework.common.enumeration.ConfigEnum;
import org.sky.framework.common.proxy.BeanFactory;
import org.sky.framework.common.utils.GavinBeanUtils;
import org.sky.framework.common.utils.logging.Logger;
import org.sky.framework.common.utils.logging.LoggerFactory;

public class AOPExecuator {
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
	
	public static void execute( Class<?> clazz,Set<Aop> aops,String request ){
		
		if( aops==null||aops.isEmpty() ) return;
		if( !request.equals(ConfigEnum.aopPointcutReqBefore.getValue())||!request.equals(ConfigEnum.aopPointcutReqAfter.getValue()) ){
			return;
		}
		
		for( Aop aop:aops ){
			AOPPointCutRequest pointcutRequest = aop.getPointcutRequest(request);
			if( pointcutRequest==null ) break;
			
			AOPPointCut pointcut = aop.getPointcut(pointcutRequest.getPointcutref());
			if( pointcut==null ) break;
	
			String expression = pointcut.getExpression();
			if( expression==null||"".equals(expression) ) break;
			
			if( match(expression) ){
				Object obj = BeanFactory.getControlledBean(aop.getRef());
				Method method = GavinBeanUtils.getMethodByName(pointcutRequest.getMethod(), obj.getClass());
				try {
					method.invoke(obj, new Object[]{});
					logger.info("aop is executed,"+request+","+pointcutRequest.getMethod());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
				
	}
	
	public static boolean match( String expression ){
		//TODO 实现aop拦截匹配
		return false;
	}
}
