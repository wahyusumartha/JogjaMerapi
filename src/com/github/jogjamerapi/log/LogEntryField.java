
package com.github.jogjamerapi.log;

import net.rim.device.api.ui.component.LabelField;

public class LogEntryField extends LabelField {

	protected int logLevel;

	public LogEntryField(String message, int pLogLevel) {
		super(message, LabelField.USE_ALL_WIDTH | LabelField.FIELD_LEFT | LabelField.FOCUSABLE);
		logLevel = pLogLevel;
	}

	public int getLogLevel() {
		return logLevel;
	}

}
