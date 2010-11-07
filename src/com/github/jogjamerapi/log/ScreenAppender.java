
package com.github.jogjamerapi.log;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.UiApplication;

public class ScreenAppender extends AbstractAppender {

	protected LogScreen screen;

	public ScreenAppender(String pName, String pType, int pThreshold, String pDestination) {
		super(pName, pType, pThreshold, pDestination);
		screen = new LogScreen(pName);
	}

	public void show() {
		UiApplication.getUiApplication().pushScreen(getLogScreen());
	}

	public void clear() {
		if (screen != null) {
			screen.clearLog();
		}
	}

	public void close() {
		clear();
		screen = null;
	}

	public void reset() {
		clear();
	}

	public void writeLine(int level, String message, final int fg, final int bg, final boolean bold) {
		final LogEntryField label = new LogEntryField(message, level) {
			public void paint(Graphics g) {
				try {
					if (bold) {
						setFont(FontFamily.forName("BBAlpha Serif").getFont(Font.BOLD, 12));
					} else {
						setFont(FontFamily.forName("BBAlpha Serif").getFont(Font.PLAIN, 12));
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				g.setBackgroundColor(bg);
				g.fillRect(0, 0, getWidth(), getHeight());
				g.setColor(fg);
				g.clear();
				super.paint(g);
			}
		};
		screen.addLog(label);
	}

	public LogScreen getLogScreen() {
		return screen;
	}

}