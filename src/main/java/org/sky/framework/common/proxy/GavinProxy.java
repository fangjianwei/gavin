package org.sky.framework.common.proxy;

import java.util.Set;


public abstract class GavinProxy {
	
	public Object newProxyInstance( Class<?> target,Set aops ){
		Object proxy = getProxy(target,aops);
		return proxy;
	}
	
	protected abstract Object getProxy(Class<?> target,Set aops);
}
