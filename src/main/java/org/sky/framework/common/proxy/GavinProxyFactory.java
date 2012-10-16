package org.sky.framework.common.proxy;

import org.sky.framework.common.proxy.cglib.CglibProxy;
import org.sky.framework.common.proxy.jdk.JDKProxy;

public class GavinProxyFactory {
	
	public static GavinProxy getProxy( Class<?> target ){
		GavinProxy proxy = null;
		if( hasImplInterface(target) ){
			proxy = new JDKProxy();
		}else{
			proxy = new CglibProxy();
		}
		return proxy;
	}
	
	private static boolean hasImplInterface( Class<?> clazz ){
		return clazz.getInterfaces().length>0;
	}
}
