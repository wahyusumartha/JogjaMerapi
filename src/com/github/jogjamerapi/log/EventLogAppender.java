
package com.github.jogjamerapi.log;

import net.rim.device.api.system.EventLogger;
import net.rim.device.api.util.StringUtilities;

public class EventLogAppender extends AbstractAppender {

	protected long guid;

	public EventLogAppender(String pName, String pType, int pThreshold, String pDestination) {
		super(pName, pType, pThreshold, pDestination);
		guid = StringUtilities.stringHashToLong(pDestination);
		EventLogger.register(guid, pDestination, EventLogger.VIEWER_STRING);
	}

	public void show() {
		EventLogger.startEventLogViewer();
	}

	public void clear() {
		EventLogger.clearLog();
	}

	public void reset() {
	}

	public void close() {
	}

	public void writeLine(int level, String message, final int fg, final int bg, final boolean bold) {
		int eventLogLevel = EventLogger.DEBUG_INFO;

		if (level == Level.DEBUG) {
			eventLogLevel = EventLogger.DEBUG_INFO;

		} else if (level == Level.INFO) {
			eventLogLevel = EventLogger.INFORMATION;

		} else if (level == Level.WARN) {
			eventLogLevel = EventLogger.WARNING;

		} else if (level == Level.ERROR) {
			eventLogLevel = EventLogger.ERROR;

		} else if (level == Level.FATAL) {
			eventLogLevel = EventLogger.SEVERE_ERROR;
		}

		EventLogger.logEvent(guid, message.getBytes(), eventLogLevel);

	}

}