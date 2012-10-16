package org.sky.framework.common.utils.logging.commons;

import org.apache.commons.logging.Log;
import org.sky.framework.common.utils.logging.Logger;

public class CommonsLogger implements Logger{

	private Log log;
	
	public CommonsLogger( Log log ){
		this.log = log;
	}
	
	public void debug(String msg) {
		log.debug(msg);
	}

	public void debug(String msg, Throwable ex) {
		log.debug(msg, ex);
	}

	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}
	
	public void error(String msg) {
		log.error(msg);
		
	}

	public void error(String msg, Throwable ex) {
		log.error(msg, ex);
	}
	
	public boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	public void fatal(String msg) {
		log.fatal(msg);
	}

	public void fatal(String msg, Throwable ex) {
		log.fatal(msg, ex);
	}
	
	public boolean isFatalEnabled() {
		return log.isFatalEnabled();
	}

	public void info(String msg) {
		log.info(msg);
	}

	public void info(String msg, Throwable ex) {
		log.info(msg, ex);
	}

	public boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	public void trace(String msg) {
		log.trace(msg);
	}

	public void trace(String msg, Throwable ex) {
		log.trace(msg, ex);
	}

	public boolean isTraceEnabled() {
		return log.isTraceEnabled();
	}
	
	public void warn(String msg) {
		log.warn(msg);
		
	}

	public void warn(String msg, Throwable ex) {
		log.warn(msg, ex);
	}
	
	public boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}
	
}
