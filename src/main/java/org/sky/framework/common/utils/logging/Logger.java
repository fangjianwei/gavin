package org.sky.framework.common.utils.logging;

public interface Logger {
	void trace(String msg);
	void trace(String msg, Throwable ex);
	boolean isTraceEnabled();

	void debug(String msg);
	void debug(String msg, Throwable ex);
	boolean isDebugEnabled();

	void info(String msg);
	void info(String msg, Throwable ex);
	boolean isInfoEnabled();

	void warn(String msg);
	void warn(String msg, Throwable ex);
	boolean isWarnEnabled();

	void error(String msg);
	void error(String msg, Throwable ex);
	boolean isErrorEnabled();

	void fatal(String msg);
	void fatal(String msg, Throwable ex);
	boolean isFatalEnabled();
}
