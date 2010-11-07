
package com.github.jogjamerapi.log;

import java.util.Vector;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class LogScreen extends MainScreen {

	protected VerticalFieldManager vfm;
	protected Vector logEntries;
	protected int displayLevel;

	public LogScreen(String pName) {
		logEntries = new Vector();
		displayLevel = Level.DEBUG;

		setTitle("Log Screen: " + pName);
		vfm = new VerticalFieldManager();
		add(vfm);

		addMenuItem(new MenuItem(">= DEBUG", 200000, 10) {
			public void run() {
				setDisplayLevel(Level.DEBUG);
				refresh();
			}
		});

		addMenuItem(new MenuItem(">= INFO", 200000, 10) {
			public void run() {
				setDisplayLevel(Level.INFO);
				refresh();
			}
		});

		addMenuItem(new MenuItem(">= WARN", 200000, 10) {
			public void run() {
				setDisplayLevel(Level.WARN);
				refresh();
			}
		});

		addMenuItem(new MenuItem(">= ERROR", 200000, 10) {
			public void run() {
				setDisplayLevel(Level.ERROR);
				refresh();
			}
		});

		addMenuItem(new MenuItem(">= FATAL", 200000, 10) {
			public void run() {
				setDisplayLevel(Level.FATAL);
				refresh();
			}
		});

		addMenuItem(new MenuItem("Clear Log", 200000, 10) {
			public void run() {
				clearLog();
			}
		});

	}

	public void addLog(LogEntryField field) {
		logEntries.addElement(field);
		if (Level.isGreaterOrEqual(field.getLogLevel(), displayLevel)) {
			vfm.add(field);
		}
	}

	public void clearLog() {
		if (vfm != null) {
			vfm.deleteAll();
		}
		if (logEntries != null) {
			logEntries.removeAllElements();
		}
	}

	public void refresh() {
		vfm.deleteAll();
		for (int i = 0; i < logEntries.size(); i++) {
			LogEntryField curr = (LogEntryField) logEntries.elementAt(i);
			if (Level.isGreaterOrEqual(curr.getLogLevel(), displayLevel)) {
				vfm.add(curr);
			}
		}
	}

	public void setDisplayLevel(int pLevel) {
		displayLevel = pLevel;
	}

	protected boolean onSavePrompt() {
		return true;
	}

}
