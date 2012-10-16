package org.sky.framework.common.utils.logging.commons;

import org.apache.commons.logging.LogFactory;
import org.sky.framework.common.utils.logging.Logger;
import org.sky.framework.common.utils.logging.LoggerFactory;

public class CommonsLoggerFactory extends LoggerFactory{

	@Override
	protected Logger getLoggerImpl(Class<?> cls) {
		return new CommonsLogger(LogFactory.getLog(cls));
	}

	@Override
	protected Logger getLoggerImpl(String name) {
		return new CommonsLogger(LogFactory.getLog(name));
	}

}
