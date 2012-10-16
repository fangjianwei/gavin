package org.sky.framework.common.proxy.cglib;

import java.lang.reflect.Method;
import java.util.Set;

import org.sky.framework.common.config.Aop;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibInterceptor implements MethodInterceptor{
	
	private Set<Aop> aops;
	
	public CglibInterceptor( Set<Aop> aops ){
		this.aops = aops;
	}

	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		
		//TODO aop
		
		System.out.println("====aop===="+method.getName());
		Object result=proxy.invokeSuper(obj, args);
		return result;
	}

}
