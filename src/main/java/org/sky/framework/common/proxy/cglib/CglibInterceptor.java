package org.sky.framework.common.proxy.cglib;

import java.lang.reflect.Method;
import java.util.Set;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.sky.framework.aop.AOPExecuator;
import org.sky.framework.common.config.Aop;
import org.sky.framework.common.enumeration.ConfigEnum;

public class CglibInterceptor implements MethodInterceptor{
	
	private Set<Aop> aops;
	
	public CglibInterceptor( Set<Aop> aops ){
		this.aops = aops;
	}

	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		
		AOPExecuator.execute(method.getDeclaringClass(), aops,ConfigEnum.aopPointcutReqBefore.getValue());
		Object result=proxy.invokeSuper(obj, args);
		AOPExecuator.execute(method.getDeclaringClass(), aops,ConfigEnum.aopPointcutReqAfter.getValue());
		
		return result;
	}

}
