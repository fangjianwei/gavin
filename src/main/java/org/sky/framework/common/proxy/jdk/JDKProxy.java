package org.sky.framework.common.proxy.jdk;

import java.lang.reflect.Proxy;
import java.util.Set;

import org.sky.framework.common.config.Aop;
import org.sky.framework.common.proxy.GavinProxy;
import org.sky.framework.common.utils.logging.Logger;
import org.sky.framework.common.utils.logging.LoggerFactory;

public class JDKProxy extends GavinProxy{
	
	private static final Logger logger = LoggerFactory.getLogger(JDKProxy.class);

	@Override
	protected Object getProxy( Class target,Set aops ) {
		logger.debug("use jdk proxy!");
		Object proxy = null;
		try {
			proxy = Proxy.newProxyInstance(target.getClassLoader(), target.getInterfaces(), new RequiredInvocationHandler(target.newInstance(),aops));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return proxy;
	}

}
