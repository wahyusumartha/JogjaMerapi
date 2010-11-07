
package com.github.jogjamerapi.log;

public class RichTextFileAppender extends TextFileAppender {

	public RichTextFileAppender(String pName, String pType, int pThreshold, String pDestination) {
		super(pName, pType, pThreshold, pDestination);
	}

	public void writeLine(int level, String message, final int fg, final int bg, final boolean bold) {
		if (level == Level.DEBUG) {
			super.writeLine(level, "<span style=\"color:" + "#000000" + "\">" + message + "</span><br>", fg, bg, bold);

		} else if (level == Level.INFO) {
			super.writeLine(level, "<span style=\"font-weight:bold; color:" + "#00FF00" + "\">" + message + "</span><br>", fg, bg, bold);

		} else if (level == Level.WARN) {
			super.writeLine(level, "<span style=\"font-weight:bold; color:" + "#F4A460" + "\">" + message + "</span><br>", fg, bg, bold);

		} else if (level == Level.ERROR) {
			super.writeLine(level, "<span style=\"font-weight:bold; color:" + "#FF0000" + "\">" + message + "</span><br>", fg, bg, bold);

		} else if (level == Level.FATAL) {
			super.writeLine(level, "<span style=\"font-weight:bold; color:" + "#FF0000" + ";" + "background-color:" + "#000000" + "\">" + message + "</span><br>", fg, bg, bold);
		}
	}

}