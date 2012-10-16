package org.sky.framework.common.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Set;

import org.sky.framework.common.config.Aop;

public class RequiredInvocationHandler implements InvocationHandler{
	
	private Object target;
	
	private Set<Aop> aops;
	
	public RequiredInvocationHandler( Object target,Set<Aop> aops ){
		this.target = target;
		this.aops = aops;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		//TODO aop
		
		method.invoke(target, args);
		
		
		return null;
	}

}
