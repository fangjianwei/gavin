package org.sky.framework.common.utils.logging.jdk;

import java.util.logging.Level;

import org.sky.framework.common.utils.logging.Logger;

public class JDKLogger implements Logger{
	
	private java.util.logging.Logger log;
	
	public JDKLogger( java.util.logging.Logger log ){
		this.log = log;
	}
	
	public void error(String msg) {
		log.log(Level.SEVERE,msg);
	}

	public void error(String msg, Throwable ex) {
		log.log(Level.SEVERE,msg,ex);
	}
	
	public boolean isErrorEnabled() {
		return log.isLoggable(Level.SEVERE);
	}

	public void fatal(String msg) {
		log.log(Level.SEVERE, msg);
		
	}

	public void fatal(String msg, Throwable ex) {
		log.log(Level.SEVERE, msg,ex);
	}

	public boolean isFatalEnabled() {
		return log.isLoggable(Level.SEVERE);
	}	

	public void info(String msg) {
		log.log(Level.INFO, msg);
		
	}

	public void info(String msg, Throwable ex) {
		log.log(Level.INFO, msg, ex);
		
	}
	
	public boolean isInfoEnabled() {
		return log.isLoggable(Level.INFO);
	}

	public void warn(String msg) {
		log.log(Level.WARNING, msg);
		
	}

	public void warn(String msg, Throwable ex) {
		log.log(Level.WARNING, msg,ex);
	}

	public boolean isWarnEnabled() {
		return log.isLoggable(Level.WARNING);
	}
	
	public void debug(String msg) {
		log.log(Level.FINE, msg);
		
	}

	public void debug(String msg, Throwable ex) {
		log.log(Level.FINE, msg, ex);
		
	}
	
	public boolean isDebugEnabled() {
		return log.isLoggable(Level.FINE);
	}

	public void trace(String msg) {
		log.log(Level.FINEST, msg);
		
	}

	public void trace(String msg, Throwable ex) {
		log.log(Level.FINEST, msg,ex);
	}

	public boolean isTraceEnabled() {
		return log.isLoggable(Level.FINEST);
	}
	


}
