
package com.github.jogjamerapi.log;

public class Logger extends AbstractLogger {

	public Logger(String pName, int pLevel, boolean pAdditive, Appender[] pAppenders) {
		super(pName, pLevel, pAdditive, pAppenders);
	}

	public static Logger getLogger(String name) {
		return LoggerFactory.getLogger(name);
	}

	public static Logger getLogger(Class className) {
		return LoggerFactory.getLogger(className.getName());
	}

	public static Logger getLogger(String name, LoggerFactory factory) {
		return factory.getLogger(name);
	}

	public static Logger getRootLogger() {
		return LoggerFactory.getRootLogger();
	}

	public static Logger[] getAllLogger() {
		return LoggerFactory.getAllLogger();
	}

	// implements abstract methods

	public void debug(String message) {
		if (Level.isGreaterOrEqual(Level.DEBUG, level)) {
			invokeAppenders(Level.DEBUG, message);
		}
		if (additive && (parent != null)) {
			parent.debug(message);
		}
	}

	public void info(String message) {
		if (Level.isGreaterOrEqual(Level.INFO, level)) {
			invokeAppenders(Level.INFO, message);
		}
		if (additive && (parent != null)) {
			parent.info(message);
		}
	}

	public void warn(String message) {
		if (Level.isGreaterOrEqual(Level.WARN, level)) {
			invokeAppenders(Level.WARN, message);
		}
		if (additive && (parent != null)) {
			parent.warn(message);
		}
	}

	public void error(String message) {
		if (Level.isGreaterOrEqual(Level.ERROR, level)) {
			invokeAppenders(Level.ERROR, message);
		}
		if (additive && (parent != null)) {
			parent.error(message);
		}
	}

	public void fatal(String message) {
		if (Level.isGreaterOrEqual(Level.FATAL, level)) {
			invokeAppenders(Level.FATAL, message);
		}
		if (additive && (parent != null)) {
			parent.fatal(message);
		}
	}

	protected void invokeAppenders(int pLevel, String pMessage) {
		Appender[] appenders = getAppenders();

		for (int i = 0; i < appenders.length; i++) {
			if (pLevel == Level.DEBUG) {
				appenders[i].debug(pMessage);
			} else if (pLevel == Level.INFO) {
				appenders[i].info(pMessage);
			} else if (pLevel == Level.WARN) {
				appenders[i].warn(pMessage);
			} else if (pLevel == Level.ERROR) {
				appenders[i].error(pMessage);
			} else if (pLevel == Level.FATAL) {
				appenders[i].fatal(pMessage);
			} else {
				// do nothing
			}
		}
	}

	public void close() {
	}

	public void reset() {
	}

	public void clear() {
		Appender[] appenders = getAppenders();
		for (int i = 0; i < appenders.length; i++) {
			appenders[i].clear();
		}
	}

	public void show() {
		Appender[] appenders = getAppenders();
		for (int i = 0; i < appenders.length; i++) {
			appenders[i].show();
		}
	}

}
