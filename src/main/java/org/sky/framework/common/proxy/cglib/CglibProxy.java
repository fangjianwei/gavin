package org.sky.framework.common.proxy.cglib;

import java.util.Set;

import net.sf.cglib.proxy.Enhancer;

import org.sky.framework.common.config.Aop;
import org.sky.framework.common.proxy.GavinProxy;

public class CglibProxy extends GavinProxy{

	private Enhancer enhancer = new Enhancer(); 
	
	@Override
	protected Object getProxy(Class<?> target,Set<Aop> aops) {
		enhancer.setSuperclass(target);
		enhancer.setCallback(new CglibInterceptor(aops));
		return enhancer.create();
	}



}
