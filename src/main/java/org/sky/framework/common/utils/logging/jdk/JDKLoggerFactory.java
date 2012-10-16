package org.sky.framework.common.utils.logging.jdk;

import org.sky.framework.common.utils.logging.Logger;
import org.sky.framework.common.utils.logging.LoggerFactory;

public class JDKLoggerFactory extends LoggerFactory{

	@Override
	protected Logger getLoggerImpl(Class<?> cls) {
		return new JDKLogger(java.util.logging.Logger.getLogger(cls.getName()));
	}

	@Override
	protected Logger getLoggerImpl(String name) {
		return new JDKLogger(java.util.logging.Logger.getLogger(name));
	}

}
