package org.sky.framework.common.utils.logging;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.sky.framework.common.utils.logging.commons.CommonsLoggerFactory;
import org.sky.framework.common.utils.logging.jdk.JDKLoggerFactory;

public abstract class LoggerFactory {
	
	private static final ReadWriteLock lock = new ReentrantReadWriteLock();
	
	private static LoggerFactory loggerFactory = null;
	
	public static Logger getLogger( Class<?> cls ){
		return getFactory().getLoggerImpl(cls);
	}
	
	public static Logger getLogger( String name ){
		return getFactory().getLoggerImpl(name);
	}
	
	protected static LoggerFactory getFactory(){
		lock.readLock().lock();
		try{
			if( loggerFactory!=null ) return loggerFactory;
		}finally{
			lock.readLock().unlock();
		}
		
		lock.writeLock().lock();
		try{
			if( loggerFactory==null ){
				try {
					Class.forName("org.apache.commons.logging.LogFactory");
					loggerFactory = new CommonsLoggerFactory();
				} catch (ClassNotFoundException e) {
					loggerFactory = new JDKLoggerFactory();
				}
			}
		}finally{
			lock.writeLock().unlock();
		}
		
		return loggerFactory;
	}
	
	protected abstract Logger getLoggerImpl( Class<?> cls );
	
	protected abstract Logger getLoggerImpl( String name );
	
	
}
