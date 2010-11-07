
package com.github.jogjamerapi.log;

import java.util.Date;

import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.ui.Color;

public abstract class AbstractAppender implements Appender {

	protected static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	protected String name;
	protected String type;
	protected int threshold;
	protected String destination;

	public abstract void close();

	public abstract void reset();

	public abstract void clear();

	public abstract void show();

	public abstract void writeLine(int level, String message, final int fg, final int bg, final boolean bold);

	public AbstractAppender(String pName, String pType, int pThreshold, String pDestination) {
		name = pName;
		type = pType;
		threshold = pThreshold;
		destination = pDestination;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getThreshold() {
		return threshold;
	}

	public String getDestination() {
		return destination;
	}

	public void debug(String message) {
		if (Level.isGreaterOrEqual(Level.DEBUG, threshold)) {
			writeLine(Level.DEBUG, formatDebug(message), Color.BLACK, Color.WHITE, false);
		}
	}

	public void info(String message) {
		if (Level.isGreaterOrEqual(Level.INFO, threshold)) {
			writeLine(Level.INFO, formatInfo(message), Color.GREEN, Color.WHITE, true);
		}
	}

	public void warn(String message) {
		if (Level.isGreaterOrEqual(Level.WARN, threshold)) {
			writeLine(Level.WARN, formatWarn(message), Color.ORANGE, Color.WHITE, true);
		}
	}

	public void error(String message) {
		if (Level.isGreaterOrEqual(Level.ERROR, threshold)) {
			writeLine(Level.ERROR, formatError(message), Color.RED, Color.WHITE, true);
		}
	}

	public void fatal(String message) {
		if (Level.isGreaterOrEqual(Level.FATAL, threshold)) {
			writeLine(Level.FATAL, formatFatal(message), Color.RED, Color.BLACK, true);
		}
	}

	protected String formatDebug(String message) {
		return simpleDateFormat.format(new Date()) + " [DEBUG] " + message;
	}

	protected String formatInfo(String message) {
		return simpleDateFormat.format(new Date()) + " [INFO] " + message;
	}

	protected String formatWarn(String message) {
		return simpleDateFormat.format(new Date()) + " [WARN] " + message;
	}

	protected String formatError(String message) {
		return simpleDateFormat.format(new Date()) + " [ERROR] " + message;
	}

	protected String formatFatal(String message) {
		return simpleDateFormat.format(new Date()) + " [FATAL] " + message;
	}
}
