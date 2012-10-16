package org.sky.framework.common.proxy;

import java.util.Set;

import org.sky.framework.common.config.Aop;


public abstract class GavinProxy {
	
	public Object newProxyInstance( Class<?> target,Set<Aop> aops ){
		Object proxy = getProxy(target,aops);
		return proxy;
	}
	
	protected abstract Object getProxy(Class<?> target,Set<Aop> aops);
}
